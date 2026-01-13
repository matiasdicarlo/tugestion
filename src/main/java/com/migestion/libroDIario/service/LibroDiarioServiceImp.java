package com.migestion.libroDIario.service;

import com.migestion.libroDIario.dto.LibroDiarioCreateDTO;
import com.migestion.libroDIario.dto.LibroDiarioResponseDTO;
import com.migestion.libroDIario.mapper.LibroDiarioMapper;
import com.migestion.libroDIario.model.LibroDiarioNota;
import com.migestion.libroDIario.repository.LibroDiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LibroDiarioServiceImp implements LibroDiarioService {

    private final LibroDiarioRepository repository;
    private final LibroDiarioMapper mapper;

    @Autowired
    public LibroDiarioServiceImp(LibroDiarioRepository repository, LibroDiarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public LibroDiarioResponseDTO create(LibroDiarioCreateDTO dto, String usuario) {
        LibroDiarioNota nota = new LibroDiarioNota();
        nota.setFecha(dto.getFecha());
        nota.setDescripcion(dto.getDescripcion());
        repository.save(nota);
        return mapper.toDTO(nota);
    }

    @Override
    public List<LibroDiarioResponseDTO> list(LocalDate start, LocalDate end) {
        return repository.findByFechaBetween(start, end)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public List<LibroDiarioResponseDTO> listAll() {
        return repository.findAllByOrderByFechaAsc()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}
