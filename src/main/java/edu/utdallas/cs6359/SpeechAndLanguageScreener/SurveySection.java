package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import java.util.List;

public class SurveySection extends Section{

    public SurveySection(String name, String test_template_path, String score_template_path, List<Question> questions){
        super(name, test_template_path, score_template_path, questions);
    }

    @Override
    public Score calcScore() {
        //TODO implement
        return super.calcScore();
    }
}
