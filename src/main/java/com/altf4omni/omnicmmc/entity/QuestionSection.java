package com.altf4omni.omnicmmc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "QUESTION_SECTION")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSection {
    /**
     * Primary key to identify each individual section within the questionnaire
     */
    @Id
    @Column(name = "QUESTION_ID")
    private Integer sectionID;
    /**
     * Attribute to identify each question section's name
     */
    @Column(name = "SECTION_NAME")
    private String sectionName;
    /**
     * This attribute allows us to navigate through a list of Questions within a specific section
     */
    @OneToMany
    private List<Question> questions;
}
