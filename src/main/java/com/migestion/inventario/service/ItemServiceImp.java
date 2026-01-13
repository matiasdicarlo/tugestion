package com.migestion.inventario.service;

import com.migestion.exception.InventarioException;
import com.migestion.inventario.model.Item;
import com.migestion.inventario.repository.ItemRepository;
import com.migestion.inventario.repository.MovimientoRepository;
import com.migestion.model.Admin;
import com.migestion.inventario.model.MovimientoStock;
import com.migestion.inventario.model.TipoMovimiento;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImp implements ItemService {

    private final ItemRepository itemRepository;

    private final CodigoBarrasService codigoBarrasService;
    private final MovimientoRepository movimientoRepository;

    private final NotificationService notificationService;

    @Autowired
    public ItemServiceImp(ItemRepository itemRepository, CodigoBarrasService codigoBarrasService, MovimientoRepository movimientoRepository, NotificationService notificationService) {
        this.itemRepository = itemRepository;
        this.codigoBarrasService = codigoBarrasService;
        this.movimientoRepository = movimientoRepository;
        this.notificationService = notificationService;
    }

    @Override
    public Item crear(Item item) {
        if (itemRepository.findBySku(item.getSku()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un ítem con el SKU: " + item.getSku());
        }
        item.setFechaAlta(LocalDate.now());
        item.setFechaUltimaModificacion(LocalDate.now());
        item.setActivo(true);
        if (item.getCodigoBarras() == null || item.getCodigoBarras().isBlank()) {
            String codigoGenerado = codigoBarrasService.generarCodigoInterno();
            item.setCodigoBarras(codigoGenerado);
        }
        return itemRepository.save(item);
    }

    @Override
    public Item actualizar(Long id, Item item) {
        Item existente = itemRepository.findById(id)
                .orElseThrow(() -> new InventarioException("Item no encontrado"));

        existente.setNombre(item.getNombre());
        existente.setDescripcion(item.getDescripcion());
        existente.setMarca(item.getMarca());
        existente.setModelo(item.getModelo());
        if (item.getCodigoBarras() == null || item.getCodigoBarras().isBlank()) {
            String codigoGenerado = codigoBarrasService.generarCodigoInterno();
            existente.setCodigoBarras(codigoGenerado);
        }else {existente.setCodigoBarras(item.getCodigoBarras());}
        existente.setUnidadMedida(item.getUnidadMedida());
        existente.setPrecioUnitario(item.getPrecioUnitario());
        existente.setPrecioCompra(item.getPrecioCompra());
        existente.setStockMinimo(item.getStockMinimo());
        existente.setStockMaximo(item.getStockMaximo());
        existente.setStockActual(item.getStockActual());
        existente.setCategoria(item.getCategoria());
        existente.setProveedor(item.getProveedor());
        existente.setUbicacionInterna(item.getUbicacionInterna());
        existente.setFechaUltimaModificacion(LocalDate.now());

        return itemRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        Item existente = itemRepository.findById(id)
                .orElseThrow(() -> new InventarioException("Item no encontrado"));
        existente.setActivo(false);
        existente.setFechaUltimaModificacion(LocalDate.now());

        itemRepository.save(existente);
    }

    @Override
    public List<Item> listarTodos() {
        return itemRepository.findAll();
    }


    @Override
    public List<Item> listarActivos(Long proveedorId, Long categoriaId) {
        if (proveedorId != null && categoriaId != null) {
            return itemRepository.findActivosByProveedorAndCategoria(proveedorId, categoriaId);
        }
        if (proveedorId != null) {
            return itemRepository.findActivosByProveedor(proveedorId);
        }
        if (categoriaId != null) {
            return itemRepository.findActivosByCategoria(categoriaId);
        }
        return itemRepository.findActivos();
    }


    @Transactional
    public MovimientoStock registrarMovimientoSalida(Long productoId, TipoMovimiento tipo, int cantidad, Admin admin) {
        Item p = itemRepository.findById(productoId).orElseThrow(() -> new InventarioException("Item no encontrado"));
        int stock= p.getStockActual()-cantidad;
        MovimientoStock m = new MovimientoStock();
        if (stock>=0){
        m.setItem(p);
        m.setTipo(tipo);
        m.setCantidad(cantidad);
        m.setUsuario(admin);
        movimientoRepository.save(m);
        p.setStockActual(stock);
        itemRepository.save(p);
        //Si queda en stock mínimo, generar notificación
        if (p.getStockMaximo() != null && stock <= p.getStockMinimo()) {
            notificationService.notificarStockMinimo(p);
            }
        }else{ throw new InventarioException("Item sin Cantidad Suficiente");}
        return m;
    }

    @Transactional
    public MovimientoStock registrarMovimientoEntrada(Long productoId, TipoMovimiento tipo, int cantidad, Admin admin) {
        Item p = itemRepository.findById(productoId).orElseThrow(() -> new InventarioException("Item no encontrado"));
        MovimientoStock m = new MovimientoStock();
        m.setItem(p);
        m.setTipo(tipo);
        m.setCantidad(cantidad);
        m.setUsuario(admin);
        movimientoRepository.save(m);
        int stock= p.getStockActual() +cantidad;
        p.setStockActual(stock);
        itemRepository.save(p);
        // Si llegó al stock máximo, notificación
        if (p.getStockMaximo() != null && stock >= p.getStockMaximo()) {
            notificationService.notificarStockMaximo(p);
        }
        return m;
    }

    @Override
    public Optional<Item> buscarPorId(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Optional<Item> buscarPorSku(String sku) {
        return itemRepository.findBySku(sku);
    }
    @Override
    public Integer cantidadProductos(){
        List<Item> items= itemRepository.findAll();
        return items.size();
    }
}
