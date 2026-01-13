package com.migestion.model;

import com.migestion.comprobantes.model.TipoCondicionIva;
import jakarta.persistence.*;

@Entity
@Table(name = "configuraciones")
public class Configuración {

    @Id
    private Long id = 1L;
    private String nombreFantasiaEmpresa;
    private String direccionEmpresa;
    private String numeroTelefono;
    private String Cuit;
    private String RazonSocialEmpresa;
    private String email;
    private String condicionIva;
    private String localidad;
    private String provincia;

    public Configuración() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreFantasiaEmpresa() {
        return nombreFantasiaEmpresa;
    }

    public void setNombreFantasiaEmpresa(String nombreFantasiaEmpresa) {
        this.nombreFantasiaEmpresa = nombreFantasiaEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getCuit() {
        return Cuit;
    }

    public void setCuit(String cuit) {
        Cuit = cuit;
    }

    public String getRazonSocialEmpresa() {
        return RazonSocialEmpresa;
    }

    public void setRazonSocialEmpresa(String razonSocialEmpresa) {
        RazonSocialEmpresa = razonSocialEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCondicionIva() {
        return condicionIva;
    }

    public void setCondicionIva(String condicionIva) {
        this.condicionIva = condicionIva;
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
}
