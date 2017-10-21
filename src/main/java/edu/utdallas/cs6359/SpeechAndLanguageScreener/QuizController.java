package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.regex.Pattern;

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
            current_question = getNextQuestion();
            if(current_question == null){
                //TODO error page: "test had no questions"
            }
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

    private int getCurrentSectionId(){
        return quiz.indexOf(current_section);
    }

    private int getCurrentQuestionId(){
        return current_section.indexOf(current_question);
    }

    private Section getSectionById(int id){
        return quiz.get(id);
    }

    private String getRedirectString(){
        return "redirect:/quiz/section_" + getCurrentSectionId() + "/question_" + getCurrentQuestionId();
    }

    @RequestMapping(value = "/quiz", method = RequestMethod.GET)
    public ModelAndView redirectToQuizStart(ModelMap model){
        return new ModelAndView(getRedirectString(), model);
    }


    @RequestMapping(value = "/quiz/section_{section_id:[\\d]+}/question_{question_id:[\\d]+}", method = RequestMethod.GET)
    public ModelAndView renderQuizView(
            @PathVariable int section_id,
            @PathVariable int question_id,
            ModelMap model) {
        String user_name = "Stu Dent"; //TODO implement current user name. this should come from the welcome page
        model.addAttribute("user_name", user_name);
        model.addAttribute("section_template_path", quiz.get(section_id).get_quiz_template_path());
        model.addAttribute("question_template_path", current_section.get(question_id).get_template_path());

        return new ModelAndView("quiz_page", model);
    }

    @RequestMapping(value = "/quiz/section_{section_id:[\\d]+}/question_{question_id:[\\d]+}", method = RequestMethod.POST)
    public ModelAndView recordQuestionResponse(
            @PathVariable int section_id,
            @PathVariable int question_id,
 //           @RequestBody String postPayload,
            HttpServletRequest request) {
        //TODO record the response in current_question
        //set http status code to 302 since we are redirecting to a GET
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.FOUND);
        current_question = getNextQuestion();
        if(current_question == null){
            return new ModelAndView("redirect:/scores");
        }
        return new ModelAndView(getRedirectString());
    }

}
