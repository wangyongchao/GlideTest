package com.glide.test.model;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lib.imagefetcher.ImageFetcher;
import com.lib.imagefetcher.LoadSource;
import com.lib.imagefetcher.Utils;
import com.lib.imagefetcher.inter.IFetcherTarget;
import com.lib.imagefetcher.inter.ILoadListener;


public class ModelActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    String url = "http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg";
    String thumbUrl = "http://dpic.tiankong.com/z9/sr/QJ9107571341.jpg?x-oss-process=style/670w";
    private String gifUrl = "http://i1.mopimg.cn/img/tt/2014-11/404/20141127150921545.gif230x170.gif";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);
        imageView = (ImageView) findViewById(R.id.imageView);
        findViewById(R.id.display).setOnClickListener(this);
        findViewById(R.id.cancle).setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.display) {
            testNew();
        } else {
//            Glide.with(this).clear(imageView);
            Utils.check(new Utils.OnFileSuccess() {
                @Override
                public void onFileSuccess() {
                    gifUrl.toString();
                }

                @Override
                public void onFileFail() {

                }
            });
        }

    }

    private void testNew() {
        ImageFetcher.with(this).asDrawable().load(url).listener(new ILoadListener() {
            @Override
            public boolean onLoadFailed(Object model, IFetcherTarget target, boolean isFirstResource) {
                System.out.println("onLoadFailed model=" + model);
                return false;
            }

            @Override
            public boolean onLoadSuccess(Object resource, Object model, IFetcherTarget target, LoadSource loadSource,
                                         boolean isFirstResource) {
                System.out.println("onLoadSuccess model=" + model);
                return false;
            }
        }).into(imageView);

    }


//    private void testAll() {
//        RequestManager requestManager = Glide.with(this);
//
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(getDrawable(R.mipmap.ic_placeholder));
//        requestOptions.error(getDrawable(R.mipmap.ic_fail));
//        requestOptions.override(100, 100);
//        requestManager.setDefaultRequestOptions(requestOptions);
//
//        RequestBuilder<Bitmap> requestBuilder = requestManager.asBitmap();
//        requestBuilder.listener(new RequestListener<Bitmap>() {
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean
//                    isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, LoadSource
//                    dataSource, boolean isFirstResource) {
//                return false;
//            }
//        });
//        requestBuilder.load(url);
//        requestBuilder.into(imageView);
//
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }
}
