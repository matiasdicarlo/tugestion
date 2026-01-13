package com.migestion.service;


import com.migestion.dto.ConfiguracionDTO;
import com.migestion.model.Configuración;
import com.migestion.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ConfigServiceImp implements ConfigService{

    private final ConfigRepository repository;

    @Autowired
    public ConfigServiceImp(ConfigRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConfiguracionDTO saveOrUpdate(ConfiguracionDTO dto) {

        Configuración config = repository.findById(1L)
                .orElse(new Configuración());

        config.setId(1L);
        config.setCuit(dto.getCuit());
        config.setCondicionIva(dto.getCondicionIva());
        config.setEmail(dto.getEmail());
        config.setLocalidad(dto.getLocalidad());
        config.setProvincia(dto.getProvincia());
        config.setDireccionEmpresa(dto.getDireccion());
        config.setNumeroTelefono(dto.getTelefono());
        config.setRazonSocialEmpresa(dto.getRazonSocial());
        config.setNombreFantasiaEmpresa(dto.getNombreFantasia());

        repository.save(config);

        return toDTO(config);
    }

    @Override
    public ConfiguracionDTO getConfiguracion() {
        Configuración config = repository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Configuración no creada"));

        return toDTO(config);
    }

    private ConfiguracionDTO toDTO(Configuración c) {
        ConfiguracionDTO dto = new ConfiguracionDTO();
        dto.setCuit(c.getCuit());
        dto.setCondicionIva(c.getCondicionIva());
        dto.setEmail(c.getEmail());
        dto.setLocalidad(c.getLocalidad());
        dto.setProvincia(c.getProvincia());
        dto.setDireccion(c.getDireccionEmpresa());
        dto.setTelefono(c.getNumeroTelefono());
        dto.setRazonSocial(c.getRazonSocialEmpresa());
        dto.setNombreFantasia(c.getNombreFantasiaEmpresa());
        return dto;
    }
}

