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
    public String renderQuizView(Model model) {
        String section_template_path = "quiz_sections/section_1";
        String question_template_path = "quiz_sections/section_1/question_1";
        String user_name = "Stu Dent"; //TODO implement current user name
        model.addAttribute("user_name", user_name);
        model.addAttribute("section_template_path", section_template_path);
        model.addAttribute("question_template_path", question_template_path);
        return "quiz_page";
    }


}
