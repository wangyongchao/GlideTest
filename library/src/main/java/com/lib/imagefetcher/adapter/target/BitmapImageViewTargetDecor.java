package com.lib.imagefetcher.adapter.target;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.request.transition.Transition;
import com.lib.imagefetcher.inter.IFetcherTarget;
import com.lib.imagefetcher.target.BitmapImageViewFetcherTarget;
import com.lib.imagefetcher.target.ViewFetcherTarget;

public class BitmapImageViewTargetDecor extends ImageViewTargetDecor<Bitmap> {

    public BitmapImageViewTargetDecor(ViewFetcherTarget target) {
        super(target);
    }

    @Override
    protected void setResource(@Nullable Bitmap resource) {
        this.getView().setImageBitmap(resource);
    }

    @Override
    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
        super.onResourceReady(resource, transition);
    }
}
