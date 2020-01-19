package com.krry.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * date: 2020/1/16 10:26 <br>
 * author: Administrator <br>
 */
public class MqttPushClient {
    @Autowired
    private PushCallback pushCallback;

    private static final Logger LOGGER = LoggerFactory.getLogger(MqttPushClient.class);

    private static MqttClient mqttClient;

    public static MqttClient getMqttClient() {
        return mqttClient;
    }

    public static void setMqttClient(MqttClient mqttClient) {
        MqttPushClient.mqttClient = mqttClient;
    }

    public void connect(String host,String clientId,String username,String password){
        MqttClient mqttClient;
        try {
            mqttClient = new MqttClient(host,clientId,new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(false);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(30);
            options.setKeepAliveInterval(20);
            MqttPushClient.setMqttClient(mqttClient);
            mqttClient.setCallback(pushCallback);
            mqttClient.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布
     * @param topic
     * @param pushMessage
     */
    public void publish(String topic,String pushMessage){
        publish(0,false,topic,pushMessage);
    }

    public void publish(int qos, boolean retained, String topic, String pushMessage){
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(pushMessage.getBytes());
        MqttTopic mqttTopic = MqttPushClient.getMqttClient().getTopic(topic);
        publish(0,false,topic,pushMessage);
        if (null == mqttTopic){
            LOGGER.error("topic not exist");
        }
        MqttDeliveryToken token;
        try {
            token = mqttTopic.publish(message);
            token.waitForCompletion();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅
     * @param topic
     */
    public void subscrible(String topic){
        subscrible(topic,0);
    }

    public void subscrible(String topic,int qos){
        try {
            MqttPushClient.getMqttClient().subscribe(topic,qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
