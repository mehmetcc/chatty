package org.mehmetcc.chatty.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private final static Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, String> template;

    private final ObjectMapper mapper;

    public KafkaProducer(KafkaTemplate<String, String> template) {
        this.template = template;

        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public <T> Either<String, Event<T>> send(final Event<T> event) {
        final String json;
        try {
            json = mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            return Either.left(e.getMessage());
        }

        return template.send(KafkaTopicConfiguration.TOPIC_NAME, json)
                .thenApply(result -> {
                    logger.debug("Received: " + json);
                    return Either.<String, Event<T>>right(event);
                })
                .exceptionally(e -> {
                    logger.error(e.getMessage());
                    return Either.left(e.getMessage());
                })
                .join();
    }
}
