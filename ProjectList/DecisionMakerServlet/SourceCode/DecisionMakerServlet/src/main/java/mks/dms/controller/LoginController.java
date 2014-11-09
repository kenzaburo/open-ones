package mks.dms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private static final String ATTR_ERROR = "error";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String homelogin(Model model, @RequestParam(value = ATTR_ERROR, required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        if (error != null) {
            model.addAttribute(ATTR_ERROR, "Wrong_Username_or_Password");
        } else {
            // Do nothing
        }

        return "login";
    }

}
