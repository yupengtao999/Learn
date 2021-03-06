package com.krry.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.text.MessageFormat;

/**
 * date: 2020/1/18 15:06 <br>
 * author: Administrator <br>
 */
public class Demo {
    public static void main(String[] args) {
        String broker = "tcp://localhost:1883";
        String clientId = "89cff5aacf264bf5bdd63d7da219dead";
        String username = "admin";
        String password = "password";
        //Use the memory persistence
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker:" + broker);
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            String topic = "demo/topics";
            System.out.println("Subscribe to topic:" + topic);
            sampleClient.subscribe(topic);
            sampleClient.setCallback(new MqttCallback() {
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String theMsg = MessageFormat.format("{0} is arrived for topic {1}.", new String(message.getPayload()), topic);
                    System.out.println(theMsg);
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                }

                public void connectionLost(Throwable throwable) {
                }
            });


            String content = "Message from MqttPublishSample";
            int qos = 2;
            System.out.println("Publishing message:" + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");

        } catch (MqttException me) {
            System.out.println("reason" + me.getReasonCode());
            System.out.println("msg" + me.getMessage());
            System.out.println("loc" + me.getLocalizedMessage());
            System.out.println("cause" + me.getCause());
            System.out.println("excep" + me);
            me.printStackTrace();
        }
    }

}
