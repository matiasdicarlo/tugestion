package com.migestion.comprobantes.service;

import com.migestion.comprobantes.components.ComprobanteMapper;
import com.migestion.comprobantes.components.GeneradorNumeroComprobante;
import com.migestion.comprobantes.dto.ComprobanteRequestDTO;
import com.migestion.comprobantes.dto.ComprobanteResponseDTO;
import com.migestion.comprobantes.model.Cliente;
import com.migestion.comprobantes.model.Comprobante;
import com.migestion.comprobantes.model.ComprobanteItem;
import com.migestion.comprobantes.repository.ClienteRepository;
import com.migestion.comprobantes.repository.ComprobanteRepository;
import com.migestion.exception.ComprobantesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComprobanteServiceImp implements ComprobanteService{

        private final ComprobanteRepository repository;
        private final ClienteRepository clienteRepository;
        private final ComprobanteMapper mapper;
        private final GeneradorNumeroComprobante generadorNumero;

        @Autowired
        public ComprobanteServiceImp(ComprobanteRepository repository, ClienteRepository clienteRepository, ComprobanteMapper mapper, GeneradorNumeroComprobante generadorNumero) {
            this.repository = repository;
            this.clienteRepository = clienteRepository;
            this.mapper = mapper;
            this.generadorNumero = generadorNumero;
        }

        @Override
        public ComprobanteResponseDTO crear(ComprobanteRequestDTO req) {

            Cliente cliente = clienteRepository.findById(req.getClienteId())
                    .orElseThrow(() -> new ComprobantesException("Cliente no encontrado"));

            Comprobante c = new Comprobante();
            c.setCliente(cliente);
            c.setTipo(req.getTipo());
            c.setFecha(LocalDateTime.now());
            c.setNumero(generadorNumero.generar(req.getTipo()));
            c.setObservaciones(req.getObservaciones());

            List<ComprobanteItem> items = req.getItems().stream().map(i -> {
                ComprobanteItem it = new ComprobanteItem();
                it.setDescripcion(i.getDescripcion());
                it.setCantidad(i.getCantidad().intValue());
                it.setPrecioUnitario(i.getPrecioUnitario());
                it.setSubtotal(i.getCantidad() * i.getPrecioUnitario());
                it.setComprobante(c);
                return it;
            }).collect(Collectors.toList());

            c.setItems(items);
            c.setTotal(items.stream().mapToDouble(ComprobanteItem::getSubtotal).sum());

            repository.save(c);

            // Si es recibo → registrar en caja
        /*
        if (c.getTipo() == TipoComprobante.RECIBO) {
            flujoCajaService.registerIngreso("Recibo " + c.getNumero(), c.getTotal());
        }
        */

            return mapper.toDTO(c);
        }

        @Override
        public List<ComprobanteResponseDTO> listar() {
            return repository.findAll().stream()
                    .map(mapper::toDTO)
                    .collect(Collectors.toList());
        }

        @Override
        public ComprobanteResponseDTO obtener(Long id) {
            return repository.findById(id)
                    .map(mapper::toDTO)
                    .orElseThrow(() -> new ComprobantesException("Comprobante no encontrado."));
        }

    }