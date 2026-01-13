package com.migestion.inventario.resource;


import com.migestion.inventario.dto.MovimientoDTO;
import com.migestion.inventario.model.MovimientoStock;
import com.migestion.service.AdminAuthorizationService;
import com.migestion.inventario.service.ItemService;
import com.migestion.inventario.service.MovimientoService;
import com.migestion.model.Admin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {


    private final ItemService itemService;
    private AdminAuthorizationService adminAuthorizationService;

    private final MovimientoService movimientoService;

    private ModelMapper modelMapper;

    @Autowired
    public MovimientoController(ItemService itemService, AdminAuthorizationService adminAuthorizationService, MovimientoService movimientoService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.adminAuthorizationService= adminAuthorizationService;
        this.movimientoService = movimientoService;
        this.modelMapper = modelMapper;
    }



    @PostMapping("/entrada/{id}")
    public ResponseEntity<MovimientoDTO> entrada(@PathVariable Long id, @RequestBody MovimientoStock movimientoStock, @RequestHeader("Authorization") String token) {
        Admin admin= adminAuthorizationService.authorize(token);
        MovimientoStock movimiento= itemService.registrarMovimientoEntrada(id, movimientoStock.getTipo(), movimientoStock.getCantidad(), admin);
        MovimientoDTO dto =   modelMapper.map(movimiento, MovimientoDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/salida/{id}")
    public ResponseEntity<MovimientoDTO> salida(@PathVariable Long id, @RequestBody MovimientoStock movimientoStock, @RequestHeader("Authorization") String token) {
        Admin admin= adminAuthorizationService.authorize(token);
        MovimientoStock movimiento= itemService.registrarMovimientoSalida(id, movimientoStock.getTipo(), movimientoStock.getCantidad(), admin);
        MovimientoDTO dto =   modelMapper.map(movimiento, MovimientoDTO.class);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<MovimientoDTO>> listarMovimientos(@RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        List<MovimientoStock> movimientos = movimientoService.listarMovimientos();
        List<MovimientoDTO> dtos = movimientos.stream()
                .map(movimiento -> modelMapper.map(movimiento, MovimientoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<List<MovimientoDTO>> listarMovimientoPorId(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        List<MovimientoStock> movimientos = movimientoService.buscarPorItemId(id);
        List<MovimientoDTO> dtos = movimientos.stream()
                .map(movimiento -> modelMapper.map(movimiento, MovimientoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        movimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/vaciar")
    public ResponseEntity<Void> vaciar(@RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        movimientoService.eliminarTodos();
        return ResponseEntity.noContent().build();
    }
}

