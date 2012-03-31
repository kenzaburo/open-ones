package openones.util;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Domain {
    final static Logger LOG = Logger.getLogger("Domain");
    final static WebClient webClient = new WebClient();
    /**
     * [Give the description for method].
     * 
     * @param args
     */
    public static void main(String[] args) {
        String domain;
        boolean isAvailable;

        for (char ch1 = 'r'; ch1 <= 'z'; ch1++) {
            LOG.info("Scan first char " + ch1);
            for (char ch2 = 'a'; ch2 <= 'z'; ch2++) {
                for (char ch3 = 'a'; ch3 <= 'z'; ch3++) {
                    domain = String.valueOf(ch1) + String.valueOf(ch2) + String.valueOf(ch3);
                    LOG.info(domain + ",");
                    isAvailable = isAvailable(domain, ".com");
                    if (isAvailable) {
                        LOG.info(domain + ".com is available.");
                    }
                }
            }

        }
        
//        for (char ch1 = 'i'; ch1 <= 'z'; ch1++) {
//            LOG.info("Scan first char " + ch1);
//            for (char ch2 = 'a'; ch2 <= 'z'; ch2++) {
//                for (char ch3 = 'a'; ch3 <= 'z'; ch3++) {
//                    domain = String.valueOf(ch1) + String.valueOf(ch2) + String.valueOf(ch3);
//                    LOG.info(domain + ",");
//                    isAvailable = isAvailable(domain, ".com");
//                    if (isAvailable) {
//                        LOG.info(domain + ".com is available.");
//                    }
//                }
//            }
//
//        }

    }

    /**
     * [Give the description for method].
     * 
     * @param domain gogo
     * @param tld .com
     * @return
     */
    public static boolean isAvailable(String domain, String tld) {
        int nmMember = -1;
        
        HtmlPage startPage;
        String hostUrl = "http://www.register.com/domain/searchresults.rcmx?webmetrics=null&searchOrigin=homepage&domain="
                + domain + "&selectedTLDs=" + tld;

        try {
            webClient.setJavaScriptEnabled(false);

            startPage = webClient.getPage(hostUrl);
            // HtmlElement element = startPage.getElementById("moduleGroupInfo");

            HtmlElement contentElement = startPage.getElementById("notAvailable");

            // System.out.println("contentElement" + contentElement);
            return (contentElement == null);

        } catch (Exception ex) {
            LOG.error("Get number of members from group at: " + hostUrl, ex);
        }
        return true;

    }
}
