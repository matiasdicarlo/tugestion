package com.migestion.comprobantes.dto;



import com.migestion.comprobantes.model.TipoComprobante;

import java.util.List;


public class ComprobanteRequestDTO {
    private Long clienteId;
    private TipoComprobante tipo;
    private List<ComprobanteItemRequestDTO> items;

    private String observaciones;

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public TipoComprobante getTipo() {
        return tipo;
    }

    public void setTipo(TipoComprobante tipo) {
        this.tipo = tipo;
    }

    public List<ComprobanteItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<ComprobanteItemRequestDTO> items) {
        this.items = items;
    }
}
