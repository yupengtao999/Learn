package com.krry.controller.Test;

/**
 * date: 2020/1/9 13:44 <br>
 * author: Administrator <br>
 */
public class Client {
    public Data request(final String queryStr){
        final FutureData future = new FutureData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }
        }).start();
        return future;
    }
}
