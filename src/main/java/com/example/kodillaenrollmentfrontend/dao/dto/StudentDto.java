package com.example.kodillaenrollmentfrontend.dao.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StudentDto {
    private Long id;
    private String firstname;
    private String lastname;
    //private String email;


    @JsonIgnore
    public String getStudentName() {
        return firstname + " " + lastname;
    }
}
