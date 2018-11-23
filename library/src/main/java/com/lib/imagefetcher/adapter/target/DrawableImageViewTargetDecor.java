package com.lib.imagefetcher.adapter.target;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.lib.imagefetcher.target.BitmapImageViewFetcherTarget;
import com.lib.imagefetcher.target.ViewFetcherTarget;

public class DrawableImageViewTargetDecor extends ImageViewTargetDecor<Drawable> {

    public DrawableImageViewTargetDecor(ViewFetcherTarget target) {
        super(target);
    }

    @Override
    protected void setResource(@Nullable Drawable resource) {
        this.getView().setImageDrawable(resource);
    }
}
