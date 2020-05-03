package com.yize.litebus;

public interface Publisher {
    void enqueue(Subscription subscription, Object data);
}
