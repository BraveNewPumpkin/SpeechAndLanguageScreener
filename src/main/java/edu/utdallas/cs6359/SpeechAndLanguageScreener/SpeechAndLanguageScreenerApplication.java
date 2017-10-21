package edu.utdallas.cs6359.SpeechAndLanguageScreener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

@SpringBootApplication
public class SpeechAndLanguageScreenerApplication {

	//TODO load quiz_sections and questions and populate quiz_sections with questions
    Question question_1 = new Question();
    LinkedList<Question> questions = new LinkedList<>(Arrays.asList(question_1));
    Section section_1 = new Section("quiz_sections/section_1", "score_sections/section_1", questions);
    LinkedList<Section> sections = new LinkedList<>(Arrays.asList(section_1));
    Quiz screener = new Quiz();

	public static void main(String[] args) {
		SpringApplication.run(SpeechAndLanguageScreenerApplication.class, args);
	}
}
