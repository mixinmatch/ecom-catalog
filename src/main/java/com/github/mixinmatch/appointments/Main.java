package com.github.mixinmatch.appointments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collections;

@SpringBootApplication
public class Main {

    static void main() {
        ApplicationContext context = SpringApplication.run(Main.class);
        var jdbc = (NamedParameterJdbcTemplate) context.getBean("namedParameterJdbcTemplate");
        jdbc.update("""
                DROP TABLE IF EXISTS CATALOG;""", Collections.emptyMap());
        jdbc.update("""
                CREATE TABLE IF NOT EXISTS CATALOG (
                id uuid PRIMARY KEY,
                name varchar(256),
                merchant varchar(256),
                merchantId uuid,
                photo varchar(256),
                isLiquidationSale boolean DEFAULT FALSE
                );""", Collections.emptyMap());
        jdbc.update("""
                INSERT INTO CATALOG
                (id, name, merchant, merchantId, photo)
                VALUES
                ('9de0ee75-8e4e-4e12-93b2-0c6cc15175f6', 'Expensive Coat', 'Bootlegciaga', '29cd34a6-5d5b-4e25-8c3f-66bec1298cc4', ''),
                ('a31e26bb-3e47-41de-8c10-dc1af6b3ea9c', 'Electric bike', 'Qinarello', '507ad6bc-ec34-4bca-847d-9398186d21fa', ''),
                ('c370e48d-b39a-485e-9bf1-78ef2e4a974f', 'Race car', 'Uulgati', '8f80cf9e-7586-4f1f-9405-22ba5c28ad0f', ''),
                ('3c298934-f98e-4009-8eb8-328bafb79d26', 'Sports car', 'Vurari', 'da8bd392-220a-4c5d-8bd3-eb37a77ac83b', ''),
                ('16376174-ca62-4fda-a5f7-53f9269c4303', 'Watch', 'Gmeoa', '454000ce-682d-43b6-9d7e-0aec7c31d425', '')
                ON CONFLICT DO NOTHING;
                """, Collections.emptyMap());
    }
}
