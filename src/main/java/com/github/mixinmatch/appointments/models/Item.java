package com.github.mixinmatch.appointments.models;

import java.util.UUID;

public record Item(
        UUID id,
        String name,
        String merchant,
        UUID merchantId,
        String photoLink,
        boolean isLiquidationSale
) {}
