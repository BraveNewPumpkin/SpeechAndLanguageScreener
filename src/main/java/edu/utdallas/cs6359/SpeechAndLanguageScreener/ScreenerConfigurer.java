package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;

@Configuration
public class ScreenerConfigurer {

    @Bean
    @Qualifier("screener")
    public Quiz quiz(){
        //TODO load this config data from file and populate sections and questions
        Question question_0 = new SingleAnswerQuestion(
                "quiz_sections/section_0/question_0",
                new HashSet<String>(Arrays.asList("option_1")));
        Question question_1 = new SingleAnswerQuestion(
                "quiz_sections/section_0/question_1",
                new HashSet<String>(Arrays.asList("option_3")));
        ArrayList<Question> questions = new ArrayList<>(Arrays.asList(question_0, question_1));
        Section section_0 = new Section(
                "Receptive Language Section",
                "quiz_sections/section",
                "score_sections/section_0",
                questions);
        question_0 = new SurveyQuestion(
                "quiz_sections/section_1/question_0",
                4);
        question_1 = new SurveyQuestion(
                "quiz_sections/section_1/question_1",
                4);
        questions = new ArrayList<>(Arrays.asList(question_0, question_1));
        Section section_1 = new SurveySection(
                "Expressive Language Section",
                "quiz_sections/section",
                "score_sections/section_0",
                questions);
        ArrayList<Section> sections = new ArrayList<>(Arrays.asList(section_0, section_1));
        return new Quiz(sections);
    }

}
