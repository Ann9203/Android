package com.jczb.checkpoint.common;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

public class SuperAsyncHttp {
    Context context;
    /**
     * 单例模式
     */
    private static SuperAsyncHttp superAsyncHttp=new SuperAsyncHttp();
    private SuperAsyncHttp(){}
    public static SuperAsyncHttp getInstance(){
        if(superAsyncHttp==null){
            superAsyncHttp=new SuperAsyncHttp();
        }
        return superAsyncHttp;
    }
 
    /**
     * 异步http请求下载图片返回Drawable对象
     */
    public Drawable post4Drawable(String url){
        HttpPost httpPost=null;
        HttpClient httpClient=null;
        HttpResponse httpResponse=null;
        try{
            httpPost=new HttpPost(url);
            httpClient=new DefaultHttpClient();
            httpResponse=httpClient.execute(httpPost);
            if(httpResponse.getStatusLine().getStatusCode()==200){
                 
                InputStream is=httpResponse.getEntity().getContent();
                return FormatTools.getInstance().InputStream2Drawable(is);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 异步http请求下载图片返回Bitmap对象
     */
    public Bitmap post4Bitmap(String url){
        HttpPost httpPost=null;
        HttpClient httpClient=null;
        HttpResponse httpResponse=null;
        try{
            httpPost=new HttpPost(url);
            httpClient=new DefaultHttpClient();
            httpResponse=httpClient.execute(httpPost);
            if(httpResponse.getStatusLine().getStatusCode()==200){
                InputStream is=httpResponse.getEntity().getContent();
                return FormatTools.getInstance().InputStream2Bitmap(is);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 异步http请求获取Json类型的返回值
     * @return
     *//*
    public JSON request4Json(Context c,JSON json){
        context=c;
        AsyncHttpClient client=new AsyncHttpClient();
        try{
            StringEntity stringEntity = new StringEntity(json.toString());
            client.post(context, "http://192.4.200.29/testxr/Home/TestReq", stringEntity, "application/json", new JsonHttpResponseHandler(){
 
                @Override
                public void onSuccess(JSONObject response) {
                    super.onSuccess(response);
                    if(response.toString()!=null){
                        Toast.makeText(context, response.toString(), 1000).show();
                        Log.i("response", response.toString());
                    }else{
                        Log.i("response", "请求错误");
                    }
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }*/
}