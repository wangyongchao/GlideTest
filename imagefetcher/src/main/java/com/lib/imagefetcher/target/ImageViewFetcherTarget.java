package com.lib.imagefetcher.target;

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
