package com.yize.litebus;

import android.os.Looper;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LiteBus {
    private static final String TAG="LiteBus";
    //默认实例
    private volatile static LiteBus DEFAULT_INSTANCE;
    public static LiteBus defaultBus(){
        if(DEFAULT_INSTANCE==null){
            synchronized (LiteBus.class){
                if(DEFAULT_INSTANCE==null){
                    DEFAULT_INSTANCE=new LiteBus();
                }
            }
        }
        return DEFAULT_INSTANCE;
    }
    public LiteBus(){
        METHOD_CACHE=new ConcurrentHashMap<Class<?>, List<SubscriberMethod>>();
        subscriptionBus=new ConcurrentHashMap<Class<?>, List<Subscription>>();
        dataTypeList=new CopyOnWriteArrayList<>();
        mainPublisher=new MainPublisher();
    }
    //订阅者的方法缓存，key为订阅者类
    private Map<Class<?>, List<SubscriberMethod>> METHOD_CACHE;

    //订阅总线，key为数据类型，value是订阅者的订阅。在消息发布的时候根据消息类型进行派发
    private Map<Class<?>,List<Subscription>> subscriptionBus;

    private List<Class<?>> dataTypeList;

    private Publisher mainPublisher;


    /**
     * 注册数据总线，根据数据类型的不同，放到Map对应位置
     * @param subscriber
     */
    public void register(Object subscriber){
        Class<?> subscriberClass=subscriber.getClass();
        List<SubscriberMethod> subscriberMethodList=findSubscribeMethods(subscriberClass);
        synchronized (this){
            for (SubscriberMethod subscriberMethod:subscriberMethodList){
                subscribe(subscriber,subscriberMethod);
            }
        }

    }

    /**
     * 使用反射获取订阅方法列表
     * @param subscriberClass
     * @return
     */
    private List<SubscriberMethod> findSubscribeMethods(Class<?> subscriberClass){
        if(METHOD_CACHE.containsKey(subscriberClass)){
            return METHOD_CACHE.get(subscriberClass);
        }
        List<SubscriberMethod> subscriberMethodList=new LinkedList<SubscriberMethod>();
        Method[] methods=subscriberClass.getDeclaredMethods();
        for (Method method:methods){
            if(method.isAnnotationPresent(Subscribe.class)){
                Subscribe subscribe=method.getAnnotation(Subscribe.class);
                WorkMode workMode=subscribe.workMode();//入口方法的工作模式，也就是作用的线程
                Class<?> dataType=method.getParameterTypes()[0];//获取订阅方法的入口参数的类型
                if(!dataTypeList.contains(dataType)){
                    dataTypeList.add(dataType);
                }
                SubscriberMethod subscriberMethod=new SubscriberMethod(method,workMode,dataType);
                subscriberMethodList.add(subscriberMethod);
            }
        }
        METHOD_CACHE.put(subscriberClass,subscriberMethodList);//放到类订阅方法的缓存里
        return subscriberMethodList;
    }
    /**
     * 为每个方法新建一个订阅，根据数据类型不同，放到一个订阅列表里
     * @param subscriber
     * @param subscriberMethod
     */
    private void subscribe(Object subscriber,SubscriberMethod subscriberMethod){
        Subscription subscription=new Subscription(subscriber,subscriberMethod);
        Class<?> dataType=subscriberMethod.dataType;
        List<Subscription> subscriptionList=subscriptionBus.get(dataType);
        if(subscriptionList==null){
            subscriptionList=new CopyOnWriteArrayList<Subscription>();
            subscriptionBus.put(dataType,subscriptionList);
        }
        subscriptionList.add(subscription);
    }

    /**
     * 普通的数据发布函数
     * @param data
     */
    public void publish(Object data){
        PublishThreadState currState=currentPublishState.get();
        List<Object> dataQueue=currState.dataQueue;
        dataQueue.add(data);
        if(!currState.isPublishing){
            currState.isMainThread= Looper.myLooper()==Looper.getMainLooper();
            currState.isPublishing=true;
            try {
                while (dataQueue.size()>0){
                    publishSingleData(dataQueue.remove(0),currState);
                }
            }finally {
                currState.isMainThread=false;
                currState.isPublishing=false;
            }
        }
    }

    /**
     * 发布消息
     * @param data
     * @param currState
     */
    public void publishSingleData(Object data,PublishThreadState currState){
        Class<?> dataType=data.getClass();
        List<Subscription> subscriptionList=subscriptionBus.get(dataType);//根据数据类型获取订阅列表
        for(Subscription subscription:subscriptionList){//为每个订阅发布消息
            publishToSubscriber(subscription,data,currState.isMainThread);
        }
    }

    /**
     * 向每个订阅者发布消息
     * @param subscription
     * @param data
     * @param isMainThread
     */
    public void publishToSubscriber(Subscription subscription,Object data,boolean isMainThread){
        WorkMode workMode=subscription.subscriberMethod.workMode;
        switch (workMode){
            case THREAD_MAIN:
                if(isMainThread){
                    invoke(subscription,data);
                }else {
                    mainPublisher.enqueue(subscription,data);
                }
                break;
            case THREAD_SYNC:
                break;
            case THREAD_ASYNC:
                break;
            default:
                break;
        }
    }

    /**
     * 反射执行方法
     * @param subscription
     * @param data
     */
    private void invoke(Subscription subscription,Object data){
        try {
            subscription.subscriberMethod.method.invoke(subscription.subscriber,data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程发布状态
     */
    private final ThreadLocal<PublishThreadState> currentPublishState=new ThreadLocal<PublishThreadState>(){
        @Override
        protected PublishThreadState initialValue() {
            return new PublishThreadState();
        }
    };

    static class PublishThreadState{
        final List<Object> dataQueue=new LinkedList<Object>();
        boolean isMainThread;
        boolean isPublishing;
    }

    /**
     * 解除数据总线上的注册
     * @param subscriber
     */
    public void unregister(Object subscriber){
        Class<?> subscriberClass=subscriber.getClass();
        List<SubscriberMethod> subscriberMethods=METHOD_CACHE.get(subscriberClass);
        if(subscriberMethods.size()==0){
            Log.i(TAG,"unregister failed ,there is no subscriber");
            return;
        }
        METHOD_CACHE.remove(subscriberClass);
        synchronized (this){
            for (Class<?> dataType:dataTypeList){
                List<Subscription> subscriptionList=subscriptionBus.get(dataType);
                if(subscriptionList!=null&&subscriptionList.size()>0){
                    int curr=0;
                    while (curr<subscriptionList.size()){
                        if(subscriptionList.get(curr).subscriber==subscriber){
                            subscriptionList.remove(curr);
                        }else {
                            curr++;
                        }
                    }
                }
            }
        }

    }

}
