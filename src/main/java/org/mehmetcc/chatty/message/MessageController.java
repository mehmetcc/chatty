package org.mehmetcc.chatty.message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MessageController {
    private final MessageRepository repository;

    public MessageController(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/message")
    List<Message> all() {
        return repository.findAll();
    }

    @PostMapping("/message")
    Message receiveMessage(@RequestBody ReceivedMessage receivedMessage) {
        return repository.save(Message.builder()
                .content(receivedMessage.content())
                .createdAt(receivedMessage.createdAt())
                .receivedAt(LocalDateTime.now())
                .build());
    }
}
