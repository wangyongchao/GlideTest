package com.glide.test.testing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.glide.test.R;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    String url = "http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg";
    String thumbUrl = "http://dpic.tiankong.com/z9/sr/QJ9107571341.jpg?x-oss-process=style/670w";
    ThreadPoolExecutor customExecutor;
    ExecutorService executorService;
    LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque<Runnable>();
    ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue(1);
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        customExecutor = new CustomExecutor(1, 1, 10,
//                TimeUnit.MINUTES, arrayBlockingQueue, new CustomExecutor.CustomThreadFactory(),
//                new CustomExecutor.CustomRejectedExecutionHandler());
//        executorService = Executors.newCachedThreadPool(new CustomExecutor.CustomThreadFactory());
        customExecutor = new ThreadPoolExecutor(0, 1,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new CustomExecutor.CustomThreadFactory());
//        customExecutor = GlideExecutor.newSourceExecutor();
        this.findViewById(R.id.test1).setOnClickListener(this);
        this.findViewById(R.id.test2).setOnClickListener(this);
        this.findViewById(R.id.test3).setOnClickListener(this);

    }

    class MyRunnable implements Runnable, Comparable {
        private int num;

        public MyRunnable(int num) {
            this.num = num;

        }

        @Override
        public void run() {
            try {
                System.out.println("执行第" + num + "个提交");
                Thread.sleep(50000);
                System.out.println("执行完成..第" + num + "个提交");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int compareTo(@NonNull Object o) {
            MyRunnable myRunnable = (MyRunnable) o;
            return this.num - myRunnable.num;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.test1) {
            MyRunnable myRunnable = new MyRunnable(count++);
            customExecutor.execute(myRunnable);
            int size = customExecutor.getQueue().size();
            System.out.println("size=" + size);

        } else if (id == R.id.test2) {
            linkedBlockingDeque.offer(new MyRunnable(count++));
        } else {

        }


    }


}
