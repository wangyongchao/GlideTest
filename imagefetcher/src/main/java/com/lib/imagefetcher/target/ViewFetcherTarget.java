package com.lib.imagefetcher.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;

import com.lib.imagefetcher.ImagePreconditions;
import com.lib.imagefetcher.inter.IFetcherTarget;
import com.lib.imagefetcher.inter.OnSizeReady;

public abstract class ViewFetcherTarget<V extends View, Z> implements IFetcherTarget<Z> {

    protected final V view;

    public ViewFetcherTarget(V view) {
        this.view = ImagePreconditions.checkNotNull(view);
    }

    public V getView() {
        return view;
    }

    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) {

    }

    @Override
    public void onLoadStarted(@Nullable Drawable placeholder) {

    }

    @Override
    public void getSize(OnSizeReady onSizeReady) {

    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {

    }

    @Override
    public void onResourceReady(Z resource) {

    }

    @Override
    public void removeCallback(OnSizeReady onSizeReady) {

    }
}
