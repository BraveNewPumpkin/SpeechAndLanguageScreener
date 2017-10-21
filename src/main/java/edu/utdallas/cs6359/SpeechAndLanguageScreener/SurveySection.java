package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import java.util.LinkedList;

public class SurveySection extends Section{

    public SurveySection(String quiz_template_path, String score_template_path, LinkedList<Question> questions){
        super(quiz_template_path, score_template_path, questions);
    }

    @Override
    public Score calcScore() {
        //TODO implement
        return super.calcScore();
    }
}
