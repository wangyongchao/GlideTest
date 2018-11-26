package com.lib.imagefetcher.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.lib.imagefetcher.inter.IFetcher;
import com.lib.imagefetcher.inter.IFetcherFactory;

/**
 * Created by wangyongchao on 2018/10/22.
 * 工厂方法模式
 */

public class GlideFetcherFactory implements IFetcherFactory {
    @Override
    public IFetcher createFetcher(Context context) {
        return new GlideFetcher(context);
    }

    @Override
    public IFetcher createFetcher(Activity activity) {
        return new GlideFetcher(activity);
    }

    @Override
    public IFetcher createFetcher(FragmentActivity fragmentActivity) {
        return new GlideFetcher(fragmentActivity);
    }

    @Override
    public IFetcher createFetcher(Fragment fragment) {
        return new GlideFetcher(fragment);
    }

    @Override
    public IFetcher createFetcher(android.support.v4.app.Fragment fragment) {
        return new GlideFetcher(fragment);
    }

    @Override
    public IFetcher createFetcher(View view) {
        return new GlideFetcher(view);
    }
}
