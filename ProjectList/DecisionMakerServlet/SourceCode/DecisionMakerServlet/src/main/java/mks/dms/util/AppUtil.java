/**
 * Licensed to Open-Ones under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones licenses this file to you under the Apache License,
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
package mks.dms.util;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import rocky.common.CHARA;
import mks.dms.dao.entity.Request;
import mks.dms.model.RequestModel;

/**
 * @author ThachLN
 *
 */
public class AppUtil {
    private static final Logger LOG = Logger.getLogger(AppUtil.class);
    
  /**
  * Transfer data from model (view) to entity.
  * - Attached files.
  * - Labels
  * @param model data from client
  * @return entity for business layer
  */
  public static Request updateModelToEntity(RequestModel model, Request request) {

      // Update attachment from models
      List<MultipartFile> lstAttachment = model.getAttachments();
      if (lstAttachment != null) {
          int numOfFile = lstAttachment.size();
          if (numOfFile >= 1) {
              MultipartFile attachFile = lstAttachment.get(0);
              request.setFilename1(attachFile.getOriginalFilename());
              try {
                  request.setAttachment1(attachFile.getBytes());
              } catch (IOException ex) {
                  LOG.warn("Could not get the content of attached file", ex);
              }
          }
          
          if (numOfFile >= 2) {
              MultipartFile attachFile = lstAttachment.get(1);
              request.setFilename2(attachFile.getOriginalFilename());
              try {
                  request.setAttachment2(attachFile.getBytes());
              } catch (IOException ex) {
                  LOG.warn("Could not get the content of attached file", ex);
              }
          }
          
          if (numOfFile >= 3) {
              MultipartFile attachFile = lstAttachment.get(2);
              request.setFilename3(attachFile.getOriginalFilename());
              try {
                  request.setAttachment3(attachFile.getBytes());
              } catch (IOException ex) {
                  LOG.warn("Could not get the content of attached file", ex);
              }
          }
      }

      return request;
  }
  
  public static Object formatJson(Integer number) {
      if (number == null) {
          return CHARA.BLANK;
      } else {
          return number.toString();
      }
  }

  public static Object formatJson(String text) {
      if (text == null) {
          return CHARA.BLANK;
      } else {
          return text;
      }
  }

  /**
  * Check a data row is not empty.
  * @param dataRow items of columns in data row
  * @return true if there is an item is not null.
  */
  public static boolean isNotEmptyRow(Object[] dataRow) {
      int len = (dataRow != null) ? dataRow.length : 0;
      
      for (int i = 0; i < len; i++) {
          if ((dataRow[i] != null) && !CHARA.BLANK.equals(dataRow[i])) {
              return true;
          }
      }
      
      return false;
  }
//    public static RequestModel parseRequestEntity2Model(Request request, RequestModel model) {
//        model.setRequestId(request.getId());
//        
//        model.setFilename1(request.getFilename1());
//
//        return model;
//    }
//    /**
//    * Transfer data from model (view) to entity.
//    * @param model data from client
//    * @return entity for business layer
//    */
//    public static Request parseRequestModel2Entity(RequestModel model) {
//        Request request = new Request();
//        
//        request.setId(model.getRequestId());
//        request.setRequesttypeCd(model.getRequestTypeCd());
//        request.setTitle(model.getTitle());
//        request.setContent(model.getContent());
//        request.setAssigneeUsername(model.getAssigneeAccount());
//        request.setStartdate(model.getStartDate());
//        request.setEnddate(model.getEndDate());
//        request.setManagerUsername(model.getManagerAccount());
//        request.setDuration(model.getDuration());
//        request.setDurationunit(model.getDurationUnit());
//        
//        // 
//        request.setFilename1(model.getFilename1());
//        
//        List<MultipartFile> listAttachment = model.getAttachments();
//
//        if ((listAttachment != null) && (listAttachment.size() > 0)) {
//            try {
//                request.setAttachment1(listAttachment.get(0).getBytes());
//            } catch (IOException ex) {
//                LOG.error("Could not get attachments", ex);
//            }
//            request.setFilename1(listAttachment.get(0).getOriginalFilename());
//        }
//        
//        List<String> listLabel = model.getListLabel();
//        if ((listLabel != null) && (listLabel.size() > 0)) {
//            request.setLabel1(model.getListLabel().get(0));
//        }
//        
//        // For Announcement, Rule
//        request.setDepartmentCd(model.getDepartmentCd());
//        
//        // Update attachment from models
//        List<MultipartFile> lstAttachment = model.getAttachments();
//        if (lstAttachment != null) {
//            int numOfFile = lstAttachment.size();
//            if (numOfFile >= 1) {
//                MultipartFile attachFile = lstAttachment.get(0);
//                request.setFilename1(attachFile.getOriginalFilename());
//                try {
//                    request.setAttachment1(attachFile.getBytes());
//                } catch (IOException ex) {
//                    LOG.warn("Could not get the content of attached file", ex);
//                }
//            }
//            
//            if (numOfFile >= 2) {
//                MultipartFile attachFile = lstAttachment.get(1);
//                request.setFilename2(attachFile.getOriginalFilename());
//                try {
//                    request.setAttachment2(attachFile.getBytes());
//                } catch (IOException ex) {
//                    LOG.warn("Could not get the content of attached file", ex);
//                }
//            }
//            
//            if (numOfFile >= 3) {
//                MultipartFile attachFile = lstAttachment.get(2);
//                request.setFilename3(attachFile.getOriginalFilename());
//                try {
//                    request.setAttachment3(attachFile.getBytes());
//                } catch (IOException ex) {
//                    LOG.warn("Could not get the content of attached file", ex);
//                }
//            }
//        }
//
//        return request;
//    }

}
