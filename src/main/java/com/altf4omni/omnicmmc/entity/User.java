package com.altf4omni.omnicmmc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Table(name = "USERS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "USERNAME")
    private String username;

    @Column(name="PASSWORD")
    private String password;

    @Column(name= "ENABLED")
    private boolean enabled;
}
