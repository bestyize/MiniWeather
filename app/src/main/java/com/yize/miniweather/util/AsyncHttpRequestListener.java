package com.yize.miniweather.util;

public interface AsyncHttpRequestListener {
    void onSuccess(String result);
    void onFailed(String reason);
}
