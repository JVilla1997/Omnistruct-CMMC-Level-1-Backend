package com.altf4omni.omnicmmc;

import com.altf4omni.omnicmmc.entity.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OmniCmmcApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmniCmmcApplication.class, args);
        System.out.println("Hello, World!");

        Person onePersn = Person.builder().num(123345).question("Where is the lamb sauce??").build();
    }
}
