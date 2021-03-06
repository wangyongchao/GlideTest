package com.glide.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.glide.test.model.ModelActivity;
import com.glide.test.testing.TestActivity;

import java.util.concurrent.ExecutionException;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    ImageView imageView2;
    String url = "http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg";
    String thumbUrl = "http://dpic.tiankong.com/z9/sr/QJ9107571341.jpg?x-oss-process=style/670w";
    private String gifUrl = "http://i1.mopimg.cn/img/tt/2014-11/404/20141127150921545.gif230x170.gif";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        findViewById(R.id.display).setOnClickListener(this);
        findViewById(R.id.display_other).setOnClickListener(this);
        findViewById(R.id.cancle).setOnClickListener(this);
        findViewById(R.id.start).setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.display) {
            testTarget();
        } else if (id == R.id.cancle) {
            Glide.with(this).clear(imageView);
        } else if (id == R.id.display_other) {
            testOptions();
        } else {
            startActivity(new Intent(this, ModelActivity.class));

        }

    }

    private void testAll() {
        RequestManager requestManager = Glide.with(this);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(getDrawable(R.mipmap.ic_placeholder));
        requestOptions.error(getDrawable(R.mipmap.ic_fail));
        requestOptions.override(100, 100);
        requestManager.setDefaultRequestOptions(requestOptions);

        RequestBuilder<Bitmap> requestBuilder = requestManager.asBitmap();
        requestBuilder.listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean
                    isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource
                    dataSource, boolean isFirstResource) {
                return false;
            }
        });
        requestBuilder.load(url);
        requestBuilder.into(imageView);

    }


    private void loadImg() {

        RequestManager requestManager = Glide.with(this);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(getDrawable(R.mipmap.ic_launcher));
        requestOptions.error(getDrawable(R.mipmap.ic_launcher_round));
        requestManager.setDefaultRequestOptions(requestOptions);
        RequestBuilder<Drawable> requestBuilder = requestManager.load(url).transition(withCrossFade()).listener(new RequestListener<Drawable>() {

            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean
                    isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource
                    dataSource, boolean isFirstResource) {
                return false;
            }
        });
        requestBuilder.into(imageView);

    }

//    private void testApt() {
//        GlideApp.with(this)
//                .asBitmap()
//                .load(url)
//                .error(R.mipmap.ic_launcher)
//                .placeholder(R.mipmap.ic_launcher_round)
//                .into(imageView);
//
//    }

    /**
     * 测试缩略图
     */
    private void testRequestBuild() {
        RequestManager requestManager = Glide.with(this);
        RequestBuilder requestBuilder = requestManager.load(url);
//        requestBuilder.thumbnail(Glide.with(this)
//                .load(thumbUrl)).into(imageView);
//        requestBuilder.thumbnail(0.25f).into(imageView);

    }

    private void testOptions() {
        Glide.with(this).
                load(thumbUrl).
                apply(RequestOptions.centerCropTransform())
                .transition(withCrossFade())
                .into(imageView2);

    }


    private void testTarget() {

        RequestManager requestManager = Glide.with(this);
        RequestBuilder<Drawable> requestBuilder = requestManager.asDrawable();
        requestBuilder.load(url)
                .apply(RequestOptions.centerCropTransform())
                .transition(withCrossFade());
        requestBuilder.listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean
                    isFirstResource) {
                System.out.println("model=" + model + ",target=" + target + ",isFirstResource=" + isFirstResource);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource
                    dataSource, boolean isFirstResource) {
                System.out.println("resource=" + resource + ",model=" + model + ",target=" + target + ",dataSource="
                        + dataSource + ",isFirstResource=" + isFirstResource);
                return false;
            }
        });
//        final FutureTarget<Bitmap> futureTarget = requestBuilder.submit();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //会阻塞主线程,只能在子线程中调用
//                    Bitmap bitmap = futureTarget.get();
//                    System.out.println("bitmap=" + bitmap);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        //清楚加载目标
//        Glide.with(this).clear(futureTarget);

//        requestBuilder.into(new SimpleWrapperTarget<Drawable>() {
//            @Override
//            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
//
//            }
//
//        });

        requestBuilder.into(new DrawableImageViewTarget(imageView));

//        requestBuilder.into(new Target<GifDrawable>() {
//            @Override
//            public void onLoadStarted(@Nullable Drawable placeholder) {
//                System.out.println("onLoadStarted");
//
//            }
//
//            @Override
//            public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                System.out.println("onLoadFailed");
//            }
//
//            @Override
//            public void onResourceReady(GifDrawable resource, Transition<? super GifDrawable> transition) {
//                System.out.println("onResourceReady");
//            }
//
//            @Override
//            public void onLoadCleared(@Nullable Drawable placeholder) {
//
//            }
//
//            @Override
//            public void getSize(SizeReadyCallback cb) {
//
//            }
//
//            @Override
//            public void removeCallback(SizeReadyCallback cb) {
//
//            }
//
//            @Override
//            public void setRequest(@Nullable Request request) {
//
//            }
//
//            @Nullable
//            @Override
//            public Request getRequest() {
//                return null;
//            }
//
//            @Override
//            public void onStart() {
//
//            }
//
//            @Override
//            public void onStop() {
//
//            }
//
//            @Override
//            public void onDestroy() {
//
//            }
//        });
//        requestBuilder.into(imageView);

    }

    private void testTransform() {
        Glide.with(this)
                .load(url)
                .apply(fitCenterTransform())
                .into(imageView);
    }


}
