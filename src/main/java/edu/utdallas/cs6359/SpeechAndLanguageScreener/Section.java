package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class Section extends ArrayList<Question> {
    private String name;
    private String test_template_path;
    private String score_template_path;

    public Section(String name, String test_template_path, String score_template_path, List<Question> questions){
        super(questions);
        this.name = name;
        this.test_template_path = test_template_path;
        this.score_template_path = score_template_path;
    }

    public String getName() {
        return name;
    }

    public String get_test_template_path() {
        return test_template_path;
    }

    public String get_score_template_path() {
        return score_template_path;
    }

    @Bean
    public Score calcScore(){
        Score score = new Score(0, 0);
        for(Question question: this){
            score.addScore(question.getScore());
        }
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTest_template_path(String test_template_path) {
        this.test_template_path = test_template_path;
    }

    public void setScore_template_path(String score_template_path) {
        this.score_template_path = score_template_path;
    }
}
