package com.lib.imagefetcher.target;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

public abstract class ImageViewFetcherTarget<Z> extends ViewFetcherTarget<ImageView, Z> {


    public ImageViewFetcherTarget(ImageView view) {
        super(view);
    }

    @Override
    public ImageView getView() {
        return super.getView();
    }

}
