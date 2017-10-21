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
            @RequestParam(value="section_template", required=false, defaultValue="Section_1") String section_template,
            @RequestParam(value="question_template", required=false, defaultValue="Section_1/Question_1") String question_template,
            Model model) {
        String user_name = "Stu Dent"; //TODO implement current user name
        model.addAttribute("user_name", user_name);
        model.addAttribute("section_template", section_template);
        model.addAttribute("question_template", question_template);
        return "QuizPage";
    }


}
