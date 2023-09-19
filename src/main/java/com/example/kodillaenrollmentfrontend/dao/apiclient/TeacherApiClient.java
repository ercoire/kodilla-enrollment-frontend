package com.example.kodillaenrollmentfrontend.dao.apiclient;

import com.example.kodillaenrollmentfrontend.dao.dto.CourseDto;
import com.example.kodillaenrollmentfrontend.dao.dto.StudentDto;
import com.example.kodillaenrollmentfrontend.dao.dto.TeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class TeacherApiClient {
    @Autowired
    private WebClient webClient;

    public List<TeacherDto> getTeachers() {
        return webClient.get().uri("/v1/teachers")
                .retrieve()
                .toEntityList(TeacherDto.class).block().getBody();
    }

    public TeacherDto getTeacher() {
        return webClient.get().uri("/v1/teachers/{teacherId}")
                .retrieve()
                .toEntity(TeacherDto.class).block().getBody();
    }

    public void createTeacher(TeacherDto teacherDto) {
        webClient.post().uri("/v1/teachers")
                .bodyValue(teacherDto)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity().block();
    }

    public TeacherDto updateTeacher() {
        return webClient.put().uri("/v1/teachers/{teacherId}")
                .retrieve()
                .toEntity(TeacherDto.class).block().getBody();
    }

    public void deleteTeacher() {
        webClient.delete().uri("/v1/teachers/{teacherId}")
                .retrieve()
                .toBodilessEntity().block();
    }

    public List<CourseDto> getCoursesByTeacherId() {
        return webClient.get().uri("/v1/teachers/{teacherId}/courses")
                .retrieve()
                .toEntityList(CourseDto.class).block().getBody();
    }
}
