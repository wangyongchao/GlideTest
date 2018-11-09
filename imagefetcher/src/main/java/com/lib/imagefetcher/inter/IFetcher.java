package com.lib.imagefetcher.inter;

import android.support.annotation.Nullable;
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
    public ITarget into(ImageView imageView);

}
