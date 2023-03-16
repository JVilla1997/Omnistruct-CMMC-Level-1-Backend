package service

import com.altf4omni.omnicmmc.dto.QuestionnaireResponse
import com.altf4omni.omnicmmc.entity.ExtendedQuestion
import com.altf4omni.omnicmmc.entity.Question
import com.altf4omni.omnicmmc.entity.QuestionSection
import com.altf4omni.omnicmmc.repository.QuestionSectionRepository
import com.altf4omni.omnicmmc.service.QuestionaireService
import spock.lang.Specification

class QuestionaireServiceSpec extends Specification {
    def questionaireSectionRepository = Mock(QuestionSectionRepository)
    def sut = new QuestionaireService(questionaireSectionRepository)

    def "getQuestionnaire is successful"() {
        when: "getQuestionnaire is called"
        def response = sut.getQuestionnaire()
        then: "It should return a questionnaire with the right hierarchy"
        response
        response.sections.size() == 1
        response.sections[0].sectionName == 'Hello World'
        response.sections[0].sequenceNumber == 1
        response.sections[0].questions.size() == 1
        response.sections[0].questions[0].prompt == 'Hello World'
        response.sections[0].questions[0].description == 'Bye World'
        response.sections[0].questions[0].sequenceNumber == 1
        response.sections[0].questions[0].flag
        response.sections[0].questions[0].extendedQuestion.size() == 1
        response.sections[0].questions[0].extendedQuestion[0].prompt == 'Hello World extended'
        response.sections[0].questions[0].extendedQuestion[0].description == 'Bye World extended'
        response.sections[0].questions[0].extendedQuestion[0].sequenceNumber == 1

        and: "The expected interactions occur"
        1 * questionaireSectionRepository.findAll() >> [new QuestionSection(1, 'Hello World',
                1, [new Question(1, "Hello World", "Bye World", 1, Boolean.TRUE
                , [new ExtendedQuestion(1, "Hello World extended", "Bye World extended", 1)])])]
        0 * _
    }
        def "Get questionnaire errors if repository fails"() {
            when: "getQuestionnaire is called"
            def response = sut.getQuestionnaire()
            then: "It should return a questionnaire with the right hierarchy"
            response == new QuestionnaireResponse()

            and: "The expected interactions occur"
            1 * questionaireSectionRepository.findAll() >> []
            0 * _
    }

    def "Should not return npe if a question does not have extended quesitons"() {
        when: "getQuestionnaire is called"
        def response = sut.getQuestionnaire()
        then: "It should return a questionnaire with the right hierarchy"
        response
        response.sections.size() == 1
        response.sections[0].sectionName == 'Hello World'
        response.sections[0].sequenceNumber == 1
        response.sections[0].questions.size() == 1
        response.sections[0].questions[0].prompt == 'Hello World'
        response.sections[0].questions[0].description == 'Bye World'
        response.sections[0].questions[0].sequenceNumber == 1
        response.sections[0].questions[0].flag
        response.sections[0].questions[0].extendedQuestion.size() == 0
        and: "No null pointer exception should be thrown"
        noExceptionThrown()

        and: "The expected interactions occur"
        1 * questionaireSectionRepository.findAll() >> [
                new QuestionSection(1, 'Hello World',
                1, [
                        new Question(1, "Hello World", "Bye World", 1, Boolean.TRUE, [])])
                 ]
        0 * _
    }

    def "Should not return npe if a section does not have questions"() {
        when: "getQuestionnaire is called"
        def response = sut.getQuestionnaire()
        then: "It should return a questionnaire with the right hierarchy"
        response
        response.sections.size() == 1
        response.sections[0].sectionName == 'Hello World'
        response.sections[0].sequenceNumber == 1
        response.sections[0].questions.size() == 0
        and: "No null pointer exception should be thrown"
        noExceptionThrown()

        and: "The expected interactions occur"
        1 * questionaireSectionRepository.findAll() >> [
                new QuestionSection(1, 'Hello World', 1, [])
        ]
        0 * _
    }

}
