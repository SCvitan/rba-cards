package com.example.cardservice.controller;

import com.example.cardservice.dto.ClientDTO;
import com.example.cardservice.enums.StatusKartice;
import com.example.cardservice.kafka.consumer.CardStatusProducer;
import com.example.cardservice.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
@AllArgsConstructor
public class ClientController {

    private final ClientService service;
    private final CardStatusProducer producer;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO){
        return service.saveClient(clientDTO);
    }

    @GetMapping("/{oib}")
    public ClientDTO getClient(@PathVariable String oib){
        return service.getClient(oib);
    }

    @DeleteMapping("/{oib}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable String oib){
        service.deleteClient(oib);
    }

    @PostMapping("/{oib}/send")
    public ResponseEntity<Void> sendClientToExternalAPI(@PathVariable String oib){
        service.sendClientToExternalApi(oib);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/card-status")
    public ResponseEntity<Void> sendStatus(@RequestParam String oib, @RequestParam StatusKartice status) {

        producer.sendStatus(oib, status);
        return ResponseEntity.ok().build();
    }

}
