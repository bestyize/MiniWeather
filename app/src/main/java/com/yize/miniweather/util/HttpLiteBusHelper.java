package com.yize.miniweather.util;

import com.yize.litebus.LiteBus;
import com.yize.litebus.Subscribe;
import com.yize.litebus.WorkMode;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpLiteBusHelper {
    private AsyncHttpRequestListener listener;
    public HttpLiteBusHelper(AsyncHttpRequestListener listener) {
        this.listener = listener;
    }
    public void doAsyncRequest(String link){
        LiteBus.defaultBus().register(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url(link).build();
                try {
                    Response response=client.newCall(request).execute();
                    LiteBusWebResponse result=new LiteBusWebResponse(response.body().string());
                    LiteBus.defaultBus().publish(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    @Subscribe(workMode = WorkMode.THREAD_MAIN)
    public void onResponseArrive(LiteBusWebResponse response) {
        if(response.response.length()==0){
            listener.onFailed("请求失败");
        }else {
            listener.onSuccess(response.response);
        }
        //LiteBus.defaultBus().unregister(this);
    }

    class LiteBusWebResponse{
        private final String response;

        LiteBusWebResponse(String response) {
            this.response = response;
        }
    }
}
