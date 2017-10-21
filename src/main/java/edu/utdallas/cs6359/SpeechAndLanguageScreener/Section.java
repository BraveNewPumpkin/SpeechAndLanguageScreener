package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import java.util.ArrayList;

public class Section extends ArrayList<Question> {
    private String name;
    private String quiz_template_path;
    private String score_template_path;

    public Section(String name, String quiz_template_path, String score_template_path, ArrayList<Question> questions){
        super(questions);
        this.name = name;
        this.quiz_template_path = quiz_template_path;
        this.score_template_path = score_template_path;
    }

    public String get_name() {
        return name;
    }

    public String get_quiz_template_path() {
        return quiz_template_path;
    }

    public String get_score_template_path() {
        return score_template_path;
    }

    public Score calcScore(){
        //TODO implement
        return new Score();
    }

}
