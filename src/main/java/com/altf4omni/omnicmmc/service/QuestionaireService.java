package com.altf4omni.omnicmmc.service;

import com.altf4omni.omnicmmc.dto.ExtendedQuestionDto;
import com.altf4omni.omnicmmc.dto.QuestionDto;
import com.altf4omni.omnicmmc.dto.QuestionSectionDto;
import com.altf4omni.omnicmmc.dto.QuestionnaireResponse;
import com.altf4omni.omnicmmc.entity.QuestionSection;
import com.altf4omni.omnicmmc.repository.QuestionSectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class QuestionaireService {
    private final QuestionSectionRepository questionSectionRepository;

    public QuestionaireService(QuestionSectionRepository questionSectionRepository) {
        this.questionSectionRepository = questionSectionRepository;
    }

    /**
     * Access {@link QuestionSectionRepository} and find all sections. Then for every section get all of the questions based
     * on the sectionID. Then for every question get all of the extended questions based on the questionID. Finally, build a questionnaire
     * response.
     *
     * @return {@link QuestionnaireResponse}
     */
    public QuestionnaireResponse getQuestionnaire() {
        var sections = questionSectionRepository.findAll();

        if (CollectionUtils.isEmpty(sections)) {
            return new QuestionnaireResponse();
        }

        var sectionDtos = sections.stream()
                .map(section -> {
                    var questionDtos = section.getQuestions().stream()
                            .map(question -> {
                                var extendedQuestionDtos = question.getExtndQuestion().stream()
                                        .map(extendedQuestion -> new ExtendedQuestionDto(extendedQuestion.getExtndPrompt(), extendedQuestion.getExtndDescription(),
                                                extendedQuestion.getExtndSequence()))
                                        .toList();
                                return new QuestionDto(extendedQuestionDtos, question.getQuestionPrompt(), question.getQuestionDescription(),
                                        question.getQFlag(), question.getQuestionSequence());
                            })
                            .toList();
                    return new QuestionSectionDto(section.getSectionID(), questionDtos, section.getSectionName(), section.getSectionSequence());
                })
                .toList();
        return new QuestionnaireResponse(sectionDtos);
    }

    public Integer createSection(QuestionSection section) {
        var createdSection = questionSectionRepository.save(section);
        return createdSection.getSectionID();
    }
}
