package com.krry.controller.Test;

/**
 * date: 2020/1/9 13:49 <br>
 * author: Administrator <br>
 */
public class Main {
    public static void main(String[] args){
        Client client = new Client();
        Data data = client.request("10");
        System.out.println("客户端请求完成！");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据 = " + data.getResult());
    }
}
