package com.waterclock.exampleMqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleMqttApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleMqttApplication.class, args);
        new MqttConfig().configureMqtt();
    }

}
