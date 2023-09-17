package com.example.kodillaenrollmentfrontend.dao;

import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CourseApiClient {

    @Autowired
    private WebClient webClient;

    public List<CourseDto> getAllCourses() {
        return webClient.get().uri("/v1/courses")
                .retrieve()
                .toEntityList(CourseDto.class)
                .block().getBody();
    }

    public CourseDto getCourse(){
        return webClient.get().uri("/v1/courses/{courseId}")
                .retrieve()
                .toEntity(CourseDto.class).block().getBody();
    }


}
