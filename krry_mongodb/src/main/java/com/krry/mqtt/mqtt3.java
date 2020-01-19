package com.krry.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Date;

/**
 * date: 2020/1/15 14:25 <br>
 * author: Administrator <br>
 */
public class mqtt3 {
    public static void main(String[] args) throws MqttException{
        // 本地mq服务器
        String THINGSBOARD_HOST = "tcp://0.0.0.0:61613";
        //标识mq发送的客户端id
        String PUBLISH_CLIENTID = "0aeb4ced8d774b6abb4fa503b1ea92d4";
        //发送到服务器的topic标识
        String PUBLISH_TOPIC = "go to home";
        //订阅到服务器的topic标识
        String SUBSCRIBE_TOPIC = "go to home";

        //mqtt连接
        MqttClient mqttClient = new MqttClient(THINGSBOARD_HOST, PUBLISH_CLIENTID, new MemoryPersistence());
        //设置超时时间
        mqttClient.setTimeToWait(10000);
        //进行连接
        mqttClient.connect(getOptions());
        //订阅
        mqttClient.subscribe(SUBSCRIBE_TOPIC, 2);
        //回调方法
        mqttClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String context = new String(message.getPayload());
                System.out.println(context);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }

            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("connect  lost");

            }

            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                System.out.println("connect success-------");
            }

        });




        String sendCon="{\"ts\":"+new Date().getTime()+", \"values\":{\"test\":1111}}";
        mqttClient.publish(PUBLISH_TOPIC, sendCon.getBytes(), 2, false);
        System.out.println("发送成功:"+sendCon);



    }

    //连接到mqtt的连接参数配置
    public static MqttConnectOptions getOptions() {
        String username = "admin";
        String password = "password";
        MqttConnectOptions options = new MqttConnectOptions();
        //设置session是否保留上一条记录
        options.setCleanSession(false);
        //连接超时时间
        options.setConnectionTimeout(10);
        //心跳会话时间
        options.setKeepAliveInterval(60);
        //自动重连
        options.setAutomaticReconnect(true);
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        return options;
    }
}
