Glide是个单例，只会初始化一次。
当调用Glide.with方法获取RequestManager的时候，会先找到RequestManagerRetriever对象，此对象用来创建RequestManager。
RequestManager和当前的FragmentManager对象是一对一关系，创建FragmentManager的时候会先创建一个SupportRequestManagerFragment并添加
到对应的FragmentManager中，然后和当前的RequestManager绑定。
