package com.altf4omni.omnicmmc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "QUESTIONAIRE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Questionaire {

    @Id
    @Column(name = "QUESTION_NUM")
    private Integer questionNumber;
    @Column(name = "QUESTION")
    private String question;
    @Column(name = "QUESTION_ANSWER")
    private String questionAnswer;
}
