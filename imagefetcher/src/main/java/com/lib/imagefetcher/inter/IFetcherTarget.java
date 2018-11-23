package com.lib.imagefetcher.inter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

/**
 * Created by wangyongchao on 2018/10/22.
 */

public interface IFetcherTarget<Z> {

    public void onLoadCleared(@Nullable Drawable placeholder);


    public void onLoadStarted(@Nullable Drawable placeholder);


    public void onLoadComplete(@Nullable Drawable drawable);


    public void onLoadFailed(@Nullable Drawable errorDrawable);
}
