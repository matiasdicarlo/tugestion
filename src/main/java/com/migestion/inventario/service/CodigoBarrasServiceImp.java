package com.migestion.inventario.service;

import com.migestion.inventario.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodigoBarrasServiceImp implements CodigoBarrasService{
    private static final String PREFIJO = "299";


    private ItemRepository itemRepository;

    @Autowired
    public CodigoBarrasServiceImp(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public String generarCodigoInterno() {
        // obtener el último código generado
        Long maxInterno = itemRepository.findMaxCodigoBarrasInterno();

        if (maxInterno == null) {
            maxInterno = 0L;
        }

        long siguiente = maxInterno + 1;

        // 9 dígitos para la parte secuencial
        String secuencia = String.format("%09d", siguiente);

        String base = PREFIJO + secuencia;

        // calcular checksum EAN-13
        int checksum = calcularChecksumEAN13(base);

        return base + checksum;
    }

     public int calcularChecksumEAN13(String codeWithoutChecksum) {
        int sum = 0;

        for (int i = 0; i < codeWithoutChecksum.length(); i++) {
            int num = Character.getNumericValue(codeWithoutChecksum.charAt(i));
            // posiciones impares x1, pares x3
            sum += (i % 2 == 0) ? num : num * 3;
        }

        int modulo = sum % 10;
        return (10 - modulo) % 10;
    }
}