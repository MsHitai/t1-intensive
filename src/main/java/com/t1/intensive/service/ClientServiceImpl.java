package com.t1.intensive.service;

import com.t1.intensive.exception.DataNotFoundException;
import com.t1.intensive.mapper.ClientMapper;
import com.t1.intensive.model.dto.ClientDto;
import com.t1.intensive.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    @Override
    @Transactional(readOnly = true)
    public ClientDto getClientById(Long id) {
        return clientMapper.toClientDto(clientRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Client was not found")));
    }
}
