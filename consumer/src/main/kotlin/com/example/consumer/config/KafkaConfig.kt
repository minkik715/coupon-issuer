package com.example.consumer.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*

@Configuration
class KafkaConfig {

    @Bean
    fun consumerFactory() : ConsumerFactory<String, Long> {
        val config = mutableMapOf<String, Any>()
        config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = String::class.java
        config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = Long::class.java
        return DefaultKafkaConsumerFactory(config)
    }

    @Bean
    fun kafkaListenerFactory(): ConcurrentKafkaListenerContainerFactory<String, Long>{
        val concurrentKafkaListenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, Long>()
        concurrentKafkaListenerContainerFactory.consumerFactory = consumerFactory()
        return concurrentKafkaListenerContainerFactory
    }
}