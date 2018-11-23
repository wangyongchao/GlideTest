package com.lib.imagefetcher.adapter.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;
import com.lib.imagefetcher.inter.IFetcherTarget;
import com.lib.imagefetcher.target.ImageViewFetcherTarget;
import com.lib.imagefetcher.target.ViewFetcherTarget;

/**
 * Created by wangy on 2018/11/12.
 */

public abstract class ImageViewTargetDecor<Z> extends ViewTargetDecor<ImageView, Z> {


    public ImageViewTargetDecor(ViewFetcherTarget target) {
        super(target);
    }

    @Override
    public void onLoadStarted(@Nullable Drawable placeholder) {
        super.onLoadStarted(placeholder);
        setResource(null);
    }

    @Override
    public void onLoadCleared(Drawable placeholder) {
        super.onLoadCleared(placeholder);
        setResource(null);
    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        super.onLoadFailed(errorDrawable);
        setResource(null);
    }

    @Override
    public void onResourceReady(Z resource, Transition<? super Z> transition) {
        setResource(resource);
    }

    protected abstract void setResource(@Nullable Z resource);

}
