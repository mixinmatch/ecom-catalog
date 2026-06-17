package com.github.mixinmatch.appointments.databases;

import com.github.mixinmatch.appointments.models.Item;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Component
public class CatalogRepo {

    private final NamedParameterJdbcTemplate namedTemplate;

    public CatalogRepo(NamedParameterJdbcTemplate  namedTemplate) {
        this.namedTemplate = namedTemplate;
    }

    public Item getItem(UUID id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return namedTemplate.query("""
                        SELECT *
                        FROM catalog.CATALOG
                        WHERE id = :id
                        """,
                params,
                rs -> {
                    if (rs.next()) {
                        return new Item(
                                rs.getObject(1, UUID.class),
                                rs.getString("name"),
                                rs.getString("merchant"),
                                rs.getObject(4, UUID.class),
                                rs.getString("photo"),
                                rs.getBoolean(6)
                        );
                    }
                    return null;
                });
    }

    public Collection<Item> getItems() {
        MapSqlParameterSource params = new MapSqlParameterSource();

        return namedTemplate.query("""
                        SELECT *
                        FROM catalog.CATALOG
                        """,
                params,
                rs -> {
                    Collection<Item> items = new ArrayList<>();
                    while (rs.next()) {
                        items.add(new Item(
                                rs.getObject(1, UUID.class),
                                rs.getString("name"),
                                rs.getString("merchant"),
                                rs.getObject(4, UUID.class),
                                rs.getString("photo"),
                                rs.getBoolean(6)
                        ));
                    }
                    return items;

                });
    }

    public void setLiquidation(UUID id, boolean isLiquidation) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("liquidation", isLiquidation);

        namedTemplate.update("""
                        UPDATE catalog.CATALOG
                        SET isLiquidationSale = :liquidation
                        WHERE id = :id
                        """,
                params);
    }
}
