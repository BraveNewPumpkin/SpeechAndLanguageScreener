package edu.utdallas.cs6359.SpeechAndLanguageScreener;

public class Question {
    private String template_path;

    public Question(String template_path){
        this.template_path = template_path;
    }

    public String get_template_path() {
        return template_path;
    }
}
