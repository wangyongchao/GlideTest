package com.lib.imagefetcher.inter;

import android.support.annotation.Nullable;

import com.lib.imagefetcher.LoadSource;

/**
 * 加载完成回调
 */
public interface ILoadListener<R> {

    /**
     * 加载失败，包括发生解析异常
     *
     * @param model           加载资源路径
     * @param target
     * @param isFirstResource
     * @return
     */
    boolean onLoadFailed(Object model, IFetcherTarget<R> target,
                         boolean isFirstResource);


    /**
     * 加载成功
     *
     * @param resource        资源类型
     * @param model           资源路径
     * @param target
     * @param loadSource      资源来源
     * @param isFirstResource
     * @return
     */
    boolean onLoadSuccess(R resource, Object model, IFetcherTarget<R> target, LoadSource loadSource,
                          boolean isFirstResource);
}
