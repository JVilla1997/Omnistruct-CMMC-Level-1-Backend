package com.altf4omni.omnicmmc.repository;

import com.altf4omni.omnicmmc.entity.ExtendedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtendedQuestionRepository extends JpaRepository<ExtendedQuestion, Integer> {
    /**
     * This method gets all ExtendedQuestions given a question ID
     * @param promptID Foreign key to identify a question
     * @return List of {@link ExtendedQuestion}
     */
    List<ExtendedQuestion> findAllByQuestionID(Integer questionID);
}
