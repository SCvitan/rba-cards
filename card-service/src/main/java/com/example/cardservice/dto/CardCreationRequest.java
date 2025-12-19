package com.example.cardservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardCreationRequest {

    private String ime;
    private String prezime;
    private String oib;
    private String status;

}
