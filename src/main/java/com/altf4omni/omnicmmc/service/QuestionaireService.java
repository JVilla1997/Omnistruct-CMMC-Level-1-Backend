package com.altf4omni.omnicmmc.service;

import com.altf4omni.omnicmmc.dto.ExtendedQuestionDto;
import com.altf4omni.omnicmmc.dto.QuestionDto;
import com.altf4omni.omnicmmc.dto.QuestionSectionDto;
import com.altf4omni.omnicmmc.dto.QuestionnaireResponse;
import com.altf4omni.omnicmmc.entity.QuestionSection;
import com.altf4omni.omnicmmc.entity.Question;
import com.altf4omni.omnicmmc.entity.ExtendedQuestion;
import com.altf4omni.omnicmmc.repository.QuestionSectionRepository;
import com.altf4omni.omnicmmc.repository.QuestionRepository;
import com.altf4omni.omnicmmc.repository.ExtendedQuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class QuestionaireService {
    private final QuestionSectionRepository questionSectionRepository;
    private final QuestionRepository questionRepository;
    private final ExtendedQuestionRepository extendedQuestionRepository;

    public QuestionaireService(QuestionSectionRepository questionSectionRepository, QuestionRepository questionRepository, ExtendedQuestionRepository extendedQuestionRepository) {
        this.questionSectionRepository = questionSectionRepository;
        this.questionRepository = questionRepository;
        this.extendedQuestionRepository = extendedQuestionRepository;
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
                                return new QuestionDto(question.getQuestionID(), extendedQuestionDtos, question.getQuestionPrompt(), question.getQuestionDescription(),
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

    public void deleteSection(Integer sectionID) {
        var sectionToDelete = new QuestionSection();
        sectionToDelete.setSectionID(sectionID);
        questionSectionRepository.delete(sectionToDelete);
    }

    public QuestionSection updateSection(QuestionSectionDto section) {
        var storedSection = questionSectionRepository.findById(section.getSectionID());
        if(!storedSection.isPresent()) {
            return null;
        }
        var updatingSection = storedSection.get();
        updatingSection.setSectionName(section.getSectionName());
        updatingSection.setSectionSequence(section.getSequenceNumber());
        questionSectionRepository.save(updatingSection);
        return updatingSection;
    }

    public Integer createQuestion(Question question) {
        var createdQuestion = questionRepository.save(question);
        return createdQuestion.getQuestionID();
    }

    public void deleteQuestion(Integer questionID) {
        var questionToDelete = new Question();
        questionToDelete.setQuestionID(questionID);
        questionRepository.delete(questionToDelete);
    }

    public Question updateQuestion(QuestionDto question) {
        var storedQuestion = questionRepository.findById(question.getQuestionID());
        if(!storedQuestion.isPresent()) {
            return null;
        }
        var updatingQuestion = storedQuestion.get();
        updatingQuestion.setQuestionPrompt(question.getPrompt());
        updatingQuestion.setQuestionSequence(question.getSequenceNumber());
        questionRepository.save(updatingQuestion);
        return updatingQuestion;
    }
}
