package com.glide.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
        findViewById(R.id.display).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImg();
            }
        });

    }

    private void loadImg() {

        String url = "http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg";
//        Glide.with(this)
//                .load(url)
//                .listener(new RequestListener<String, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        return false;
//                    }
//                })
////                .asBitmap()//只加载静态图片
////                .asGif()  //只加载动态图片，如果是静态图片，执行error
//                .placeholder(R.mipmap.ic_launcher)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存策略
//                .error(R.mipmap.ic_launcher)//加载异常
//                .override(500, 500)//指定图片显示的宽高
//                .into(imageView);

        Glide.with(this).
                load(url).into(imageView).onLoadStarted(getResources().getDrawable(R.mipmap.ic_launcher));

    }

}
