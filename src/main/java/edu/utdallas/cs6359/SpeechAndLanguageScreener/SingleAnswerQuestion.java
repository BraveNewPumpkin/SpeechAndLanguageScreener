package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.Set;

public class SingleAnswerQuestion extends Question{
    protected final int points_possible = 1;
    Set<String> given_answers;
    private Set<String> correct_answers;

    public SingleAnswerQuestion(String template_path, Set<String> correct_answers){
        super(template_path);
        this.correct_answers = correct_answers;
    }

    @Override
    public void set_given_answers(Map<String, String[]> answers){
        set_given_answers(answers.keySet());
    }

    public void set_given_answers(Set<String> answers){
        given_answers = answers;
    }

    @Bean
    @Override
    public Score getScore(){
        return new Score(calcPointsEarned(), calcPointsPossible());
    }

    @Override
    public int calcPointsEarned(){
        int points_earned;
        if(correct_answers.containsAll(given_answers)){
            points_earned = points_possible;
        } else {
            points_earned = 0;
        }
        return points_earned;
    }

    @Override
    public int calcPointsPossible(){
        return points_possible;
    }
}
