package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import java.util.LinkedList;

public class Section extends LinkedList<Question> {
    private String quiz_template_path;
    private String score_template_path;

    public Section(String quiz_template_path, String score_template_path, LinkedList<Question> questions){
        super(questions);
        this.quiz_template_path = quiz_template_path;
        this.score_template_path = score_template_path;
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
