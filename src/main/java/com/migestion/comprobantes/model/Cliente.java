package com.migestion.comprobantes.model;


import jakarta.persistence.*;


@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Datos identidad
    private String razonSocial;
    private String nombreFantasia;

    // Datos legales
    private String cuit;

    @Enumerated(EnumType.STRING)
    private TipoCondicionIva condicionIva;    // RESPONSABLE_INSCRIPTO / MONOTRIBUTO / EXENTO / CONSUMIDOR_FINAL

    // Contacto
    private String direccion;
    private String localidad;
    private String provincia;
    private String telefono;
    private String email;

    private Boolean activo = true;

    private String ingresosBrutos;
    private String categoriaIIBB;
    private String inicioActividades;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
