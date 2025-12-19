package com.example.cardservice.service;

import com.example.cardservice.dto.CardCreationRequest;
import com.example.cardservice.dto.ClientDTO;
import com.example.cardservice.enums.StatusKartice;
import com.example.cardservice.exceptions.custom.ResourceAlreadyExistsException;
import com.example.cardservice.exceptions.custom.ResourceNotFoundException;
import com.example.cardservice.mapper.ClientMapper;
import com.example.cardservice.model.Client;
import com.example.cardservice.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;
    private final RestTemplate restTemplate;

    private static final String url = "https://api.something.com/v1/api/v1/card-request";

    public ClientDTO saveClient(ClientDTO clientDTO){

        if (repository.existsByOib(clientDTO.getOib())){
            throw new ResourceAlreadyExistsException("Client already exist with OIB: " + clientDTO.getOib());
        }

        Client client = mapper.toEntity(clientDTO);
        Client saved = repository.save(client);
        return mapper.toDto(saved);
    }

    public ClientDTO getClient(String oib){
        return mapper.toDto(findClientByOIB(oib));
    }

    public void deleteClient(String oib){
        repository.delete(findClientByOIB(oib));
    }

    public void sendClientToExternalApi(String oib){

        Client client = findClientByOIB(oib);

        CardCreationRequest request = new CardCreationRequest();
        request.setIme(client.getIme());
        request.setPrezime(client.getPrezime());
        request.setOib(client.getOib());
        request.setStatus(String.valueOf(client.getStatusKartice()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CardCreationRequest> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                Void.class
        );

        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("Card creation failed");
        }
    }

    public void updateStatus(String oib, StatusKartice status) {

        Client client = findClientByOIB(oib);
        client.setStatusKartice(status);
        repository.save(client);
    }

    private Client findClientByOIB(String oib){
        Client client = repository.findByOib(oib)
                .orElseThrow(() -> new ResourceNotFoundException("Client with OIB: " + oib + " not found!"));
        return client;
    }

}
