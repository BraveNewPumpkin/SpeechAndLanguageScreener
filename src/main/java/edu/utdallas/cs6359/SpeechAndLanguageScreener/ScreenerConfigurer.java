package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.ArrayList;

@Configuration
public class ScreenerConfigurer {

    @Bean
    @Qualifier("screener")
    public Quiz quiz(){
        //TODO load this config data from file and populate sections and questions
        Question question_1 = new Question("quiz_sections/section_1/question_1");
        Question question_2 = new Question("quiz_sections/section_1/question_2");
        ArrayList<Question> questions = new ArrayList<>(Arrays.asList(question_1, question_2));
        Section section_1 = new Section("quiz_sections/section_1", "score_sections/section_1", questions);
        question_1 = new Question("quiz_sections/section_2/question_1");
        question_2 = new Question("quiz_sections/section_2/question_2");
        questions = new ArrayList<>(Arrays.asList(question_1, question_2));
        Section section_2 = new Section("quiz_sections/section_1", "score_sections/section_1", questions);
        ArrayList<Section> sections = new ArrayList<>(Arrays.asList(section_2));
        return new Quiz(sections);
    }

}
