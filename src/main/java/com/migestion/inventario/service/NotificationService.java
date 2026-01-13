package com.migestion.inventario.service;

import com.migestion.inventario.model.Item;
import com.migestion.inventario.model.Notificacion;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    public void notificarStockMinimo(Item item);
    public void notificarStockMaximo(Item item);

    public Notificacion marcarComoLeida(Notificacion n);
    public List<Notificacion> listarNoLeidas();

    public Notificacion obtenerNotificacion(long id);
}
