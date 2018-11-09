package com.lib.imagefetcher;

/**
 * Created by wangyongchao on 2018/10/19.
 */

public class ImagePreconditions {

    private ImagePreconditions() {

    }

    public static <T> T checkNotNull(T arg) {
        return checkNotNull(arg, "Argument must not be null");
    }

    public static <T> T checkNotNull(T arg, String message) {
        if (arg == null) {
            throw new NullPointerException(message);
        }
        return arg;
    }
}
