package org.mehmetcc.chatty.message;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MessageController {
    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    // TODO for testing. delet
    @GetMapping("/message")
    ResponseEntity<List<Message>> all() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/message")
    ResponseEntity<Message> receiveMessage(@RequestBody ReceivedMessage receivedMessage) {
        return service.persistMessage(Message.builder()
                        .content(receivedMessage.content())
                        .createdAt(receivedMessage.createdAt())
                        .receivedAt(LocalDateTime.now())
                        .build())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }
}
