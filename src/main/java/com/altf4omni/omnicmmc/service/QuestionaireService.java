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
                                        .map(extendedQuestion -> new ExtendedQuestionDto(extendedQuestion.getExtndQuestionID(), extendedQuestion.getExtndPrompt(), extendedQuestion.getExtndDescription(),
                                                extendedQuestion.getExtndSequence(), extendedQuestion.getQuestionID()))
                                        .toList();
                                return new QuestionDto(question.getQuestionID(), extendedQuestionDtos, question.getQuestionPrompt(), question.getQuestionDescription(),
                                        question.getQFlag(), question.getQuestionSequence(), question.getSectionID());
                            })
                            .toList();
                    return new QuestionSectionDto(section.getSectionID(), questionDtos, section.getSectionName(), section.getSectionSequence());
                })
                .toList();
        return new QuestionnaireResponse(sectionDtos);
    }

    /**
     * Create service for a new Section
     */
    public Integer createSection(QuestionSection section) {
        var createdSection = questionSectionRepository.save(section);
        return createdSection.getSectionID();
    }

    /**
     * Delete service for an existing Section
     */
    public boolean deleteSection(Integer sectionID) {
        var storedSection = questionSectionRepository.findById(sectionID);
        if(!storedSection.isPresent()) {
            return false;
        }
        var sectionToDelete = new QuestionSection();
        sectionToDelete.setSectionID(sectionID);
        questionSectionRepository.delete(sectionToDelete);
        return true;
    }

    /**
     * Update service for an existing Section
     */
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

    /**
     * Create service for a new question
     */
    public Integer createQuestion(Question question) {
        var createdQuestion = questionRepository.save(question);
        return createdQuestion.getQuestionID();
    }

    /**
     * Delete service for an existing question
     */
    public boolean deleteQuestion(Integer questionID) {
        var storedQuestion = questionRepository.findById(questionID);
        if(!storedQuestion.isPresent()) {
            return false;
        }
        var questionToDelete = new Question();
        questionToDelete.setQuestionID(questionID);
        questionRepository.delete(questionToDelete);
        return true;
    }

    /**
     * Update service for an existing question
     */
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

    /**
     * Create service for a new extended question
     */
    public Integer createExtendedQuestion(ExtendedQuestion extendedQuestion) {
        var createdExtendedQuestion = extendedQuestionRepository.save(extendedQuestion);
        return createdExtendedQuestion.getExtndQuestionID();
    }

    /**
     * Delete service for an existing extended question
     */
    public boolean deleteExtendedQuestion(Integer extendedQuestionID) {
        var storedExtendedQuestion = extendedQuestionRepository.findById(extendedQuestionID);
        if(!storedExtendedQuestion.isPresent()) {
            return false;
        }
        var extendedQuestionToDelete = new ExtendedQuestion();
        extendedQuestionToDelete.setExtndQuestionID(extendedQuestionID);
        extendedQuestionRepository.delete(extendedQuestionToDelete);
        return true;
    }

    /**
     * Update service for an existing extended question
     */
    public ExtendedQuestion updateExtendedQuestion(ExtendedQuestionDto extendedQuestion) {
        var storedExtendedQuestion = extendedQuestionRepository.findById(extendedQuestion.getExtendedQuestionID());
        if(!storedExtendedQuestion.isPresent()) {
            return null;
        }
        var updatingExtendedQuestion = storedExtendedQuestion.get();
        updatingExtendedQuestion.setExtndPrompt(extendedQuestion.getPrompt());
        updatingExtendedQuestion.setExtndSequence(extendedQuestion.getSequenceNumber());
        extendedQuestionRepository.save(updatingExtendedQuestion);
        return updatingExtendedQuestion;
    }
}
