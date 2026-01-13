package com.migestion.comprobantes.repository;

import com.migestion.comprobantes.model.Comprobante;
import com.migestion.comprobantes.model.TipoComprobante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {
    long countByTipo(TipoComprobante tipo);
}
