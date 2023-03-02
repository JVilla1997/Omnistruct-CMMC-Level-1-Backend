package com.altf4omni.omnicmmc.repository;

import com.altf4omni.omnicmmc.entity.Question;
import com.altf4omni.omnicmmc.entity.QuestionSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
//    /**
//     * This method gets all Questions given a section ID
//     * @param sectionID Foreign key to identify question section
//     * @return List of {@link Question}
//     */
//    List<Question> findAllBySectionID(Integer sectionID);
}
