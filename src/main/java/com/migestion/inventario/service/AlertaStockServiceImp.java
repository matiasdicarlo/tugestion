package com.migestion.inventario.service;

import com.migestion.inventario.dto.ItemAlertaDTO;
import com.migestion.inventario.model.Item;
import com.migestion.inventario.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaStockServiceImp implements AlertaStockService{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<ItemAlertaDTO> obtenerProductosConStockMinimo() {
        List<Item> items= itemRepository.findItemsConStockMinimo();
        return items.stream()
                .map(i -> new ItemAlertaDTO(
                        i.getId(),
                        i.getNombre(),
                        i.getStockActual(),
                        i.getStockMinimo(),
                        i.getStockMaximo()))
                .toList();

    }
    @Override
    public List<ItemAlertaDTO> obtenerProductosConStockMaximo() {
       List<Item> items= itemRepository.findItemsConStockMaximo();
        return items.stream()
                .map(i -> new ItemAlertaDTO(
                        i.getId(),
                        i.getNombre(),
                        i.getStockActual(),
                        i.getStockMinimo(),
                        i.getStockMaximo()))
                .toList();
    }
    @Override
    public List<ItemAlertaDTO> obtenerProductosEnAlerta() {
        List<Item> items= itemRepository.findItemsConAlertas();
        return items.stream()
                .map(i -> new ItemAlertaDTO(
                        i.getId(),
                        i.getNombre(),
                        i.getStockActual(),
                        i.getStockMinimo(),
                        i.getStockMaximo()))
                .toList();
    }
}
