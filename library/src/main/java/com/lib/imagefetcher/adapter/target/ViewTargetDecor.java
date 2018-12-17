package com.lib.imagefetcher.adapter.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Synthetic;
import com.lib.imagefetcher.target.ViewFetcherTarget;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangy on 2018/11/12.
 */

public abstract class ViewTargetDecor<V extends View, Z> extends ViewTarget<V, Z> {

    protected ViewFetcherTarget<V, Z> mTarget;

    public ViewTargetDecor(ViewFetcherTarget<V, Z> target) {
        super(target.getView());
        this.mTarget = target;
    }


}
