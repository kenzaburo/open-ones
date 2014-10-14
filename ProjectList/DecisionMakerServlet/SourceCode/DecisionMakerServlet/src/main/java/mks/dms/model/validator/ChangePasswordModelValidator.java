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

import mks.dms.model.ChangePasswordModel;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author ThachLN
 */
public class ChangePasswordModelValidator implements Validator {
    private final static Logger LOG = Logger.getLogger(ChangePasswordModelValidator.class);

    @Override
    public boolean supports(Class<?> paramClass) {
        return ChangePasswordModel.class.equals(paramClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        // Check required
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "required");
        // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmNewPassword", "required");

        ChangePasswordModel changePasswordModel = (ChangePasswordModel) obj;
        String newPassword = changePasswordModel.getNewPassword();
        String connfirmNewPassword = changePasswordModel.getConfirmNewPassword();

        
        if ((newPassword != null) && (!newPassword.equals(connfirmNewPassword))) {
             errors.rejectValue("matchedPassword", "Password_must_match");
        }

                
    }

}
