package com.example.mrsporegistrator.services;

import com.example.mrsporegistrator.dto.InventoryEventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaListenerService {
    @KafkaListener(topics = "inventory-events", groupId = "registrator-group")
    public void listen(InventoryEventDto message) {
        log.info("Received message: {}", message);

    }
}
