package com.krry.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * date: 2020/1/16 10:27 <br>
 * author: Administrator <br>
 */
public class PushCallback implements MqttCallback{

    @Autowired
    MqttPushClient mqttPushClient;
    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("连接断开，可以重新连接");
//        mqttPushClient.connect();
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
