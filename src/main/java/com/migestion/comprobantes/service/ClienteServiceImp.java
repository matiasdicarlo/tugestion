package com.migestion.comprobantes.service;

import com.migestion.comprobantes.components.ClienteMapper;
import com.migestion.comprobantes.dto.ClienteRequestDTO;
import com.migestion.comprobantes.dto.ClienteResponseDTO;
import com.migestion.comprobantes.model.Cliente;
import com.migestion.comprobantes.repository.ClienteRepository;
import com.migestion.comprobantes.util.CuitValidator;
import com.migestion.exception.ComprobantesException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImp implements ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    @Autowired
    public ClienteServiceImp(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ClienteResponseDTO create(ClienteRequestDTO dto) {
        if (repository.existsByCuit(dto.getCuit())) {
            throw new ComprobantesException("Ya existe un cliente con ese CUIT.");
        }
        if (!CuitValidator.validarCUIT(dto.getCuit())) {
            throw new ComprobantesException("El CUIT es inválido.");
        }

        Cliente c = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(c));
    }

    @Override
    public ClienteResponseDTO update(Long id, ClienteRequestDTO dto) {
        Cliente c = repository.findById(id)
                .orElseThrow(() -> new ComprobantesException("Cliente no encontrado"));

        if (!CuitValidator.validarCUIT(dto.getCuit())) {
            throw new ComprobantesException("El CUIT es inválido.");
        }
        mapper.updateEntity(c, dto);
        return mapper.toDTO(repository.save(c));
    }

    @Override
    public ClienteResponseDTO get(Long id) {
        Cliente c = repository.findById(id)
                .orElseThrow(() -> new ComprobantesException("Cliente no encontrado"));
        return mapper.toDTO(c);
    }

    @Override
    public ClienteResponseDTO getByNombre(String nombre) {
        Cliente c = repository.findByNombreFantasia(nombre);
        return mapper.toDTO(c);
    }


    @Override
    public List<ClienteResponseDTO> list() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void logicalDelete(Long id) {
        Cliente c = repository.findById(id)
                .orElseThrow(() -> new ComprobantesException("Cliente no encontrado"));
        c.setActivo(false);
        repository.save(c);
    }

    @Override
    public Integer cantidadClientes(){
        List<Cliente> clientes= repository.findAll();
        return clientes.size();
    }
}