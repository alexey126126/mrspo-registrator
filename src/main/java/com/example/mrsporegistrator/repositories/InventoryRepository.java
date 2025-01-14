package com.example.mrsporegistrator.repositories;

import com.example.mrsporegistrator.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<ProductEntity, Long> {
}
