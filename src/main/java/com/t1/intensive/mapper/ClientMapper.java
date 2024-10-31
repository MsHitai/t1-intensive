package com.t1.intensive.mapper;

import com.t1.intensive.model.dto.ClientDto;
import com.t1.intensive.model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto toClientDto(Client client);

    @Mapping(target = "accounts", ignore = true)
    Client toClientEntity(ClientDto dto);
}
