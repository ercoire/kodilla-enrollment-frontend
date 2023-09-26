package com.example.kodillaenrollmentfrontend.dao.apiclient;

import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.example.kodillaenrollmentfrontend.dao.dto.PaymentDto;
import com.example.kodillaenrollmentfrontend.dao.dto.StudentDto;
import com.example.kodillaenrollmentfrontend.dao.dto.WsdcInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

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

    public StudentDto updateStudent(StudentDto dto) {
        return webClient.put().uri("/v1/students/{studentId}", dto.getId())
                .bodyValue(dto)
                .retrieve()
                .toEntity(StudentDto.class).block().getBody();
    }

    public List<StudentDto> getStudents() {
        return webClient.get().uri("/v1/students")
                .retrieve()
                .toEntityList(StudentDto.class).block().getBody();
    }

    public StudentDto getStudent(Long studentId) {
        return webClient.get().uri("/v1/students/{studentId}", studentId)
                .retrieve()
                .toEntity(StudentDto.class).block().getBody();
    }

    public void deleteStudent(Long studentId) {
        webClient.delete().uri("/v1/students/{studentId}", studentId)
                .retrieve()
                .toBodilessEntity().block();
    }

    public List<CourseDto> getCoursesByStudentId(Long studentId) {
        return webClient.get().uri("/v1/students/{studentId}/courses", studentId)
                .retrieve()
                .toEntityList(CourseDto.class).block().getBody();
    }

    public List<PaymentDto> getPaymentsByStudent(Long studentId) {
        return webClient.get().uri("/v1/students/{studentId}/payments", studentId)
                .retrieve()
                .toEntityList(PaymentDto.class).block().getBody();
    }

    public Optional<WsdcInfoDto> getWsdcInfo(Long studentId) {
        return Optional.ofNullable(webClient.get().uri("/v1/students/{studentId}/wsdc-info", studentId)
                .retrieve()
                .toEntity(WsdcInfoDto.class).block().getBody());
    }
}
