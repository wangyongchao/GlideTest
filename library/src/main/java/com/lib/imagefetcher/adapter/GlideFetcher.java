package com.lib.imagefetcher.adapter;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lib.imagefetcher.ImagePreconditions;
import com.lib.imagefetcher.Utils;
import com.lib.imagefetcher.adapter.target.BitmapImageViewTargetDecor;
import com.lib.imagefetcher.adapter.target.DrawableImageViewTargetDecor;
import com.lib.imagefetcher.inter.IFetcher;
import com.lib.imagefetcher.inter.IFetcherTarget;
import com.lib.imagefetcher.target.BitmapImageViewFetcherTarget;
import com.lib.imagefetcher.target.DrawableImageViewFetcherTarget;
import com.lib.imagefetcher.target.ViewFetcherTarget;

import java.io.File;

/**
 * Created by wangyongchao on 2018/10/22.
 */

public class GlideFetcher implements IFetcher {
    private RequestManager mRequestManager;
    private RequestBuilder mRequestBuilder;
    private RequestOptions mRequestOptions = new RequestOptions();
    private Object model;
    private Class resourceTypeClass;


    public GlideFetcher(Context context) {
        mRequestManager = Glide.with(context);
    }

    public GlideFetcher(Fragment fragment) {
        mRequestManager = Glide.with(fragment);
    }

    public GlideFetcher(android.support.v4.app.Fragment fragment) {
        mRequestManager = Glide.with(fragment);

    }

    public GlideFetcher(View view) {
        mRequestManager = Glide.with(view);

    }

    @Override
    public boolean isPaused() {
        return mRequestManager.isPaused();
    }

    @Override
    public void pauseRequests() {
        mRequestManager.pauseRequests();
    }

    @Override
    public IFetcher asBitmap() {
        resourceTypeClass = Bitmap.class;
        return this;
    }

    @Override
    public IFetcher asGif() {
        resourceTypeClass = GifDrawable.class;
        return this;
    }

    @Override
    public IFetcher asDrawable() {
        resourceTypeClass = Drawable.class;
        return this;
    }

    @Override
    public IFetcher asFile() {
        resourceTypeClass = File.class;
        return this;
    }

    @Override
    public IFetcher load(@Nullable Object model) {
        this.model = model;
        return this;
    }

    @Override
    public IFetcher downloadOnly() {
        mRequestBuilder = mRequestManager.downloadOnly();
        return this;
    }


    @Override
    public IFetcherTarget into(ImageView imageView) {
        Utils.assertMainThread();
        ImagePreconditions.checkNotNull(imageView);
        if (resourceTypeClass == null) {
            resourceTypeClass = Drawable.class;
        }
        mRequestBuilder = mRequestManager.as(resourceTypeClass);

        if (!mRequestOptions.isTransformationSet()
                && mRequestOptions.isTransformationAllowed()
                && imageView.getScaleType() != null) {
            // Clone in this method so that if we use this RequestBuilder to load into a View and then
            // into a different target, we don't retain the transformation applied based on the previous
            // View's scale type.
            switch (imageView.getScaleType()) {
                case CENTER_CROP:
                    mRequestOptions.clone().optionalCenterCrop();
                    break;
                case CENTER_INSIDE:
                    mRequestOptions.clone().optionalCenterInside();
                    break;
                case FIT_CENTER:
                case FIT_START:
                case FIT_END:
                    mRequestOptions.clone().optionalFitCenter();
                    break;
                case FIT_XY:
                    mRequestOptions.clone().optionalCenterInside();
                    break;
                case CENTER:
                case MATRIX:
                default:
            }
        }
        mRequestBuilder.apply(mRequestOptions).load(model);

        ViewFetcherTarget viewFetcherTarget = null;
        Target target = null;

        if (Bitmap.class.equals(resourceTypeClass)) {
            viewFetcherTarget = new BitmapImageViewFetcherTarget(imageView);
            target = new BitmapImageViewTargetDecor(viewFetcherTarget);
        } else if (Drawable.class.isAssignableFrom(resourceTypeClass)) {
            viewFetcherTarget = new DrawableImageViewFetcherTarget(imageView);
            target = new DrawableImageViewTargetDecor(viewFetcherTarget);
        } else {
            throw new IllegalArgumentException(
                    "Unhandled class: " + resourceTypeClass + ", try .as*(Class).transcode(ResourceTranscoder)");
        }

        mRequestBuilder.into(target);

        return viewFetcherTarget;
    }

    @Override
    public <Y extends IFetcherTarget> Y into(@NonNull Y target) {


        return null;
    }


}
