/**
 * Licensed to OpenOnes under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * OpenOnes licenses this file to you under the Apache License,
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
package openones.mail;


/**
 * @author DUNGND60190
 * 
 */
public class ImageFormat {
    
    public ImageFormat() {
        super();
        // TODO Auto-generated constructor stub
    }
    private String imgTag;
    private String imgSrc;
    
    public ImageFormat(String imgTag, String imgSrc) {
        super();
        this.imgTag = "<" + imgTag + ">";
        this.imgSrc = imgSrc;
    }
    /**
     * @return the imgTag
     */
    public String getImgTag() {
        return imgTag;
    }
    /**
     * @param imgTag the imgTag to set
     */
    public void setImgTag(String imgTag) {
        this.imgTag = "<" + imgTag + ">";
    }
    
    /**
     * @return the imgSrc
     */
    public String getImgSrc() {
        return imgSrc;
    }
    /**
     * @param imgSrc the imgSrc to set
     */
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
   
}
