package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.context.annotation.Bean;

import java.util.Map;
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
        set_given_answers(answers.keySet());
    }

    public void set_given_answers(Set<String> answers){
        given_answers = answers;
    }

    @Override
    public int calcPointsEarned(){
        return given_answers.size();
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
