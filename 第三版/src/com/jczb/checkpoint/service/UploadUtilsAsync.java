package com.jczb.checkpoint.service;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;

import com.jczb.checkpoint.common.AgentApi;
import com.jczb.checkpoint.common.Constants;
import com.jczb.checkpoint.service.ProgressOutHttpEntity.ProgressListener;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.io.File;
/**
 * 异步AsyncTask+HttpClient上传文件,支持多文件上传,并显示上传进度
 * @author JPH
 * Date:2014.10.09
 * last modified 2014.11.03
 */
public class UploadUtilsAsync extends AsyncTask<String, Integer, String>{
	/**服务器路径**/
	private String url;
	/**上传的参数**/
	private Map<String,String> paramMap;
	private Map<String,String> data;
	/**要上传的文件**/
	private ArrayList<File>files;
	private long totalSize;
	private Context context;
	private ProgressDialog progressDialog;
	public UploadUtilsAsync(Context context,Map<String,String> data, String url,Map<String, String>paramMap,ArrayList<File>files) {
		this.context=context;
		this.url=url;
		this.data=data;
		this.paramMap=paramMap;
		this.files=files;
	}

	@Override
	protected void onPreExecute() {//执行前的初始化
		// TODO Auto-generated method stub
		progressDialog=new ProgressDialog(context);
		progressDialog.setTitle("请稍等...");			
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setCancelable(true);
		progressDialog.show();
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {//执行任务
		String result = AgentApi.dopost(data,
				Constants.UPLOAD_URL);
		if (!result.equals("true")) {
			Toast.makeText(context, "数据错误,请重新上传!", Toast.LENGTH_LONG).show();
			return "false";
		}
		// TODO Auto-generated method stub
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setCharset(Charset.forName(HTTP.UTF_8));//设置请求的编码格式
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//设置浏览器兼容模式
		int count=0;
		for (File file:files) {
//			FileBody fileBody = new FileBody(file);//把文件转换成流对象FileBody
//			builder.addPart("file"+count, fileBody);
			builder.addBinaryBody("file"+count, file);
			count++;
		}		
		builder.addTextBody("method", paramMap.get("method"));//设置请求参数
		builder.addTextBody("fileTypes", paramMap.get("fileTypes"));//设置请求参数
		HttpEntity entity = builder.build();// 生成 HTTP POST 实体  	
		totalSize = entity.getContentLength();//获取上传文件的大小
        ProgressOutHttpEntity progressHttpEntity = new ProgressOutHttpEntity(
        		entity, new ProgressListener() {
                    @Override
                    public void transferred(long transferedBytes) {
                        publishProgress((int) (100 * transferedBytes / totalSize));//更新进度
                    }
                });
        return uploadFile(url, progressHttpEntity);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {//执行进度
		// TODO Auto-generated method stub
		Log.i("info", "values:"+values[0]);
		progressDialog.setProgress((int)values[0]);//更新进度条
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(String result) {//执行结果
		// TODO Auto-generated method stub
		Log.i("info", result);
		Toast.makeText(context, result, Toast.LENGTH_LONG).show();
		progressDialog.dismiss();
		super.onPostExecute(result);
	}
	/**
	 * 向服务器上传文件
	 * @param url
	 * @param entity
	 * @return
	 */
	public String uploadFile(String url, ProgressOutHttpEntity entity) {				
		HttpClient httpClient=new DefaultHttpClient();// 开启一个客户端 HTTP 请求 
        httpClient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);// 设置连接超时时间
        HttpPost httpPost = new HttpPost(url);//创建 HTTP POST 请求  
        httpPost.setEntity(entity);
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return "文件上传成功";
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null && httpClient.getConnectionManager() != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }
        return "文件上传失败";
    }
}