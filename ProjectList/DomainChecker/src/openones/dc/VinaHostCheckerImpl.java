/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package openones.dc;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import rocky.common.CommonUtil;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

/**
 * @author thachln
 */
public class VinaHostCheckerImpl implements IDomainChecker {
    /** Logger. */
    static final Logger LOG = Logger.getLogger("VinaHostCheckerImpl");
    final static WebClient webClient = new WebClient();

    /**
     * Pattern query to check domain from VinaHost. Ex:
     * https://secure.vinahost.vn/ac/domainchecker.php?domain=open-ones&ext=.com
     */
    static final String URL_PATTERN = "https://secure.vinahost.vn/ac/domainchecker.php?domain=${domain}&ext=${ext}";

    /**
     * To check a domain with multi extensions, using this sample url:
     * https://secure.vinahost.vn/ac/domainchecker.php?domain=openone&tlds[]=.com&tlds[]=.co&tlds[]=.vn
     */
    static final String URL_MULTI_ROOT = "https://secure.vinahost.vn/ac/domainchecker.php?domain=${domain}";

    public VinaHostCheckerImpl() {
        webClient.setJavaScriptEnabled(false);
    }

    @Override
    public boolean isAvailable(String domainName, String ext) {
        Map<String, Object> valueMap = new HashMap<String, Object>();

        valueMap.put("domain", domainName);
        valueMap.put("ext", ext);
        String query = CommonUtil.formatPattern(URL_PATTERN, valueMap);

        LOG.debug("query=" + query);

        try {
            HtmlPage resultPage = webClient.getPage(query);
            return parsePage(resultPage);
        } catch (Exception ex) {
            LOG.error("Parse query " + query, ex);
        }

        return false;
    }

    @Override
    public boolean[] checkAvailable(String domain, String[] exts) {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        StringBuffer queryBuffer = new StringBuffer();

        valueMap.put("domain", domain);
        String queryDomain = CommonUtil.formatPattern(URL_MULTI_ROOT, valueMap);
        queryBuffer.append(queryDomain);

        // Build parameters for extensions
        // Ex: tlds[]=.com&tlds[]=.co&tlds[]=.vn
        for (String ext : exts) {
            queryBuffer.append("&tlds[]=").append(ext);
        }
        LOG.debug("query=" + queryBuffer.toString());

        try {
            HtmlPage resultPage = webClient.getPage(queryBuffer.toString());
            return checkStatus(domain, exts, resultPage);
        } catch (Exception ex) {
            LOG.error("Parse query " + queryBuffer, ex);
        }

        return null;
    }

    /**
     * [Give the description for method].
     * @param page
     * @return
     */
    private boolean parsePage(HtmlPage page) {
        LOG.debug(page.asXml());
        String strPage = page.asXml();

        if (strPage.indexOf("đã được đăng ký") > 0) {
            return true;
        }

        if (strPage.indexOf("chưa được đăng ký!") > 0) {
            return false;
        }

        return false;
    }

    private boolean[] checkStatus(String domain, String[] exts, HtmlPage page) {
        boolean[] resultCheck = new boolean[exts.length];
        Map<String, String> statusMap = parsePageMultiExts(page);

        String fullDomain;
        String status;
        for (int i = 0; i < exts.length; i++) {
            fullDomain = domain + exts[i];
            if (statusMap.containsKey(fullDomain)) {
                status = statusMap.get(fullDomain);
                if ("Tên miền đã đăng ký!".equals(status)) {
                    resultCheck[i] = false;
                } else if ("Tên miền chưa được đăng ký. Đăng ký ngay!".equals(status)) {
                    resultCheck[i] = true;
                }
            } else {
                LOG.debug("Domain '" + fullDomain + "' not found");
            }
        }

        return resultCheck;
    }

    /**
     * [Give the description for method].
     * @param page
     * @return
     */
    private Map<String, String> parsePageMultiExts(HtmlPage page) {
        Map<String, String> result = new Hashtable<String, String>();

        // LOG.debug(page.asXml());
        List<HtmlForm> formList = page.getForms();

        // Get last form
        HtmlForm resultForm = formList.get(formList.size() - 1);

        // LOG.debug("resultForm=" + resultForm.asXml());

        // Get table of result
        DomNodeList<HtmlElement> htmlTableList = resultForm.getElementsByTagName("table");
        HtmlTable htmlContentTable = (HtmlTable) htmlTableList.get(0);

        // Scan data, skip the header row
        HtmlTableRow row;
        HtmlTableCell cellDomain;
        HtmlTableCell cellStatus;
        for (int i = 0; i < htmlContentTable.getRowCount(); i++) {
            row = htmlContentTable.getRow(i);

            // LOG.debug("row " + i + ":" + row.asXml());
            cellDomain = row.getCell(1);
            cellStatus = row.getCell(2);
            result.put(cellDomain.asText().trim(), cellStatus.asText().trim());
            LOG.debug("cell 1:" + cellDomain.asText() + ";cell 2:" + cellStatus.asText());
        }
        return result;
    }
}
