package com.example.kodillaenrollmentfrontend.dao.apiclient;

import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.example.kodillaenrollmentfrontend.dao.dto.PaymentDto;
import com.example.kodillaenrollmentfrontend.dao.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    public CourseDto getCourse() {
        return webClient.get().uri("/v1/courses/{courseId}")
                .retrieve()
                .toEntity(CourseDto.class).block().getBody();
    }


    public void createCourse(CourseDto dto) {
        webClient.post().uri("/v1/courses")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity().block();
    }

    public List<StudentDto> getStudentsByCourse() {
        return webClient.get().uri("/v1/courses/{courseId}/students")
                .retrieve()
                .toEntityList(StudentDto.class)
                .block().getBody();
    }

    public CourseDto updateCourse() {
        return webClient.put().uri("/v1/courses/{courseId}")
                .retrieve()
                .toEntity(CourseDto.class).block().getBody();
    }

    public void deleteCourse() {
        webClient.delete().uri("/v1/courses/{courseId}")
                .retrieve()
                .toBodilessEntity().block();
    }

    public List<PaymentDto> getPaymentsByCourseId() {
        return webClient.get().uri("/{courseId}/payments")
                .retrieve()
                .toEntityList(PaymentDto.class)
                .block().getBody();
    }

    public void addStudentToCourse() {
        webClient.post().uri("/v1/courses/{courseId}/students/{studentId}")
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
