package com.migestion.libroDIario.mapper;

import com.migestion.libroDIario.dto.LibroDiarioResponseDTO;
import com.migestion.libroDIario.model.LibroDiarioNota;
import org.springframework.stereotype.Component;

@Component
public class LibroDiarioMapper {

    public LibroDiarioResponseDTO toDTO(LibroDiarioNota n) {
        LibroDiarioResponseDTO dto = new LibroDiarioResponseDTO();
        dto.setId(n.getId());
        dto.setFecha(n.getFecha());
        dto.setDescripcion(n.getDescripcion());
        dto.setCreatedAt(n.getCreatedAt());
        return dto;
    }
}
