package com.altf4omni.omnicmmc.repository;

import com.altf4omni.omnicmmc.entity.QuestionSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionSectionRepository extends JpaRepository<QuestionSection, Integer> {
}
