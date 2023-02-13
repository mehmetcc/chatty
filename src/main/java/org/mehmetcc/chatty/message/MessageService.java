package org.mehmetcc.chatty.message;

import io.vavr.control.Either;
import org.mehmetcc.chatty.event.Event;
import org.mehmetcc.chatty.event.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final MessageRepository repository;

    private final KafkaProducer producer;

    public MessageService(MessageRepository repository, KafkaProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    List<Message> findAll() {
        return repository.findAll();
    }

    Either<String, Message> persistMessage(final Message message) {
        try {
            repository.save(message);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Either.left(e.getMessage());
        }

        return producer.send(new Event<Message>(message, LocalDateTime.now())).map(Event::getObject);
    }
}
