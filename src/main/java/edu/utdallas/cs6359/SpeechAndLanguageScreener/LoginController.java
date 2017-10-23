package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    Map<User.Type, User> users;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView renderTestView(ModelMap model) {
        model.addAttribute("users", users);
        return new ModelAndView("/login_page", model);
    }


    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ModelAndView saveNames(HttpServletRequest request){
        Map<String, String[]> parameters = request.getParameterMap();
        //validity check the names and store them if they pass
        if(!parameters.containsKey("tester_name")
                || !parameters.containsKey("testee_name")
                || parameters.get("tester_name")[0] == ""
                || parameters.get("testee_name")[0] == ""){
            //TODO error page: no names entered
            request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.BAD_REQUEST);
            return new ModelAndView("/error_page");
        }
        users.get(User.Type.TESTER).setName(parameters.get("tester_name")[0]);
        users.get(User.Type.TESTEE).setName(parameters.get("testee_name")[0]);
        //set http status code to 302 since we are redirecting to a GET
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.FOUND);
        return new ModelAndView("redirect:/test");
    }

}
