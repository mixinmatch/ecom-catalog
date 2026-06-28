package com.github.mixinmatch.appointments.databases;

import com.github.mixinmatch.appointments.models.Item;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;

@Component
public class CatalogRepo {

    @Autowired
    private EntityManager em;

    public CatalogRepo() {
    }

    public Item getItem(UUID id) {
        return em.find(Item.class, id);
    }

    public Collection<Item> getItems() {
        return em
                .createQuery("SELECT e FROM Item e", Item.class)
                .getResultList();
    }

    public void setLiquidation(UUID id, boolean isLiquidation) {
        Item i = em.find(Item.class, id);
        i.setLiquidationSale(isLiquidation);
        em.getTransaction().commit();
    }
}
