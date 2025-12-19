package com.example.cardservice.model;

import com.example.cardservice.enums.StatusKartice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ime;
    private String prezime;
    private String oib;
    @Enumerated(EnumType.STRING)
    private StatusKartice statusKartice;

}
