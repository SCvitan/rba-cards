package com.example.cardservice.kafka.consumer;

import com.example.cardservice.dto.CardStatusResponse;
import com.example.cardservice.enums.StatusKartice;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardStatusProducer {

    private final KafkaTemplate<String, CardStatusResponse> kafkaTemplate;

    private static final String TOPIC = "cardTopic";

    public void sendStatus(String oib, StatusKartice status) {

        CardStatusResponse message =
                new CardStatusResponse(oib, status.name());

        kafkaTemplate.send(TOPIC, oib, message);
    }
}
