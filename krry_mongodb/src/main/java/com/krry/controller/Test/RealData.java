package com.krry.controller.Test;

/**
 * date: 2020/1/9 13:23 <br>
 * author: Administrator <br>
 */
public class RealData implements Data {
    protected String result = null;

    public RealData(String param){
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i < 10;i++){
            sb.append(param);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = sb.toString();
    }
    @Override
    public String getResult() {
        return result;
    }
}
