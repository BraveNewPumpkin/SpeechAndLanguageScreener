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
import java.util.Map;
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
        try{
            setCurrentQuizPosition(0, 0);
            if(current_question == null){
                //TODO error page: "test had no questions"
            }
        } catch (IndexOutOfBoundsException e){
            //TODO error page: "test is missing sections or questions"
        }
    }

    private void setCurrentQuizPosition(int section_id, int question_id) throws IndexOutOfBoundsException {
        sections_iter = quiz.listIterator(section_id);
        current_section = sections_iter.next();
        questions_iter = current_section.listIterator(question_id);
        current_question = questions_iter.next();
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
            } else {
                //we're out of options and haven't found next question
                break;
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
        model.addAttribute("section_name", current_section.get_name());
        model.addAttribute("section_template_path", quiz.get(section_id).get_quiz_template_path());
        model.addAttribute("question_template_path", current_section.get(question_id).get_template_path());

        return new ModelAndView("/quiz_page", model);
    }

    @RequestMapping(value = "/quiz/section_{section_id:[\\d]+}/question_{question_id:[\\d]+}", method = RequestMethod.POST)
    public ModelAndView recordQuestionResponse(
            @PathVariable int section_id,
            @PathVariable int question_id,
            HttpServletRequest request) {
        //POSTing a question out of order resets the order to the given question
        if(getCurrentSectionId() != section_id || getCurrentQuestionId() != question_id){
            try {
                setCurrentQuizPosition(section_id, question_id);
            } catch (IndexOutOfBoundsException e) {
                //TODO error page 404
            }
        }
        //TODO record the response in current_question
        Map<String, String[]> parameters = request.getParameterMap();
        current_question.setAnswer(parameters);
        //set http status code to 302 since we are redirecting to a GET
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.FOUND);
        current_question = getNextQuestion();
        if(current_question == null){
            return new ModelAndView("redirect:/scores");
        }
        return new ModelAndView(getRedirectString());
    }

}
