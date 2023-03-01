package com.altf4omni.omnicmmc.service;

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
    public QuestionnaireResponse getQuestionnaire() {
        /*
        var sections = questionSectionRepository.findAll().stream()
                .map()
        */
        /*
        1. Stream through sections
        2. For each section map to only the ID of the section
        3. For each section ID return list of questions
        4. Collect list of questions into a list
         */
        /*
        var question = sections.stream()
                .map(section -> section.getSectionID() )
                .map(id -> questionRepository.findAllBySectionID(id))
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Question::getSectionID));
        */
        /*
        1. Stream through questions
        2. For each question map to only the ID of the question
        3. For each question ID return list of the extended questions
        4. Collect list of extended questions into a list
        */
        return null;
    }
}
