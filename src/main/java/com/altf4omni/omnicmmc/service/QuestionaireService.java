package com.altf4omni.omnicmmc.service;

import com.altf4omni.omnicmmc.dto.QuestionCreationRequest;
import com.altf4omni.omnicmmc.entity.Question;
import com.altf4omni.omnicmmc.repository.QuestionaireRepository;
import org.springframework.stereotype.Service;

/**
 * This java class will have the logic for the question and questionAnswer
 */
@Service
public class QuestionaireService {
    private final QuestionaireRepository questionaireRepository;

    public QuestionaireService(QuestionaireRepository questionaireRepository) {
        this.questionaireRepository = questionaireRepository;
    }
    public String createQuestion(QuestionCreationRequest request) {
        //var question asks for questionName and questionAnswer from dto/QuestionCreationRequest and creates an entity
        //and of type entity/Questionaire
        var question = Question.builder()
                .prompt(request.getQuestionName())
                .build();
        questionaireRepository.save(question);
        return "Question created successfully!";
    }
}
