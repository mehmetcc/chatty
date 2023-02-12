package org.mehmetcc.chatty.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    List<Message> findAll() {
        return repository.findAll();
    }

    Optional<Message> persistMessage(final Message message) {
        try {
            repository.save(message);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }

        return Optional.of(message);
    }
}
