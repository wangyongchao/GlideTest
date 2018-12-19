package com.lib.imagefetcher;

import com.lib.imagefetcher.annotiaon.Export;
import com.lib.imagefetcher.inter.IFetcherFactory;

/**
 * Created by wangyongchao on 2018/10/19.
 * 图片全局配置类
 */
@Export
public final class ImageConfiguration {

    private IFetcherFactory fetcherFactory;


    /**
     * 设置加载图片的工厂类
     *
     * @param fetcherFactory
     */
    public void setFetcherFactory(IFetcherFactory fetcherFactory) {
        this.fetcherFactory = fetcherFactory;
    }

    public IFetcherFactory getFetcherFactory() {
        return this.fetcherFactory;
    }
}
