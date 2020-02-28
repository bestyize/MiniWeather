package com.yize.miniweather.util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class AsyncHttpHelper {
    private static final int REQUEST_OK=0;
    private static final int REQUEST_FAILURE=1;
    private AsyncHttpRequestListener listener;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle=msg.getData();
            switch (msg.what){
                case REQUEST_OK:
                    listener.onSuccess(bundle.getString("result"));
                    break;
                case REQUEST_FAILURE:
                    listener.onFailed(bundle.getString("result"));
                    break;
                default :
                    break;
            }
        }
    };

    public AsyncHttpHelper(AsyncHttpRequestListener listener) {
        this.listener = listener;
    }

    public void doGet(final String link){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message=new Message();
                Bundle bundle=new Bundle();
                try {
                    URL url=new URL(link);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setConnectTimeout(4000);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("User-Agent","Android");
                    BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
                    StringBuilder sb=new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){
                        sb.append(line);
                    }
                    bundle.putString("result",sb.toString());
                    message.what=REQUEST_OK;
                } catch (Exception e) {
                    bundle.putString("result",e.toString());
                    message.what=REQUEST_FAILURE;
                }finally {
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

    public void doGetUseOkHttpOne(final String link){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url(link).build();
                Message message=new Message();
                Bundle bundle=new Bundle();
                try {
                    Response response=client.newCall(request).execute();
                    message.what=REQUEST_OK;
                    assert response.body() != null;
                    bundle.putString("result",response.body().string());
                } catch (IOException e) {
                    bundle.putString("result",e.toString());
                    message.what=REQUEST_FAILURE;
                }
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }).start();
    }

    public void doGetUseOkHttp(String link){
        Message message=new Message();
        Bundle bundle=new Bundle();
        OkHttpClient client =new OkHttpClient();
        Request request=new Request.Builder().url(link).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                bundle.putString("result",e.toString());
                message.what=REQUEST_FAILURE;
                message.setData(bundle);
                handler.sendMessage(message);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                bundle.putString("result",response.body().string());
                message.what=REQUEST_OK;
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });
    }

}
