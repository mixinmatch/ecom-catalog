package com.github.mixinmatch.appointments.sub;

import com.github.mixinmatch.appointments.databases.CatalogRepo;
import com.github.mixinmatch.appointments.models.Message;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Component
public class RabbitSubscriber {
    private CatalogRepo catalogRepo;
    private final ObjectMapper om = new ObjectMapper();

    @Autowired
    public RabbitSubscriber(CatalogRepo repo) {
        this.catalogRepo = repo;
    }

    @RabbitListener(bindings = @QueueBinding(
           value = @Queue(name="inventory-queue", durable = "true"),
           exchange = @Exchange(value ="amq.direct", type = ExchangeTypes.DIRECT)
    ))
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
