package com.altf4omni.omnicmmc.service;

import com.altf4omni.omnicmmc.dto.ExtendedQuestionDto;
import com.altf4omni.omnicmmc.dto.QuestionDto;
import com.altf4omni.omnicmmc.dto.QuestionSectionDto;
import com.altf4omni.omnicmmc.dto.QuestionnaireResponse;
import com.altf4omni.omnicmmc.entity.ExtendedQuestion;
import com.altf4omni.omnicmmc.entity.Question;
import com.altf4omni.omnicmmc.repository.ExtendedQuestionRepository;
import com.altf4omni.omnicmmc.repository.QuestionRepository;
import com.altf4omni.omnicmmc.repository.QuestionSectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class QuestionaireService {
    private final QuestionRepository questionRepository;
    private final ExtendedQuestionRepository extendedQuestionRepository;
    private final QuestionSectionRepository questionSectionRepository;

    public QuestionaireService(QuestionRepository questionRepository, ExtendedQuestionRepository extendedQuestionRepository, QuestionSectionRepository questionSectionRepository) {
        this.questionRepository = questionRepository;
        this.extendedQuestionRepository = extendedQuestionRepository;
        this.questionSectionRepository = questionSectionRepository;
    }

    /**
     * Access {@link QuestionSectionRepository} and find all sections. Then for every section get all of the questions based
     * on the sectionID. Then for every question get all of the extended questions based on the questionID. Finally, build a questionnaire
     * response.
     * @return {@link QuestionnaireResponse}
     */
    public QuestionnaireResponse getQuestionnaire() {

        var sections = questionSectionRepository.findAll();

        var sectionDtos = sections.stream()
                .map(section -> {
                    var sectionID = section.getSectionID();
                    var questions = questionRepository.findAllBySectionID(sectionID);
                    var questionDtos = questions.stream()
                            .map(question -> {
                                var questionID = question.getQuestionID();
                                var extendedQuestions = extendedQuestionRepository.findAllByQuestionID(questionID);
                                var extendedQuestionDtos = extendedQuestions.stream()
                                        .map(extendedQuestion -> new ExtendedQuestionDto(extendedQuestion.getExtndPrompt(), extendedQuestion.getExtndDescription(),
                                                extendedQuestion.getExtndSequence()))
                                        .toList();
                                return new QuestionDto(extendedQuestionDtos, question.getQuesttionPrompt(), question.getQuestionDescription(),
                                        question.getQFlag(), question.getQuestionSequence());
                            })
                            .toList();
                    return new QuestionSectionDto(questionDtos, section.getSectionName(), section.getSectionSequence());
                })
                .toList();
        return new QuestionnaireResponse(sectionDtos);
    }
}
