package com.migestion.inventario.resource;

import com.migestion.inventario.dto.ItemDTO;
import com.migestion.service.AdminAuthorizationService;
import com.migestion.inventario.model.Item;
import com.migestion.inventario.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;
    private AdminAuthorizationService adminAuthorizationService;

    private ModelMapper modelMapper;


    @Autowired
    public ItemController(ItemService itemService, AdminAuthorizationService adminAuthorizationService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.adminAuthorizationService= adminAuthorizationService;
        this.modelMapper = modelMapper;
    }


    @PostMapping
    public ResponseEntity<Item> crear(@RequestBody Item item, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.crear(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> actualizar(@PathVariable Long id, @RequestBody Item item, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        Item itemUpdate= itemService.actualizar(id, item);
        ItemDTO dto = modelMapper.map(itemUpdate, ItemDTO.class);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        itemService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> listarActivos(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false) Long proveedorId,
            @RequestParam(required = false) Long categoriaId) {
        adminAuthorizationService.authorize(token);
        System.out.println("Proveedor recibido: " + proveedorId);
        System.out.println("Categoria recibida: " + categoriaId);
        List<Item> items = itemService.listarActivos(proveedorId, categoriaId);
        List<ItemDTO> dtos = items.stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> obtenerPorId(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return itemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Item> obtenerPorProveedor(@PathVariable String sku, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return itemService.buscarPorSku(sku)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<Item> obtenerPorSku(@PathVariable String sku, @RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return itemService.buscarPorSku(sku)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cantidad")
    public Integer cantidaItems(@RequestHeader("Authorization") String token) {
        adminAuthorizationService.authorize(token);
        return itemService.cantidadProductos();
    }
}

