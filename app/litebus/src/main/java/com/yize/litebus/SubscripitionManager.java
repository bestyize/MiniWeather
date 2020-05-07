package com.yize.litebus;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SubscripitionManager {
    private static final int MAX_THREAD_SIZE=64;
    private static final int CORE_THREAD_SIZE=5;
    private boolean fair=true;
    private ExecutorService executor;
    //同步执行器队列
    private final Deque<Runnable> syncWorkerQueue=new LinkedList<>();

    /**
     * 构建公平或者非公平请求（即是否按顺序）
     * @param fair
     */
    public SubscripitionManager(boolean fair){
        this.fair=fair;
        new SubscripitionManager();
    }

    public SubscripitionManager() {
        if(executor==null){
            executor=new ThreadPoolExecutor(CORE_THREAD_SIZE,MAX_THREAD_SIZE,1, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(MAX_THREAD_SIZE));
        }
    }


    /**
     * 使用线程池
     * @param runnable
     */
    public void enqueue(Runnable runnable){
        synchronized (this){
            if(fair){
                syncWorkerQueue.offerLast(runnable);
            }else {
                syncWorkerQueue.offerFirst(runnable);
            }

        }
        for (Runnable r:syncWorkerQueue){
            executor.execute(r);
        }
    }


}
