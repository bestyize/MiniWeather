package com.yize.miniweather.util;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpAsyncTaskHelper extends AsyncTask<String,Integer,String> {
    private AsyncHttpRequestListener listener;

    public HttpAsyncTaskHelper(AsyncHttpRequestListener listener) {
        this.listener = listener;
    }
    @Override
    protected String doInBackground(String... strings) {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(strings[0]).build();
        try {
            Response response=client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(s.length()==0){
            listener.onFailed("请求失败");
        }else {
            listener.onSuccess(s);
        }
    }
}
