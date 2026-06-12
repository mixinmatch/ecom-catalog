package com.github.mixinmatch.appointments.Controllers;

import com.github.mixinmatch.appointments.databases.CatalogRepo;
import com.github.mixinmatch.appointments.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class Functions {

    @Autowired
    private CatalogRepo catalogRepo;

    @Bean
    public BiConsumer<String, Status> LiquidationStatus() {
        return (String itemId, Status status) -> catalogRepo.setLiquidation(UUID.fromString(itemId), status.isLiquidationStatus());
    }

    @Bean
    public Function<String, Item> GetItem() {
        return itemId -> {
            UUID id;
            try {
                id = UUID.fromString(itemId);
            } catch (IllegalArgumentException e) {
                return null;
            }
            return catalogRepo.getItem(id);
        };
    }

    @Bean
    public Supplier<Collection<Item>> GetItems() {
        return () -> catalogRepo.getItems();
    }

    @Bean
    public Supplier<String> Health() {
        return () -> """
                {
                    "message": "ok"
                }
                """;
    }
}
