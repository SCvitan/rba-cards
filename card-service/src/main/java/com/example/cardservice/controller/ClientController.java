package com.example.cardservice.controller;

import com.example.cardservice.dto.ClientDTO;
import com.example.cardservice.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
@AllArgsConstructor
public class ClientController {

    private final ClientService service;

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

}
