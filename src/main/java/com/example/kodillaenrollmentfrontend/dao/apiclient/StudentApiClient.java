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
public class StudentApiClient {

    @Autowired
    WebClient webClient;

    public void createStudent(StudentDto dto) {
        webClient.post().uri("/v1/students")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity().block();
    }

    public StudentDto updateStudent() {
        return webClient.put().uri("/v1/students/{studentId}")
                .retrieve()
                .toEntity(StudentDto.class).block().getBody();
    }

    public List<StudentDto> getStudents() {
        return webClient.get().uri("/v1/students")
                .retrieve()
                .toEntityList(StudentDto.class).block().getBody();
    }

    public StudentDto getStudent() {
        return webClient.get().uri("/v1/students/{studentId}")
                .retrieve()
                .toEntity(StudentDto.class).block().getBody();
    }

    public void deleteStudent() {
        webClient.delete().uri("/v1/students/{studentId}")
                .retrieve()
                .toBodilessEntity().block();
    }

    public List<CourseDto> getCoursesByStudentId() {
        return webClient.get().uri("/v1/students/{studentId}/courses")
                .retrieve()
                .toEntityList(CourseDto.class).block().getBody();
    }

    public List<PaymentDto> getPaymentsByStudent() {
        return webClient.get().uri("/v1/students/{studentId}/payments")
                .retrieve()
                .toEntityList(PaymentDto.class).block().getBody();
    }

}
