package com.example.kodillaenrollmentfrontend.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class PaymentCreationDto {

    private Long id;
    private LocalDate paymentDate;
    private Long studentId;
    private int amount;
    private Long courseId;
}
