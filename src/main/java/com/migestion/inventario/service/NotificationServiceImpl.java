package com.migestion.inventario.service;

import com.migestion.exception.InventarioException;
import com.migestion.inventario.model.Item;
import com.migestion.inventario.model.Notificacion;
import com.migestion.inventario.model.NotificationType;
import com.migestion.inventario.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {


    private NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository repo) {
        this.notificationRepository = repo;
    }
    @Override
    public void notificarStockMinimo(Item item) {
        Notificacion n = new Notificacion();
        n.setMensaje("El producto " + item.getNombre() + " alcanzó su stock mínimo.");
        n.setTipo(NotificationType.ALERTA);
        notificationRepository.save(n);
    }
    @Override
    public void notificarStockMaximo(Item item) {
        Notificacion n = new Notificacion();
        n.setMensaje("El producto " + item.getNombre() + " alcanzó su stock máximo.");
        n.setTipo(NotificationType.ALERTA);
        notificationRepository.save(n);
    }
    @Override
    public Notificacion marcarComoLeida(Notificacion n){
        n.setEsLeida(true);
        notificationRepository.save(n);
        return n;
    }
    public Notificacion obtenerNotificacion(long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new InventarioException("Notificación no encontrada"));
    }
    @Override
    public List<Notificacion> listarNoLeidas() {
        return notificationRepository.findByEsLeida(false);
    }

}
