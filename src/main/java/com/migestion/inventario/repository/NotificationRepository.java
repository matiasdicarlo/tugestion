package com.migestion.inventario.repository;

import com.migestion.inventario.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notificacion, Long> {


    List<Notificacion> findByEsLeida(boolean esLeida);
}
