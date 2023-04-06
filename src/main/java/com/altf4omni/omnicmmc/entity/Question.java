package com.altf4omni.omnicmmc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "QUESTION_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer questionID;

    /**
     * This column will contain the actual prompt of each question
     */
    @Column(name = "QUESTION_PROMPT")
    private String questionPrompt;

    /**
     * This column
     */
    @Column(name = "QUESTION_DESCRIPTION")
    private String questionDescription;

    /**
     * Foreign key that maps each question to the correct section
     */
    @Column(name = "QUESTION_SEQUENCE")
    private Integer questionSequence;

    /**
     * TODO: add description
     */
    @Column(name = "QUESTION_FLAG")
    private Boolean qFlag;

    @OneToMany
    @JoinColumn(name = "QUESTION_ID")
    private List<ExtendedQuestion> extndQuestion;
}
