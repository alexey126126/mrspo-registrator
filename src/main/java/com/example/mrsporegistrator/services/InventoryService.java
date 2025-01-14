package com.example.mrsporegistrator.services;

import com.example.mrsporegistrator.dto.InventoryEventDto;
import com.example.mrsporegistrator.entities.ProductEntity;
import com.example.mrsporegistrator.mappers.InventoryMapper;
import com.example.mrsporegistrator.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper mapper;

    @Transactional
    public ProductEntity save(InventoryEventDto dto) {
        return inventoryRepository.save(mapper.toEntity(dto));
    }

    @Transactional
    public ProductEntity update(ProductEntity entity, InventoryEventDto dto) {
        entity.setQuantity(entity.getQuantity() + dto.getQuantity());
        if (entity.getPrice() != dto.getPrice()) {
            entity.setPrice(dto.getPrice());
        }
        return inventoryRepository.save(entity);
    }

    @Transactional
    public void delete(ProductEntity entity) {
        inventoryRepository.delete(entity);
    }

    public ProductEntity findProductByName(InventoryEventDto dto) {
        return inventoryRepository.findByName(dto.getProductName());
    }
}
