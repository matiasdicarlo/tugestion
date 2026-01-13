package com.migestion.comprobantes.dto;

import com.migestion.comprobantes.model.TipoCondicionIva;

public class ClienteRequestDTO {

    private String razonSocial;
    private String nombreFantasia;
    private String cuit;
    private TipoCondicionIva condicionIva;
    private String direccion;
    private String localidad;
    private String provincia;
    private String telefono;
    private String email;

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public TipoCondicionIva getCondicionIva() {
        return condicionIva;
    }

    public void setCondicionIva(TipoCondicionIva condicionIva) {
        this.condicionIva = condicionIva;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
