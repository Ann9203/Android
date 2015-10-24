package com.jczb.checkpoint.ui;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.UnsupportedEncodingException;  
import java.util.ArrayList;  
import java.util.List;  
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.message.BasicNameValuePair;  
import android.app.Activity;  
import android.os.Bundle;  
public class ListViewActivity extends Activity {  
    //创建请求对象  
    private HttpPost post;  
    //创建客户端对象  
    private HttpClient cliet;  
    //创建发送请求的对象  
    private HttpResponse response;  
    //  
    private UrlEncodedFormEntity urlEntity;  
    //创建接收返回数据的对象  
    private HttpEntity entity;  
    //创建流对象  
    private InputStream is;  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        //setContentView(R.layout.activity_main);  
        //包装请求的地址  
        post=new HttpPost("http://10.0.2.2:8080/My_Service/webdate.jsp");  
        //创建默认的客户端对象  
        cliet=new DefaultHttpClient();  
        //用list封装要向服务器端发送的参数  
        List<BasicNameValuePair> pairs=new ArrayList<BasicNameValuePair>();  
        pairs.add(new BasicNameValuePair("name", "llllllllll"));  
        try {  
            //用UrlEncodedFormEntity来封装List对象  
            urlEntity=new UrlEncodedFormEntity(pairs);  
            //设置使用的Entity  
            post.setEntity(urlEntity);  
            try {  
                //客户端开始向指定的网址发送请求  
                response=cliet.execute(post);  
                //获得请求的Entity  
                entity=response.getEntity();  
                is=entity.getContent();  
                //下面是读取数据的过程  
                BufferedReader br=new BufferedReader(new InputStreamReader(is));  
                String line=null;  
                StringBuffer sb=new StringBuffer();  
                while((line=br.readLine())!=null){  
                    sb.append(line);  
                }  
                System.out.println(sb.toString());  
            } catch (ClientProtocolException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
                                               
                                               
    }  
}  