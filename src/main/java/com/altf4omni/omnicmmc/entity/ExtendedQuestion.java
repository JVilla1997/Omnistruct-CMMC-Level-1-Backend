package com.altf4omni.omnicmmc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "EXTENDED_QUESTION")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedQuestion {

    @Id
    @Column(name = "EXTND_QUESTION_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer extndQuestionID;

    @Column(name = "EXTND_PROMPT")
    private String extndPrompt;

    @Column(name = "EXTND_DESCRIPTION")
    private String extndDescription;

    @Column(name = "EXTND_SEQUENCE")
    private Integer extndSequence;

    @Column(name = "QUESTION_ID")
    private Integer questionID;
}
