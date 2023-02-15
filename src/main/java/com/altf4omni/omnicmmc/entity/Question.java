package com.altf4omni.omnicmmc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "QUESTION")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    /**
     * Primary key for the question's prompt, this will help us in the future to query for a specific
     * question by only using it's ID
     */
    @Id
    @Column(name = "PROMPT_ID")
    private Integer promptID;
    /**
     * This column will contain the actual prompt of each question
     */
    @Column(name = "PROMPT")
    private String prompt;
    /**
     * Foreign key that maps each question to the correct section
     */
    @ManyToOne
    @JoinColumn(name = "QUESTION_SECTION")
    private QuestionSection sectionID;

}
