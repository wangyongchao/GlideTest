package com.lib.imagefetcher.adapter;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.Target;
import com.lib.imagefetcher.inter.IFetcher;
import com.lib.imagefetcher.inter.ITarget;

/**
 * Created by wangyongchao on 2018/10/22.
 */

public class GlideFetcher implements IFetcher {
    private RequestManager requestManager;
    private RequestBuilder requestBuilder;
    private Object model;


    public GlideFetcher(Context context) {
        requestManager = Glide.with(context);

    }

    public GlideFetcher(Fragment fragment) {
        requestManager = Glide.with(fragment);
    }

    public GlideFetcher(android.support.v4.app.Fragment fragment) {
        requestManager = Glide.with(fragment);

    }

    public GlideFetcher(View view) {
        requestManager = Glide.with(view);

    }

    @Override
    public boolean isPaused() {
        return requestManager.isPaused();
    }

    @Override
    public void pauseRequests() {
        requestManager.pauseRequests();
    }

    @Override
    public IFetcher asBitmap() {
        requestBuilder = requestManager.asBitmap();
        return this;
    }

    @Override
    public IFetcher asGif() {
        requestBuilder = requestManager.asGif();
        return this;
    }

    @Override
    public IFetcher asDrawable() {
        requestBuilder = requestManager.asDrawable();
        return this;
    }

    @Override
    public IFetcher asFile() {
        requestBuilder = requestManager.asFile();
        return this;
    }

    @Override
    public IFetcher load(@Nullable Object model) {
        this.model = model;
        return this;
    }

    @Override
    public IFetcher downloadOnly() {
        requestBuilder = requestManager.downloadOnly();
        return this;
    }

    @Override
    public ITarget into(ImageView imageView) {
        if (requestBuilder == null) {
            requestBuilder = requestManager.asDrawable();
        }
        requestBuilder.load(model);

        Target target = requestBuilder.into(imageView);

        return null;
    }


}
