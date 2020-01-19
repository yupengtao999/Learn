package com.krry.controller.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * date: 2020/1/9 13:21 <br>
 * author: Administrator <br>
 */
public class FutureData implements Data {

    private RealData realData = null;

    private boolean isReady = false;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void setRealData(RealData realData){
        while (isReady){
            return;
        }
        lock.lock();
        try {
            this.realData = realData;
            this.isReady = true;
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    @Override
    public String getResult() {
        lock.lock();
        try {
            while (!isReady){
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return realData.getResult();
    }
}
