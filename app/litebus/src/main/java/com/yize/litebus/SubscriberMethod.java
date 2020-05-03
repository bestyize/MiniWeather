package com.yize.litebus;

import java.lang.reflect.Method;

public class SubscriberMethod {
    public final Method method;//订阅的方法
    public final WorkMode workMode;//订阅方法工作线程
    public final Class<?> dataType;//订阅方法入口参数类型

    public SubscriberMethod(Method method, WorkMode workMode, Class<?> dataType) {
        this.method = method;
        this.workMode = workMode;
        this.dataType = dataType;
    }
}
