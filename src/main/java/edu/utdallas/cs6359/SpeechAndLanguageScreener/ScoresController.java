package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kyle Bolton
 */
@Controller
public class ScoresController {
    @Autowired
    @Qualifier("screener")
    private Quiz quiz;


    @RequestMapping(value = "/scores", method = RequestMethod.GET)
    public ModelAndView renderQuizView(
            ModelMap model) {
        return new ModelAndView("/scores_page", model);
    }
}
