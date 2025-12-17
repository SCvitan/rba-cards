package com.example.cardservice.mapper;

import com.example.cardservice.dto.ClientDTO;
import com.example.cardservice.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientDTO dto);
    ClientDTO toDto(Client client);
}
