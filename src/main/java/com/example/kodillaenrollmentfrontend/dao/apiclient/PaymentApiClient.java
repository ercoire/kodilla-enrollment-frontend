package com.example.kodillaenrollmentfrontend.dao.apiclient;

import com.example.kodillaenrollmentfrontend.dao.dto.PaymentCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PaymentApiClient {

    @Autowired
    WebClient webClient;

    public void createPayment(PaymentCreationDto dto) {
        webClient.post().uri("/v1/payments")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity().block();
    }
}
