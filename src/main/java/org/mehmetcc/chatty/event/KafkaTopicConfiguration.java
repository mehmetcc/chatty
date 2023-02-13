package org.mehmetcc.chatty.event;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {
    @Value("${kafka.topicName}")
    public static String TOPIC_NAME;

    @Bean
    public NewTopic messages() {
        return TopicBuilder.name(TOPIC_NAME).build();
    }
}
