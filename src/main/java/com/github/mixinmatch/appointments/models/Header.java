package com.github.mixinmatch.appointments.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Header {
    private UUID messageId;
    private UUID correlationId;
    private LocalDateTime timestamp;


    public UUID getMessageId() {
        return messageId;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(UUID correlationId) {
        this.correlationId = correlationId;
    }

    public LocalDateTime getTime() {
        return timestamp;
    }

    public void setTime(LocalDateTime time) {
        this.timestamp = time;
    }
}
