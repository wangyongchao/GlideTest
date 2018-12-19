package com.lib.imagefetcher.inter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.lib.imagefetcher.annotiaon.Export;

/**
 * Created by wangyongchao on 2018/10/19.
 * 创建fetcher的抽象工厂类
 */
@Export
public interface IFetcherFactory {

    IFetcher createFetcher(Context context);

    IFetcher createFetcher(Activity activity);

    IFetcher createFetcher(FragmentActivity fragmentActivity);

    IFetcher createFetcher(Fragment fragment);

    IFetcher createFetcher(android.support.v4.app.Fragment fragment);

    IFetcher createFetcher(View view);

}
