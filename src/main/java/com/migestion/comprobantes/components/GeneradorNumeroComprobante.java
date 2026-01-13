package com.migestion.comprobantes.components;


import com.migestion.comprobantes.model.TipoComprobante;
import com.migestion.comprobantes.repository.ComprobanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeneradorNumeroComprobante {

    private final ComprobanteRepository repository;

    public String generar(TipoComprobante tipo) {
        long next = repository.countByTipo(tipo) + 1;
        String prefix = tipo == TipoComprobante.RECIBO ? "R" : "RM";
        return prefix + "-" + String.format("%06d", next);
    }
}
