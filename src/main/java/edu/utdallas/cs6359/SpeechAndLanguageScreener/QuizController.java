package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Kyle Bolton
 */
@Controller
public class QuizController {

    @RequestMapping(value = "/quiz", method = RequestMethod.GET)
    public String renderQuizView(
            @RequestParam(value="question_template", required=false, defaultValue="question_1") String question_template,
            Model model) {
        model.addAttribute("question_template", question_template);
        return "QuizPage";
    }


}
