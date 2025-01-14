package com.example.mrsporegistrator.services;

import com.example.mrsporegistrator.dto.InventoryEventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaListenerService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final InventoryService service;
    @KafkaListener(
            topics = "inventory-events",
            groupId = "registrator-group"
    )
    public void listenInventoryEvents(String record) {
        try {
            InventoryEventDto eventDto = objectMapper.readValue(record, InventoryEventDto.class);
            log.info("Прочитано сообщение: {} ", eventDto);
            var entity = service.findProductByName(eventDto);

            if (Objects.nonNull(entity) && eventDto.isEventType()) {
                service.update(entity, eventDto);
            } else if (Objects.nonNull(entity)) {
                service.delete(entity);
            } else {
                service.save(eventDto);
            }

        } catch (Exception e) {
            System.err.println("Failed to process record: " + record);
        }
    }
}
