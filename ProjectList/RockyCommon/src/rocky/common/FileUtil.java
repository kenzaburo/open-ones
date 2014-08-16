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
package rocky.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * @author thachle
 */
public class FileUtil {
    private final static Logger LOG = Logger.getLogger(FileUtil.class);

    /**
     * Đọc nội dung của file resource với encoding cho trước
     * @param resourcePath đường dẫn file trong CLASSPATH
     * @param encoding
     * @return nội dung file. Nếu có lỗi thì trả lại null
     */
    public static String getContent(String resourcePath, String encoding) throws IOException {
        InputStream fis = null;
        InputStreamReader isReader = null;
        BufferedReader buffReader = null;
        char[] buff = new char[512];
        int len;
        StringBuffer sb = new StringBuffer();
        try {
            fis = CommonUtil.loadResource(resourcePath);
            isReader = new InputStreamReader(fis, encoding);
            buffReader = new BufferedReader(isReader);
            while ((len = buffReader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
        } catch (Exception e) {
            // e.printStackTrace();
        } finally {
            if (buffReader != null) {
                buffReader.close();
            }
            if (isReader != null) {
                isReader.close();
            }
            if (fis != null) {
                fis.close();
            }
        }

        return sb.toString();

    }

    /**
     * Đọc nội dung của file resource với encoding cho trước
     * @param resourcePath đường dẫn file trong CLASSPATH
     * @param encoding
     * @return nội dung file. Nếu có lỗi thì trả lại null
     */
    public static String getContent(String resourcePath, boolean isResource, String encoding) {
        InputStream fis = null;
        InputStreamReader isReader = null;
        BufferedReader buffReader = null;
        char[] buff = new char[512];
        int len;
        StringBuffer sb = new StringBuffer();
        try {
            if (isResource) {
                fis = CommonUtil.class.getResourceAsStream(resourcePath);
            } else {
                fis = new FileInputStream(resourcePath);
            }
            if (CommonUtil.isNNandNB(encoding)) {
                isReader = new InputStreamReader(fis, encoding);
            } else {
                isReader = new InputStreamReader(fis);
            }

            buffReader = new BufferedReader(isReader);
            while ((len = buffReader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
        } catch (IOException ioe) {
            LogService.logWarn("CommonUtil.getContent(" + resourcePath + "," + encoding + ") throws", ioe);
            return null;
        } finally {
            close(buffReader);
            close(isReader);
            close(fis);
        }

        return sb.toString();

    }

    public static String getContent(File file, String encoding) throws IOException {
        InputStream fis = null;
        InputStreamReader isReader = null;
        BufferedReader buffReader = null;
        char[] buff = new char[512];
        int len;
        StringBuffer sb = new StringBuffer();

        try {
            fis = new FileInputStream(file);
            isReader = new InputStreamReader(fis, encoding);
            buffReader = new BufferedReader(isReader);
            while ((len = buffReader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
        } catch (Exception ex) {
            LOG.error("Reading content of file", ex);
        } finally {
            close(buffReader);
            close(isReader);
            close(fis);
        }

        return sb.toString();
    }

    public static void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException ex) {
                LOG.warn(ex);
            }
        }
    }

    public static void close(BufferedReader buffReader) {
        if (buffReader != null) {
            try {
                buffReader.close();
            } catch (IOException ex) {
                LOG.warn(ex);
            }
        }
    }

    public static void close(InputStreamReader isReader) {
        if (isReader != null) {
            try {
                isReader.close();
            } catch (IOException ex) {
                LOG.warn(ex);
            }
        }
    }
}
