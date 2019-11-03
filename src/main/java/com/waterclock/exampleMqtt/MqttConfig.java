package com.waterclock.exampleMqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttConfig {

    private String topic;
    private String host;
    private int port;

    private String username;
    private String password;

    //set your mqtt configs 
    public MqttConfig() {
        this.topic = "";
        this.host = "";
        this.port = 8080;
        this.username = "";
        this.password = "";
    }

    public void configureMqtt(){

        try {


            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            MqttClient client = new MqttClient(String.format("tcp://%s:%d", host, port),
                    MqttClient.generateClientId(), new MemoryPersistence());
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    System.out.println("Connection to MQTT broker lost!");
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    System.out.println("Message received:\n\t"+ new String(mqttMessage.getPayload()) );
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });
            client.connect(options);

            client.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
