package service

import com.altf4omni.omnicmmc.dto.QuestionCreationRequest
import com.altf4omni.omnicmmc.entity.Question
import com.altf4omni.omnicmmc.repository.QuestionaireRepository
import com.altf4omni.omnicmmc.service.QuestionaireService
import spock.lang.Specification

class QuestionaireServiceSpec extends Specification {
    //No semi-colon, skrrr skrrr
    //'Def' groovy is smart enough to figure out the type of variable, groovy is a good boy
    def questionaireRepository = Mock(QuestionaireRepository)
    //This is telling us that we are going to be testing over Questionaire service.
    def sut = new QuestionaireService(questionaireRepository)
    //This will be our method name
    def "Is create question method actually creating a method? Sussy >_>"() {
        given: "A question, duh"
        def questionRequest =
                new QuestionCreationRequest(questionName: "Why am I so Lazy??", questionAnswer: "Just git gud boii")
        def questionEntity = Question.builder().prompt("Why am I so Lazy??").build()
        when: "Create question is called"
        def response = sut.createQuestion(questionRequest)
        then: "The response sucks, but is correct"
        //Making sure response is not null, funny according to Dani. Questionable
        response
        //We are expecting our response to be this static String, else we are screwed
        response == "Question created successfully!"
        and: "The expected sussyness occur"
        1 * questionaireRepository.save(questionEntity)
        // '_' means anything, meaning nothing else happens. After this there won't be any other call
        0 * _
    }
}
