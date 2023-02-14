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
@Table(name = "QUESTION_BANK")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Questionnaire {
    @Id
    @Column(name = "NAME")
    private String sectionID;
}
