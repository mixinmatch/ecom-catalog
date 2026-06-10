package com.github.mixinmatch.appointments.Controllers;

import com.github.mixinmatch.appointments.databases.CatalogRepo;
import com.github.mixinmatch.appointments.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {
    @Autowired
    private CatalogRepo catalogRepo;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("ok!");
    }

    @GetMapping("/item/{uuid}")
    public ResponseEntity<Item> getItem(@PathVariable("uuid") String itemId) {
        if (itemId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        UUID id;
        try {
            id = UUID.fromString(itemId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(catalogRepo.getItem(id));
    }

    @GetMapping("/items")
    public ResponseEntity<Collection<Item>> getItems() {
        return ResponseEntity.status(HttpStatus.OK).body(catalogRepo.getItems());
    }
    @PutMapping("/items/{uuid}")
    public ResponseEntity<Collection<Item>> setLiquidationStatus(
            @PathVariable("uuid") String itemId,
            @RequestBody Status status
            ) {
        catalogRepo.setLiquidation(UUID.fromString(itemId), status.isLiquidationStatus());
        return ResponseEntity.ok().build();
    }
}
