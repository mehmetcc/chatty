package org.mehmetcc.chatty.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Event<T> {
    private final T object;

    private final LocalDateTime publishedAt;
}
