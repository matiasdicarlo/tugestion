package com.migestion.libroDIario.service;

import com.migestion.libroDIario.dto.LibroDiarioCreateDTO;
import com.migestion.libroDIario.dto.LibroDiarioResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface LibroDiarioService {
    public LibroDiarioResponseDTO create(LibroDiarioCreateDTO dto, String usuario);

    public List<LibroDiarioResponseDTO> list(LocalDate start, LocalDate end);

    public List<LibroDiarioResponseDTO> listAll();
}
