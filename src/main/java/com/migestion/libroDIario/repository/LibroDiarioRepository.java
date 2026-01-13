package com.migestion.libroDIario.repository;

import com.migestion.libroDIario.model.LibroDiarioNota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LibroDiarioRepository extends JpaRepository<LibroDiarioNota, Long> {

    List<LibroDiarioNota> findByFechaBetween(LocalDate start, LocalDate end);

    List<LibroDiarioNota> findAllByOrderByFechaAsc();
}
