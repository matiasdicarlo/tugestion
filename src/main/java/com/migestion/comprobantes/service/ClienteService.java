package com.migestion.comprobantes.service;

import com.migestion.comprobantes.dto.ClienteRequestDTO;
import com.migestion.comprobantes.dto.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {

    ClienteResponseDTO create(ClienteRequestDTO dto);

    ClienteResponseDTO update(Long id, ClienteRequestDTO dto);

    ClienteResponseDTO get(Long id);

    public ClienteResponseDTO getByNombre(String nombre);

    List<ClienteResponseDTO> list();

    void logicalDelete(Long id);
    public Integer cantidadClientes();
}