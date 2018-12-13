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
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    String url = "http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg";
    String thumbUrl = "http://dpic.tiankong.com/z9/sr/QJ9107571341.jpg?x-oss-process=style/670w";
    ThreadPoolExecutor customExecutor;
    ExecutorService executorService;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        customExecutor = new CustomExecutor(1, 5, 10,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), new CustomExecutor.CustomThreadFactory(),
                new CustomExecutor.CustomRejectedExecutionHandler());
//        executorService = Executors.newCachedThreadPool(new CustomExecutor.CustomThreadFactory());
//        customExecutor = GlideExecutor.newSourceExecutor();
        this.findViewById(R.id.test1).setOnClickListener(this);

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
                Thread.sleep(5000);
                System.out.println("执行完成..第" + num + "个提交");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int compareTo(@NonNull Object o) {
            return 0;
        }
    }

    @Override
    public void onClick(View v) {
        System.out.println("onclic=" + count);
        MyRunnable myRunnable = new MyRunnable(count);
        customExecutor.execute(myRunnable);
        count++;

    }


}
