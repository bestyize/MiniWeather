package com.yize.litebus;

/**
 * 一个订阅类，包括订阅者
 * 订阅方法
 * 订阅状态
 */
public class Subscription {
    public final Object subscriber;
    public final SubscriberMethod subscriberMethod;
    public volatile boolean isAlive=true;

    public Subscription(Object subscriber, SubscriberMethod subscriberMethod) {
        this.subscriber = subscriber;
        this.subscriberMethod = subscriberMethod;
    }
}
