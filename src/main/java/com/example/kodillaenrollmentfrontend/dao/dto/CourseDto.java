package com.example.kodillaenrollmentfrontend.dao.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class CourseDto {

    private Long id;
    private String title;
    private List<TeacherDto> assignedTeachers;
    private LocalDate startingDate;
    private LocalDate endDate;
    private int pricePerMonth;
    private String description;
    private int duration;
    private String day;
    private LocalTime time;


}
