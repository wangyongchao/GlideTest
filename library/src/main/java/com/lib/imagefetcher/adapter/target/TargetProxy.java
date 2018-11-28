package com.lib.imagefetcher.adapter.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.lib.imagefetcher.ImagePreconditions;
import com.lib.imagefetcher.inter.IFetcherTarget;
import com.lib.imagefetcher.inter.OnSizeReady;

public class TargetProxy<Z> implements Target<Z> {

    private IFetcherTarget iFetcherTarget;

    public TargetProxy(IFetcherTarget fetcherTarget) {
        this.iFetcherTarget = fetcherTarget;
        ImagePreconditions.checkNotNull(fetcherTarget);
    }


    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) {
        iFetcherTarget.onLoadCleared(placeholder);
        // Do nothing.
    }

    @Override
    public void getSize(final SizeReadyCallback cb) {
        iFetcherTarget.getSize(new OnSizeReady() {
            @Override
            public void onSizeReady(int width, int height) {
                cb.onSizeReady(width, height);
            }
        });

    }

    @Override
    public void removeCallback(SizeReadyCallback cb) {


    }

    @Override
    public void setRequest(@Nullable Request request) {

    }

    @Nullable
    @Override
    public Request getRequest() {
        return null;
    }

    @Override
    public void onLoadStarted(@Nullable Drawable placeholder) {
        // Do nothing.
    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        // Do nothing.
    }

    @Override
    public void onResourceReady(Z resource, Transition<? super Z> transition) {

    }

    @Override
    public void onStart() {
        // Do nothing.
    }

    @Override
    public void onStop() {
        // Do nothing.
    }

    @Override
    public void onDestroy() {
        // Do nothing.
    }
}
