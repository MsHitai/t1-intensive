package com.t1.intensive.mapper;

import com.t1.intensive.model.dto.ClientDto;
import com.t1.intensive.model.dto.ClientShortDto;
import com.t1.intensive.model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "blockedWhom", ignore = true)
    @Mapping(target = "blockedFor", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    Client toEntity(ClientShortDto dto);

    ClientDto toClientDto(Client client);

    @Mapping(target = "accounts", ignore = true)
    Client toClientEntity(ClientDto dto);

    List<ClientShortDto> toClientDtoList(List<Client> clients);

    ClientShortDto toShortDto(Client client);
}
