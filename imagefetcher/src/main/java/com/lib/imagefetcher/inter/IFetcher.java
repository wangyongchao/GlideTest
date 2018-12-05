package com.lib.imagefetcher.inter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by wangyongchao on 2018/10/19.
 */

public interface IFetcher {

    /**
     * 当前requestmanager所关联的请求是否被暂停
     *
     * @return
     */
    boolean isPaused();


    /**
     * 暂停所有请求
     */
    public void pauseRequests();

    /**
     * 恢复所有请求
     */
    public void resumeRequests();

    /**
     * 清楚请求
     *
     * @param view
     */
    public void clear(View view);

    /**
     * 以Bitmap的形式加载资源
     *
     * @return
     */
    public IFetcher asBitmap();

    /**
     * 以Gif的形式加载资源
     *
     * @return
     */
    public IFetcher asGif();

    /**
     * 以Drawable的形式加载资源
     *
     * @return
     */
    public IFetcher asDrawable();


    /**
     * 保存到文件
     *
     * @return
     */
    public IFetcher asFile();

    /**
     * 加载的来源，url或者本地文件
     *
     * @param model
     * @return
     */
    public IFetcher load(@Nullable Object model);


    /**
     * 只下载资源
     *
     * @return
     */
    public IFetcher downloadOnly();

    /**
     * 加载资源到ImageView
     *
     * @param imageView
     * @return
     */
    public IFetcherTarget into(ImageView imageView);


    /**
     * 加载到自定义的target
     *
     * @param target
     * @param <Y>
     * @return
     */
    public <Y extends IFetcherTarget> Y into(@NonNull Y target);

    /**
     * 监听加载成功或失败
     *
     * @param listener
     * @return
     */
    public IFetcher listener(ILoadListener listener);


}
