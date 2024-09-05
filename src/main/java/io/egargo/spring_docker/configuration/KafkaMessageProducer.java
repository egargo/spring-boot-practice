// package io.egargo.spring_docker.configuration;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.stereotype.Component;
//
// @Component
// public class KafkaMessageProducer {
// @Autowired
// private KafkaTemplate<String, String> kafkaTemplate;
//
// public void sendMessage(String topic, String message) {
// kafkaTemplate.send(topic, message);
// }
// }
