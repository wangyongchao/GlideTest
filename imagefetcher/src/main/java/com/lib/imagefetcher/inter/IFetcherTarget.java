package com.lib.imagefetcher.inter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

/**
 * 加载目标资源接口
 *
 * @param <Z>
 */
public interface IFetcherTarget<Z> {

    int SIZE_ORIGINAL = Integer.MIN_VALUE;


    /**
     * 资源开始加载
     *
     * @param placeholder
     */
    void onLoadStarted(@Nullable Drawable placeholder);

    /**
     * 资源加载失败
     *
     * @param errorDrawable
     */
    void onLoadFailed(@Nullable Drawable errorDrawable);

    /**
     * 资源准备好
     *
     * @param resource
     * @param transition
     */
    void onResourceReady(Z resource);

    /**
     * 加载被清除
     *
     * @param placeholder
     */
    void onLoadCleared(@Nullable Drawable placeholder);

    /**
     * 获取大小，资源大小确认后，才开始加载
     *
     * @param cb
     */
    void getSize(OnSizeReady onSizeReady);

    /**
     * Removes the given callback from the pending set if it's still retained.
     *
     * @param cb The callback to remove.
     */
    void removeCallback(OnSizeReady onSizeReady);

//    /**
//     * Sets the current request for this target to retain, should not be called outside of Glide.
//     */
//    void setRequest(@Nullable Request request);
//
//    /**
//     * Retrieves the current request for this target, should not be called outside of Glide.
//     */
//    @Nullable
//    Request getRequest();
}
