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
package mks.dms.model.validator;

import java.util.Date;

import mks.dms.dao.entity.Request;
import mks.dms.model.RequestModel;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author ThachLN
 */
public class RequestModelValidator implements Validator {
    private final static Logger LOG = Logger.getLogger(RequestModelValidator.class);

    @Override
    public boolean supports(Class<?> paramClass) {
        return RequestModel.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        // Check title.required
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "request.title", "required");

        RequestModel requestModel = (RequestModel) obj;
        Request request = requestModel.getRequest();
        LOG.debug("duration=" + request.getDuration());

        // Check start date <= end date
        Date startDate = request.getStartdate();
        Date endDate = request.getEnddate();
        
        if ((startDate != null) && (endDate != null)) {
            if (startDate.after(endDate)) {
                errors.rejectValue("request.startdate", "startDate.after.endDate");
            }
        }
        
        // Check duration > 0
        Integer duration = request.getDuration();
        if (duration != null) {
            if (duration <= 0) {
                errors.rejectValue("request.duration", "number.positive");
            }
        }
                
    }

}
