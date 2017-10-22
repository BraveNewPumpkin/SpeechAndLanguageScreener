package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class Section extends ArrayList<Question> {
    private String name;
    private String quiz_template_path;
    private String score_template_path;

    public Section(String name, String quiz_template_path, String score_template_path, List<Question> questions){
        super(questions);
        this.name = name;
        this.quiz_template_path = quiz_template_path;
        this.score_template_path = score_template_path;
    }

    public String getName() {
        return name;
    }

    public String get_quiz_template_path() {
        return quiz_template_path;
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

    public void setQuiz_template_path(String quiz_template_path) {
        this.quiz_template_path = quiz_template_path;
    }

    public void setScore_template_path(String score_template_path) {
        this.score_template_path = score_template_path;
    }
}
