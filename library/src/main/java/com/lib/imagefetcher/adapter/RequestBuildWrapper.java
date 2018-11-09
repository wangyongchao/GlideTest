package com.lib.imagefetcher.adapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;

/**
 * Created by wangyongchao on 2018/10/22.
 */

public class RequestBuildWrapper extends RequestBuilder {

    protected RequestBuildWrapper(Glide glide, RequestManager requestManager, Class transcodeClass) {
        super(glide, requestManager, transcodeClass);
    }

}
