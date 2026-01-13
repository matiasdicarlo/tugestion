package com.migestion.service;

import com.migestion.dto.ConfiguracionDTO;
import com.migestion.model.Configuración;

public interface ConfigService {

    public ConfiguracionDTO saveOrUpdate(ConfiguracionDTO dto);
    public ConfiguracionDTO getConfiguracion();
}
