package com.altf4omni.omnicmmc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

//
//@Entity
//@Table(name = "Person")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

//    @Id
//    @Column(name = "LICENSE")
    private String question;

    private Integer questionNum;

}
