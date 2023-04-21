package com.niniconi.mrm.entity;

import com.niniconi.mrm.entity.enumeration.MessageStatus;
import lombok.Data;

@Data
public class MessageEntity {
    MessageStatus status;
    String message;

    public MessageEntity(MessageStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
