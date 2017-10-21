package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.LinkedList;

@Configuration
public class ScreenerConfigurer {
    @Bean
    @Qualifier("screener")
    public Quiz quiz(){
        //TODO load quiz_sections and questions and populate quiz_sections with questions
        Question question_1 = new Question("quiz_sections/section_1/question_1");
        LinkedList<Question> questions = new LinkedList<>(Arrays.asList(question_1));
        Section section_1 = new Section("quiz_sections/section_1", "score_sections/section_1", questions);
        LinkedList<Section> sections = new LinkedList<>(Arrays.asList(section_1));
        return new Quiz(sections);
    }
}
