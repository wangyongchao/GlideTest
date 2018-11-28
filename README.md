# GlideTest
 Glide测试
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
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource
                    dataSource, boolean isFirstResource) {
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

//        requestBuilder.into(new SimpleTarget<Drawable>() {
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
