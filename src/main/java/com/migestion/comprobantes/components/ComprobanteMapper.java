package com.migestion.comprobantes.components;



import com.migestion.comprobantes.dto.*;
import com.migestion.comprobantes.model.Comprobante;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ComprobanteMapper {

    public ComprobanteResponseDTO toDTO(Comprobante c) {
        ComprobanteResponseDTO dto = new ComprobanteResponseDTO();
        dto.setId(c.getId());
        dto.setNumero(c.getNumero());
        dto.setTipo(c.getTipo().name());
        dto.setClienteId(c.getCliente().getId());
        dto.setClienteNombre(c.getCliente().getNombreFantasia());
        dto.setFecha(c.getFecha());
        dto.setTotal(c.getTotal());
        dto.setObservaciones(c.getObservaciones());

        dto.setItems(
                c.getItems().stream()
                        .map(i -> {
                            ComprobanteItemResponseDTO d = new ComprobanteItemResponseDTO();
                            d.setDescripcion(i.getDescripcion());
                            d.setCantidad(i.getCantidad().doubleValue());
                            d.setPrecioUnitario(i.getPrecioUnitario());
                            d.setSubtotal(i.getSubtotal());
                            return d;
                        })
                        .collect(Collectors.toList())
        );

        return dto;
    }
}
