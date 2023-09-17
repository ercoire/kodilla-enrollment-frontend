package com.example.kodillaenrollmentfrontend.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class PaymentDto {

    private Long id;
    private LocalDate paymentDate;
    private StudentDto studentDto;
    private int amount;
    private Long courseId;

}
