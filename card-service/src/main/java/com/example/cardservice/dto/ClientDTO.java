package com.example.cardservice.dto;

import com.example.cardservice.enums.StatusKartice;
import lombok.*;

@Getter
@Setter
public class ClientDTO {
    
    private String ime;
    private String prezime;
    private String oib;
    private StatusKartice statusKartice;

}
