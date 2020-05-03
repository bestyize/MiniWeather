package com.yize.litebus;
//方法作用线程,工作所在线程
public enum WorkMode {
    THREAD_MAIN,//主线程
    THREAD_SYNC,//本线程
    THREAD_ASYNC//异步线程
}
