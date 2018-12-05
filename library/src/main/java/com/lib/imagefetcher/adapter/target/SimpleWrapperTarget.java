package com.lib.imagefetcher.adapter.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.bumptech.glide.request.target.Target;
import com.lib.imagefetcher.ImagePreconditions;
import com.lib.imagefetcher.inter.IFetcherTarget;
import com.lib.imagefetcher.inter.OnSizeReady;

/**
 * 包装glide请求成功回调的target
 */
public class SimpleWrapperTarget implements IFetcherTarget {
    private Target mTarget;

    public SimpleWrapperTarget(Target target) {
        this.mTarget = target;
        ImagePreconditions.checkNotNull(target);
    }

    @Override
    public void onLoadStarted(@Nullable Drawable placeholder) {
        mTarget.onLoadStarted(placeholder);

    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        mTarget.onLoadFailed(errorDrawable);

    }

    @Override
    public void onResourceReady(Object resource) {
        mTarget.onResourceReady(resource, null);

    }

    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) {
        mTarget.onLoadCleared(placeholder);

    }

    @Override
    public void getSize(OnSizeReady onSizeReady) {
        mTarget.getSize(null);

    }

    @Override
    public void removeCallback(OnSizeReady onSizeReady) {
        mTarget.removeCallback(null);

    }
}
