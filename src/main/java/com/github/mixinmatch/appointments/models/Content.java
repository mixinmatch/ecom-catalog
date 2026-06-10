package com.github.mixinmatch.appointments.models;

import java.util.UUID;

public class Content
{
    public UUID itemId;
    public boolean isLiquidated;

    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public boolean isLiquidated() {
        return isLiquidated;
    }

    public void setLiquidated(boolean liquidated) {
        isLiquidated = liquidated;
    }
}
