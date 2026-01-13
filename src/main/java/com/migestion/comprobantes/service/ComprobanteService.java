package com.migestion.comprobantes.service;


import com.migestion.comprobantes.dto.ComprobanteRequestDTO;
import com.migestion.comprobantes.dto.ComprobanteResponseDTO;

import java.util.List;

public interface ComprobanteService {

    ComprobanteResponseDTO crear(ComprobanteRequestDTO request);

    List<ComprobanteResponseDTO> listar();

    ComprobanteResponseDTO obtener(Long id);
}
