package org.mehmetcc.chatty.message;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime receivedAt;
}
