package com.glide.test;

import android.app.Application;

import com.lib.imagefetcher.ImageFetcher;
import com.lib.imagefetcher.adapter.GlideFetcherFactory;

/**
 * Created by wangyongchao on 2018/10/23.
 */

public class TestApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ImageFetcher.init(new GlideFetcherFactory());
    }
}
