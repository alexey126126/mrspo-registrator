package com.example.mrsporegistrator.mappers;

import com.example.mrsporegistrator.dto.InventoryEventDto;
import com.example.mrsporegistrator.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {
    public ProductEntity toEntity(InventoryEventDto dto) {
        return ProductEntity.builder()
                .name(dto.getProductName())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                //.createdAt(dto.getTimestamp())
                .build();
    }
}
