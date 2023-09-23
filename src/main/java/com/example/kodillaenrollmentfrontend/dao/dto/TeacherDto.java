package com.example.kodillaenrollmentfrontend.dao.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class TeacherDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String description;


    public String getName() {
        return firstname + " " + lastname;
    }
}
