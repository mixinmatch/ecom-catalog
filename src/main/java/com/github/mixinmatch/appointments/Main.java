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
                ('9de0ee75-8e4e-4e12-93b2-0c6cc15175f6', 'Cozyjak', 'Bootlegciaga', '29cd34a6-5d5b-4e25-8c3f-66bec1298cc4', '/images/coat.jpg'),
                ('a31e26bb-3e47-41de-8c10-dc1af6b3ea9c', 'Coatrage II', 'Qinarello', '507ad6bc-ec34-4bca-847d-9398186d21fa', '/images/ebike.jpg'),
                ('c370e48d-b39a-485e-9bf1-78ef2e4a974f', 'Swoosh', 'Uulgati', '8f80cf9e-7586-4f1f-9405-22ba5c28ad0f', '/images/car.jpg'),
                ('3c298934-f98e-4009-8eb8-328bafb79d26', 'Beast', 'Vurari', 'da8bd392-220a-4c5d-8bd3-eb37a77ac83b', '/images/sportcar.jpg'),
                ('16376174-ca62-4fda-a5f7-53f9269c4303', 'Vora', 'Gmeoa', '454000ce-682d-43b6-9d7e-0aec7c31d425', '/images/watch.jpg'),
                
                ('9bcecbe7-1c2e-4566-af8b-23965bb056b4', 'Coatrage', 'Bootlegciaga', '29cd34a6-5d5b-4e25-8c3f-66bec1298cc4', '/images/coat.jpg'),
                ('2974f68a-a1c6-4902-b1c1-9bb7d2f8888b', 'Whimsy Wheels', 'Qinarello', '507ad6bc-ec34-4bca-847d-9398186d21fa', '/images/ebike.jpg'),
                ('ef04af2b-62fb-4f63-85cf-3a4e05c2953a', 'Nitro', 'Uulgati', '8f80cf9e-7586-4f1f-9405-22ba5c28ad0f', '/images/car.jpg'),
                ('03845c56-b764-4da1-9a11-1d15b791fda3', 'Rush', 'Vurari', 'da8bd392-220a-4c5d-8bd3-eb37a77ac83b', '/images/sportcar.jpg'),
                ('2e828c30-797c-4bab-b7eb-da1a7e07d20c', 'Aebris', 'Gmeoa', '454000ce-682d-43b6-9d7e-0aec7c31d425', '/images/watch.jpg'),
                ('15e7a89c-231e-4735-9781-68b022199c05', 'Arctic Edge', 'Bootlegciaga', '29cd34a6-5d5b-4e25-8c3f-66bec1298cc4', '/images/coat.jpg'),
                ('99f8bb92-db48-42d8-b09f-2c6aefe171e4', 'Bouncy Biker', 'Qinarello', '507ad6bc-ec34-4bca-847d-9398186d21fa', '/images/ebike.jpg'),
                ('76d6e7bd-9b8d-42a0-b2a7-d095bb21f270', 'Bolt', 'Uulgati', '8f80cf9e-7586-4f1f-9405-22ba5c28ad0f', '/images/car.jpg'),
                ('5c5d6379-e8c5-4570-8fb7-2c8a1d382bb4', 'Swift', 'Vurari', 'da8bd392-220a-4c5d-8bd3-eb37a77ac83b', '/images/sportcar.jpg'),
                ('b60df29c-8219-4507-ba65-2d7ce05a0545', 'Marvum', 'Gmeoa', '454000ce-682d-43b6-9d7e-0aec7c31d425', '/images/watch.jpg'),
                ('72de9dbf-db5d-47c7-9b31-a0c1b0c8bda3', 'Arctic Edge III', 'Bootlegciaga', '29cd34a6-5d5b-4e25-8c3f-66bec1298cc4', '/images/coat.jpg'),
                ('8a2065e1-a5d4-4af7-a6e3-27972df557cc', 'Bouncy Biker III', 'Qinarello', '507ad6bc-ec34-4bca-847d-9398186d21fa', '/images/ebike.jpg'),
                ('34d7a19e-36fb-4949-a81c-24fd688fac13', 'Bolt III', 'Uulgati', '8f80cf9e-7586-4f1f-9405-22ba5c28ad0f', '/images/car.jpg'),
                ('1ebc0405-dfbb-4e33-a559-d289ff385292', 'Swift III', 'Vurari', 'da8bd392-220a-4c5d-8bd3-eb37a77ac83b', '/images/sportcar.jpg'),
                ('e7fa6881-e13f-4d0e-89eb-0a9d1133cc03', 'Marvum III', 'Gmeoa', '454000ce-682d-43b6-9d7e-0aec7c31d425', '/images/watch.jpg'),
                ('e7564d2d-87c7-43be-a2e8-b4e06e2ae3a3', 'Arctic Edge IV', 'Bootlegciaga', '29cd34a6-5d5b-4e25-8c3f-66bec1298cc4', '/images/coat.jpg'),
                ('6b6eb29a-7975-4018-8175-4b9cb3aa7d2b', 'Bouncy Biker IV', 'Qinarello', '507ad6bc-ec34-4bca-847d-9398186d21fa', '/images/ebike.jpg'),
                ('16279bc9-1d0e-4f12-b0a0-5ba726a917f2', 'Bolt IV', 'Uulgati', '8f80cf9e-7586-4f1f-9405-22ba5c28ad0f', '/images/car.jpg'),
                ('815d1e23-33c3-4b43-8c56-b8b9fe834b7b', 'Swift IV', 'Vurari', 'da8bd392-220a-4c5d-8bd3-eb37a77ac83b', '/images/sportcar.jpg'),
                ('3b95b355-a772-4b82-b537-07d99864494d', 'Marvum IV', 'Gmeoa', '454000ce-682d-43b6-9d7e-0aec7c31d425', '/images/watch.jpg')
                ON CONFLICT DO NOTHING;
                """, Collections.emptyMap());
    }
}
