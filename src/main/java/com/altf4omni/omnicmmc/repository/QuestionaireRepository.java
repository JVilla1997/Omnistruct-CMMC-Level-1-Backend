package com.altf4omni.omnicmmc.repository;

import com.altf4omni.omnicmmc.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionaireRepository extends JpaRepository<Questions, String> {

}