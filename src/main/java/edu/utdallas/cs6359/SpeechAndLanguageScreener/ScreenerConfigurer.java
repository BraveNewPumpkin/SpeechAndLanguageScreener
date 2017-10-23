package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ScreenerConfigurer {

    @Bean
    @Qualifier("screener")
    public Test test(){
        //TODO load this config data from file and populate sections and questions
        Question question_0 = new SingleAnswerQuestion(
                "test_sections/section_0/question_0",
                new HashSet<String>(Arrays.asList("option_1")));
        Question question_1 = new SingleAnswerQuestion(
                "test_sections/section_0/question_1",
                new HashSet<String>(Arrays.asList("option_3")));
        ArrayList<Question> questions = new ArrayList<>(Arrays.asList(question_0, question_1));
        Section section_0 = new Section(
                "Receptive Language Section",
                "test_sections/section",
                "score_sections/section_0",
                User.Type.TESTEE,
                questions);
        question_0 = new SurveyQuestion(
                "test_sections/section_1/question_0",
                4);
        question_1 = new SurveyQuestion(
                "test_sections/section_1/question_1",
                4);
        questions = new ArrayList<>(Arrays.asList(question_0, question_1));
        Section section_1 = new SurveySection(
                "Expressive Language Section",
                "test_sections/section",
                "score_sections/survey_section",
                User.Type.TESTER,
                questions);
        ArrayList<Section> sections = new ArrayList<>(Arrays.asList(section_0, section_1));
        return new Test(sections);
    }

    @Bean
    public Map<User.Type, User> makeUsers(){
        Map<User.Type, User> users = new HashMap<>();
        users.put(User.Type.TESTER, new User());
        users.put(User.Type.TESTEE, new User());
        return Collections.unmodifiableMap(users);
    }

}
