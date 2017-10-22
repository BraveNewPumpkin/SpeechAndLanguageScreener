package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.context.annotation.Bean;

import java.util.Map;

public abstract class Question {
    protected String template_path;

    public Question(String template_path){
        this.template_path = template_path;
    }

    public String get_template_path() {
        return template_path;
    }

    public abstract void set_given_answers(Map<String, String[]> answers);
    @Bean
    public abstract Score getScore();
    public abstract int calcPointsEarned();
    public abstract int calcPointsPossible();

}
