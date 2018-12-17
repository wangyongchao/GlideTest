package com.glidetest.java;

import com.glide.test.testing.CustomExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
        System.out.println("test");
        /**
         * 1.当线程池小于corePoolSize时，新提交任务将创建一个新线程执行任务，即使此时线程池中存在空闲线程。
         2.当线程池达到corePoolSize时，新提交任务将被放入workQueue中，等待线程池中任务调度执行
         3.当workQueue已满，且maximumPoolSize>corePoolSize时，新提交任务会创建新线程执行任务
         4.当提交任务数超过maximumPoolSize时，新提交任务由RejectedExecutionHandler处理
         5.当线程池中超过corePoolSize线程，空闲时间达到keepAliveTime时，关闭空闲线程
         6.当设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize线程空闲时间达到keepAliveTime也将关闭
         */
        CustomExecutor customExecutor = new CustomExecutor(5, 8, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), new CustomExecutor.CustomThreadFactory(),
                new CustomExecutor.CustomRejectedExecutionHandler());
        for (int i = 0; i < 10; i++) {
            System.out.println("提交第" + i + "个任务");
            customExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
