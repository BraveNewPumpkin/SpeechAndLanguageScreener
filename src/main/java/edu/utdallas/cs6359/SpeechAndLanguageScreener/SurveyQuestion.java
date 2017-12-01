package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

public class SurveyQuestion extends Question{
    protected int points_possible;
    Set<String> given_answers;

    public SurveyQuestion(String template_path, int points_possible){
        super(template_path);
        this.points_possible = points_possible;
    }

    @Override
    public void set_given_answers(Map<String, String[]> answers){
        String[] temp = answers.get("answer");
        if(temp != null){
            Set<String> answer_set = new HashSet<String>(Arrays.asList(temp));
            set_given_answers(answer_set);
        }else{
            //TODO error response "invalid answer"
        }
    }

    public void set_given_answers(Set<String> answers){
        given_answers = answers;
    }

    @Override
    public int calcPointsEarned(){
        int points_earned = 0;
        if(given_answers != null) {
            points_earned = given_answers.size();
        }
        return points_earned;
    }

    @Override
    public int calcPointsPossible(){
        return points_possible;
    }

    @Bean
    @Override
    public Score getScore(){
        return new Score(calcPointsEarned(), calcPointsPossible());
    }
}
