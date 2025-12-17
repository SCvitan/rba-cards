package com.example.cardservice.service;

import com.example.cardservice.dto.ClientDTO;
import com.example.cardservice.enums.StatusKartice;
import com.example.cardservice.exceptions.custom.ResourceAlreadyExistsException;
import com.example.cardservice.mapper.ClientMapper;
import com.example.cardservice.model.Client;
import com.example.cardservice.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper mapper;

    public ClientDTO saveClient(ClientDTO clientDTO){

        if (repository.existsByOib(clientDTO.getOib())){
            throw new ResourceAlreadyExistsException("Client already exist with OIB: " + clientDTO.getOib());
        }

        Client client = mapper.toEntity(clientDTO);
        client.setStatusKartice(StatusKartice.PENDING);

        Client saved = repository.save(client);
        return mapper.toDto(saved);
    }

}
