package com.lib.imagefetcher.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.lib.imagefetcher.LoadSource;
import com.lib.imagefetcher.adapter.target.SimpleWrapperTarget;
import com.lib.imagefetcher.inter.IFetcherTarget;
import com.lib.imagefetcher.inter.ILoadListener;
import com.lib.imagefetcher.inter.OnSizeReady;

public class GlideLoadListener<R> implements RequestListener<R> {
    private final ILoadListener mLoadListener;

    public GlideLoadListener(ILoadListener loadListener) {
        this.mLoadListener = loadListener;
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<R> target, boolean isFirstResource) {
        if (mLoadListener != null) {
            mLoadListener.onLoadFailed(model, new SimpleWrapperTarget(target), isFirstResource);
        }
        return false;
    }

    @Override
    public boolean onResourceReady(R resource, Object model, Target<R> target, DataSource dataSource, boolean
            isFirstResource) {
        if (mLoadListener != null) {
            LoadSource loadSource = LoadSource.REMOTE;
            if (dataSource == DataSource.LOCAL) {
                loadSource = LoadSource.LOCAL;
            } else if (dataSource == DataSource.REMOTE) {
                loadSource = LoadSource.REMOTE;
            } else if (dataSource == DataSource.DATA_DISK_CACHE) {
                loadSource = LoadSource.DATA_DISK_CACHE;
            } else if (dataSource == DataSource.RESOURCE_DISK_CACHE) {
                loadSource = LoadSource.RESOURCE_DISK_CACHE;
            } else if (dataSource == DataSource.MEMORY_CACHE) {
                loadSource = LoadSource.MEMORY_CACHE;
            }

            mLoadListener.onLoadSuccess(resource, model, new SimpleWrapperTarget(target), loadSource,
                    isFirstResource);
        }
        return false;
    }
}
