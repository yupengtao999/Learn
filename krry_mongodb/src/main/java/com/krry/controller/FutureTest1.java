package com.krry.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * date: 2020/1/7 14:38 <br>
 * author: Administrator <br>
 */
public class FutureTest1 {
    // Future模式计算1到1亿的和
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();

        // 计算1000次1至1亿的和
        for (int i = 0; i < 1000; i++) {
            // 调度执行
            futureList.add(executorService.submit(new Calc()));
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - start));

        for (int i = 0; i < 1000; i++) {
            try {
                Integer result = futureList.get(i).get();
                System.out.println("第" + i + "个结果: " + result);
            } catch (InterruptedException | ExecutionException e) {
            }
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
    }

    public static class Calc implements Callable<Integer> {


        public Integer call() throws Exception {
            return cal(100000000);
        }

        public static int cal (int num) {
            int sum = 0;
            for (int i = 0; i < num; i++) {
                sum += i;
            }
            return sum;
        }
    }

}
