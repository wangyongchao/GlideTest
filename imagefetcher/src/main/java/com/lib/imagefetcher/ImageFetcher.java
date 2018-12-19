package com.lib.imagefetcher;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.lib.imagefetcher.annotiaon.Export;
import com.lib.imagefetcher.inter.IFetcher;
import com.lib.imagefetcher.inter.IFetcherFactory;

/**
 * Created by wangyongchao on 2018/10/19.
 * 加载图片的入口
 */
@Export
public class ImageFetcher {

    private final static ImageConfiguration IMAGE_CONFIGURATION = new ImageConfiguration();


    private ImageFetcher() {
    }

    /**
     * 初始化，只能被初始化一次
     *
     * @param fetcherFactory 工厂类
     */
    public static void init(@NonNull IFetcherFactory fetcherFactory) {
        if (fetcherFactory == null) {
            throw new IllegalArgumentException("factory can not null");
        }
        if (IMAGE_CONFIGURATION.getFetcherFactory() != null) {
            throw new IllegalArgumentException("The fetcherFactory is initialized.");
        }

        IMAGE_CONFIGURATION.setFetcherFactory(fetcherFactory);
    }

    private static IFetcherFactory getNotNullFactory() {
        IFetcherFactory fetcherFactory = IMAGE_CONFIGURATION.getFetcherFactory();
        ImagePreconditions.checkNotNull(fetcherFactory, "the factory uninitialized,please call init() to initialize");
        return fetcherFactory;
    }


    public static IFetcher with(Context context) {

        return getNotNullFactory().createFetcher(context);
    }

    public static IFetcher with(Activity activity) {

        return getNotNullFactory().createFetcher(activity);
    }

    public static IFetcher with(FragmentActivity fragmentActivity) {

        return getNotNullFactory().createFetcher(fragmentActivity);
    }


    public static IFetcher with(Fragment fragment) {

        return getNotNullFactory().createFetcher(fragment);
    }

    public static IFetcher with(android.support.v4.app.Fragment fragment) {

        return getNotNullFactory().createFetcher(fragment);
    }

    public static IFetcher with(View view) {

        return getNotNullFactory().createFetcher(view);
    }


}
