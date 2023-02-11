package org.mehmetcc.chatty.message;

import java.time.LocalDateTime;

public record ReceivedMessage(String content, LocalDateTime createdAt) {
}
