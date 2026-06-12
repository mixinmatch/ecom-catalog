package com.github.mixinmatch.appointments.sub;

import com.azure.spring.messaging.servicebus.implementation.core.annotation.ServiceBusListener;
import com.github.mixinmatch.appointments.databases.CatalogRepo;
import com.github.mixinmatch.appointments.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Component
public class ServiceBus {
    private CatalogRepo catalogRepo;
    private final ObjectMapper om = new ObjectMapper();

    @Autowired
    public ServiceBus(CatalogRepo repo) {
        this.catalogRepo = repo;
    }

    @ServiceBusListener(destination = "catalog-queue")
    public void markAsLiquidationItem(String message) {
        System.out.println("Message.java: " + message);
        JsonNode msg2 = om.readTree(message);
        JsonNode node = msg2.path("content").path("isLiquidated");

        if (node.asBoolean()) {
            var msg = om.readValue(message, Message.class);
            if (msg.content.itemId != null) {
                catalogRepo.setLiquidation(msg.content.itemId, node.asBoolean());
            }
        }
    }
}
