Glide是个单例，只会初始化一次。
当调用Glide.with方法获取RequestManager的时候，会先找到RequestManagerRetriever对象，此对象用来创建RequestManager。
RequestManager和当前的FragmentManager对象是一对一关系，创建FragmentManager的时候会先创建一个SupportRequestManagerFragment并添加
到对应的FragmentManager中，然后和当前的RequestManager绑定。


一.线程池
public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
        BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory,RejectedExecutionHandler handler);
        
 corePoolSize：核心池的大小，这个参数跟后面讲述的线程池的实现原理有非常大的关系。在创建了线程池后，默认情况下，
 线程池中并没有任何线程，而是等待有任务到来才创建线程去执行任务，除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法，
 从这2个方法的名字就可以看出，是预创建线程的意思，即在没有任务到来之前就创建corePoolSize个线程或者一个线程。默认情况下，
 在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，当线程池中的线程数目达到corePoolSize后，
 就会把到达的任务放到缓存队列当中；
 
 maximumPoolSize：线程池最大线程数，这个参数也是一个非常重要的参数，它表示在线程池中最多能创建多少个线程；
 
 keepAliveTime：表示线程没有任务执行时最多保持多久时间会终止。默认情况下，
 只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用，直到线程池中的线程数不大于corePoolSize，
 即当线程池中的线程数大于corePoolSize时，如果一个线程空闲的时间达到keepAliveTime，则会终止，
 直到线程池中的线程数不超过corePoolSize。但是如果调用了allowCoreThreadTimeOut(boolean)方法，
 在线程池中的线程数不大于corePoolSize时，keepAliveTime参数也会起作用，直到线程池中的线程数为0； 
        
unit： 参数keepAliveTime的时间单位，有7种取值，在TimeUnit类中有7种静态属性：

workQueue:一个阻塞队列，用来存储等待执行的任务，这个参数的选择也很重要，会对线程池的运行过程产生重大影响。

threadFactory：线程工厂，主要用来创建线程

handler：表示当拒绝处理任务时的策略，有以下四种取值：
ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。 
ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。 
ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务 

总结：
如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，
若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
如果线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，
直至线程池中的线程数目不大于corePoolSize；如果允许为核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过keepAliveTime，
线程也会被终止。
当线程执行完成任务后，会从队列中获取任务执行，如果任务队列中没有任务，则会阻塞当前线程。

线程池大小配置：
一般需要根据任务的类型来配置线程池大小：
如果是CPU密集型任务，就需要尽量压榨CPU，参考值可以设为 N CPU+1
如果是IO密集型任务，参考值可以设置为2*N CPU
当然，这只是一个参考值，具体的设置还需要根据实际情况进行调整，比如可以先将线程池大小设置为参考值，
再观察任务运行情况和系统负载、资源利用率来进行适当调整。

线程池线程使用完成后处于阻塞状态，要手动销毁。

Executors.newFixedThreadPool：创建固定数量的线性池
new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS,
 new LinkedBlockingQueue<Runnable>())
核心线程和最大线程数量一样，线程池个数是固定的。
    
Executors.newCachedThreadPool():缓冲池
ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,
new SynchronousQueue<Runnable>())如果没有空闲线程每次创建新线程执行任务
SynchronousQueue：不存储数据，必须生产一个消费一个,所以如果没有空闲线程的话就会被拒绝。


ExecutorService newSingleThreadExecutor():创建一个线程的线程池
new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
new LinkedBlockingQueue<Runnable>())

Glie线程池
1.SourceExecutor:
ThreadPoolExecutor(int 4,int 4,long keepAliveTime,TimeUnit unit,
        PriorityBlockingQueue ,ThreadFactory threadFactory,RejectedExecutionHandler handler);
SourceExecutor:核心线程数根据cpu的个数计算，最多不会超过四个线程。glie线程池阻塞队列采用优先级阻塞队列，根据任务的优先级执行。
SourceExecutor最大只有4个线程在执行，新加的任务都会根据优先级在队列中排队等待执行。优先级队列会自动扩容，因此不会存在被拒绝的情况。
2.DiskCacheExecutor：
ThreadPoolExecutor(int 1,int 1,long keepAliveTime,TimeUnit unit,
        PriorityBlockingQueue ,ThreadFactory threadFactory,RejectedExecutionHandler handler);
diskcache创建了一个固定线程池，线程池只有一个线程，顺序执行。
2.MemorySizeCalculator
根据手机设备信息智能计算缓存的大小。
AndroidO也就是8.0 26以后，Bitmap的像素数据是存放在native内存当中，对象存放在java heap当中。
arrayPool 4M

