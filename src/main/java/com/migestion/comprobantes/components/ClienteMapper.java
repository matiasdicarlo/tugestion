package com.migestion.comprobantes.components;


import com.migestion.comprobantes.dto.ClienteRequestDTO;
import com.migestion.comprobantes.dto.ClienteResponseDTO;
import com.migestion.comprobantes.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequestDTO dto) {
        Cliente c = new Cliente();
        c.setRazonSocial(dto.getRazonSocial());
        c.setNombreFantasia(dto.getNombreFantasia());
        c.setCuit(dto.getCuit());
        c.setCondicionIva(dto.getCondicionIva());
        c.setDireccion(dto.getDireccion());
        c.setLocalidad(dto.getLocalidad());
        c.setProvincia(dto.getProvincia());
        c.setTelefono(dto.getTelefono());
        c.setEmail(dto.getEmail());
        c.setActivo(true);
        return c;
    }

    public void updateEntity(Cliente c, ClienteRequestDTO dto) {
        c.setRazonSocial(dto.getRazonSocial());
        c.setNombreFantasia(dto.getNombreFantasia());
        c.setCuit(dto.getCuit());
        c.setCondicionIva(dto.getCondicionIva());
        c.setDireccion(dto.getDireccion());
        c.setLocalidad(dto.getLocalidad());
        c.setProvincia(dto.getProvincia());
        c.setTelefono(dto.getTelefono());
        c.setEmail(dto.getEmail());
    }

    public ClienteResponseDTO toDTO(Cliente c) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(c.getId());
        dto.setRazonSocial(c.getRazonSocial());
        dto.setNombreFantasia(c.getNombreFantasia());
        dto.setCuit(c.getCuit());
        dto.setCondicionIva(c.getCondicionIva());
        dto.setDireccion(c.getDireccion());
        dto.setLocalidad(c.getLocalidad());
        dto.setProvincia(c.getProvincia());
        dto.setTelefono(c.getTelefono());
        dto.setEmail(c.getEmail());
        dto.setActivo(c.getActivo());
        return dto;
    }
}