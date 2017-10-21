package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import java.util.Map;

public class Question {
    private String template_path;

    public Question(String template_path){
        this.template_path = template_path;
    }

    public void setAnswer(Map<String, String[]> answer){
        //TODO implement answer and correct answer
    }

    public String get_template_path() {
        return template_path;
    }
}
