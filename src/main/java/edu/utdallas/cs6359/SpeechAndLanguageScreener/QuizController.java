package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.Iterator;

/**
 * @author Kyle Bolton
 */
@Controller
public class QuizController {
    @Autowired
    @Qualifier("screener")
    private Quiz quiz;

    private Iterator<Section> sections_iter;
    private Iterator<Question> questions_iter;
    private Section current_section;
    private Question current_question;


    @PostConstruct
    private void initialize(){
        sections_iter = quiz.iterator();
        if (sections_iter.hasNext()) {
            current_section = sections_iter.next();
            questions_iter = current_section.iterator();
        } else {
            //TODO error page: "test had no sections"
        }
    }

    private Question getNextQuestion() {
        Question next_question = null;
        boolean next_question_found = false;
        while(!next_question_found) {
            if (questions_iter.hasNext()) {
                next_question = questions_iter.next();
                next_question_found = true;
            } else if (sections_iter.hasNext()) {
                current_section = sections_iter.next();
                questions_iter = current_section.iterator();
            }
        }
        return next_question;
    }

    @RequestMapping(value = "/quiz", method = RequestMethod.GET)
    public String renderQuizView(Model model) {
        String user_name = "Stu Dent"; //TODO implement current user name
        current_question = getNextQuestion();
        if(current_question == null){
            //TODO redirect to GET /scores_page
            return "scores_page";
        }
        model.addAttribute("user_name", user_name);
        model.addAttribute("section_template_path", current_section.get_quiz_template_path());
        model.addAttribute("question_template_path", current_question.get_template_path());
        return "quiz_page";
    }

    @RequestMapping(value = "/quiz", method = RequestMethod.POST)
    public String recordQuestionResponse(Model model) {
        //TODO record the response in current_question

        return renderQuizView(model);
    }

}
