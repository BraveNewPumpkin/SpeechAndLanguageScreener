package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import java.util.Map;
import java.util.Set;

public class SurveyQuestion extends Question{
    Set<String> correct_answers;
    Set<String> given_answers;

    public SurveyQuestion(String template_path, Set<String> correct_answers){
        super(template_path);
        this.correct_answers = correct_answers;
    }

    public void set_given_answers(Map<String, String[]> answers){
        set_given_answers(answers.keySet());
    }

    public void set_given_answers(Set<String> answers){
        given_answers = answers;
    }

    public int calcPointsEarned(){
        return given_answers.size();
    }

    public int calcPointsPossible(){
        return correct_answers.size();
    }
}
