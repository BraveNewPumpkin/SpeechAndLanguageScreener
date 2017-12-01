package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.Set;

public class SingleAnswerQuestion extends Question{
    protected final int points_possible = 1;
    String given_answer;
    private String correct_answer;

    public SingleAnswerQuestion(String template_path, String correct_answer){
        super(template_path);
        this.correct_answer = correct_answer;
    }

    @Override
    public void set_given_answers(Map<String, String[]> answers){
        String[] temp = answers.get("answer");
        if(temp != null){
            set_given_answer(temp[0]);
        }else{
            //TODO error response "invalid answer"
        }
    }

    public void set_given_answer(String answer){
        given_answer = answer;
    }

    @Bean
    @Override
    public Score getScore(){
        return new Score(calcPointsEarned(), calcPointsPossible());
    }

    @Override
    public int calcPointsEarned(){
        int points_earned;
        if(correct_answer != null && correct_answer.equals(given_answer)){
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
