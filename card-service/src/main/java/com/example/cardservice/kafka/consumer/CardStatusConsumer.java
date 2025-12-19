package com.example.cardservice.kafka.consumer;

import com.example.cardservice.dto.CardStatusResponse;
import com.example.cardservice.enums.StatusKartice;
import com.example.cardservice.exceptions.custom.ResourceNotFoundException;
import com.example.cardservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CardStatusConsumer {

    private final ClientService clientService;

    @KafkaListener(topics = "cardTopic", groupId = "card-service")
    public void consume(CardStatusResponse message) {

        log.info("Card status updated: {}", message.getStatus());

        try {
            clientService.updateStatus(
                    message.getOib(),
                    StatusKartice.valueOf(message.getStatus())
            );
        } catch (ResourceNotFoundException ex) {
            log.warn(
                    "Client with OIB {} was not found.",
                    message.getOib()
            );
        } catch (Exception ex) {
            log.error("Unexpected error", ex);
        }
    }
}
