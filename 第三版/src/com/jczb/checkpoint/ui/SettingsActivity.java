package com.jczb.checkpoint.ui;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import com.alibaba.fastjson.JSON;
import com.jczb.checkpoint.R;
import com.jczb.checkpoint.common.AgentApi;
import com.jczb.checkpoint.common.Constants;
import com.jczb.checkpoint.manager.AppPhotoFileManager;
import com.jczb.checkpoint.manager.AppUpManager;
import com.jczb.checkpoint.model.AppPhotoFile;
import com.jczb.checkpoint.model.AppUp;
import com.jczb.checkpoint.model.UserVo;
import com.jczb.checkpoint.service.UploadService;
import com.jczb.checkpoint.service.UploadUtilsAsync;

import android.R.string;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.Directory;
import android.text.method.MovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 设置页面
 * @author wlc
 * @date 2015-3-17
 */
public class SettingsActivity extends BaseActivity implements OnClickListener {
	
	//初始化变量
	private Button btMakesure;
	private EditText etPower;				//功率
	private Intent mainIntent;
	private AppUpManager appUpManager;
	private AppPhotoFileManager appPhotoFileManager;
	private WebView webView;
	StringBuilder sb = null;

	byte[] image = null;

	
	private ArrayList<File>files;
	private Map<String, String>params;
	Handler mHandler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case UploadService.UPLOAD_SUCCESS:
				Toast.makeText(SettingsActivity.this, "上传成功", Toast.LENGTH_LONG).show();
				break;		
			case UploadService.UPLOAD_FAIL:
				Toast.makeText(SettingsActivity.this, "上传失败", Toast.LENGTH_LONG).show();
				break;
			}
			super.handleMessage(msg);
		}		
	};
	
	/**
	 * 手动上传按钮
	 */
	private Button btMovementUP;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.makesure_id:
			mainIntent = new Intent(SettingsActivity.this,MainActivity.class);
			Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
			startActivity(mainIntent);
			//finish();
			break;
		case R.id.movementUP:
			Toast.makeText(this, "手动上传中...", Toast.LENGTH_SHORT).show();
			didUpload();
			break;
		default:
			break;
		}	
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		etPower = (EditText)findViewById(R.id.power_data_id);		
		btMakesure = (Button)findViewById(R.id.makesure_id);
		btMovementUP = (Button)findViewById(R.id.movementUP);
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		initView();
		findViewById();
		btMakesure.setOnClickListener(this);
		btMovementUP.setOnClickListener(this);
		
		appUpManager=new AppUpManager(this);
		appPhotoFileManager = new AppPhotoFileManager(this);
		files = new ArrayList<File>();
		params = new HashMap<String, String>();
	}
	
	/**
	 * 查找数据库
	 */
	public void didUpload()
	{
		files.clear();
		params.clear();
		List<AppUp> ups = appUpManager.getUpDataWithID(1);
		
		sb=new StringBuilder();
		sb.append("{\"AnBiaoCheck\":[");
		for (int i = 0; i < ups.size(); i++) {
			
			AppUp up=ups.get(i);
			
			sb.append("{");
			
			sb.append("\"id\":\""+up.getID()+"\",");
			sb.append("\"EPCCode\":\""+up.getEPRCode()+"\",");
			sb.append("\"TIDCode\":\""+up.getTIDCode()+"\",");
			sb.append("\"MeiKuangName\":\""+up.getMeKuangiName()+"\",");
			sb.append("\"UserName\":\""+up.getJobName()+"\",");
			sb.append("\"AnJianCode\":\""+up.getAnJianCode()+"\",");
			sb.append("\"CheckDate\":\""+up.getCheckDate()+"\",");
			sb.append("\"ExceRemark\":\""+up.getExceRemark()+"\",");
			sb.append("\"AnBiaoCode\":\""+up.getAnBiaoCode()+"\",");
			sb.append("\"ifhg\":\""+up.getCheckResult()+"\",");
			sb.append("\"UserID\":\""+up.getUserID()+"\",");
			
			List<AppPhotoFile> appPhotoFiles = appPhotoFileManager.getPhotoFileByCondition(up.getID(),"抽查图片");
			sb.append("\"FileInfo\": [");
			for (int j = 0; j < appPhotoFiles.size(); j++) {
				AppPhotoFile appPhotoFile=appPhotoFiles.get(j);
				image = appPhotoFile.getImage();
				if (image != null) {
					String fileUrl = getApplicationContext().getFilesDir().getAbsolutePath();
					fileUrl = fileUrl + "/" ;//+ appPhotoFile.getFileName();
					File file = getFileFromBytes(image, fileUrl,appPhotoFile.getFileName());
					files.add(file);
				}
				sb.append("{");
				sb.append("\"FileName\":\""+appPhotoFile.getFileName()+"\",");
				sb.append("\"FileType\":\""+appPhotoFile.getFileType()+"\"");
				if(j==appPhotoFiles.size()-1)
				{
					sb.append("}");
				}
				else {
					sb.append("},");
				}
			}
			sb.append("]");
			if(i==ups.size()-1)
			{
				sb.append("}");
			}
			else {
				sb.append("},");
			}
		}
		sb.append("]}");
		
		Map<String, String> parmas = new HashMap<String, String>();
		parmas.put("data", sb.toString());
		String url="http://192.168.1.138/CCRIAnBiao/houseInterface.ashx?json=PostUploadFile";
		StringBuffer sbFileTypes=new StringBuffer();
		for (File tempFile:files) {
			String fileName=tempFile.getName();
			sbFileTypes.append(getFileType(fileName));			
		}
		params.put("fileTypes",sbFileTypes.toString());
		params.put("method", "upload");
		UploadUtilsAsync uploadAsy = new UploadUtilsAsync(SettingsActivity.this,parmas,url,params,files);
		uploadAsy.execute();
		//自动上传
		//upload(parmas);
	}

	public void upload( Map data) {
		
		StringBuffer sbFileTypes=new StringBuffer();
		for (File tempFile:files) {
			String fileName=tempFile.getName();
			sbFileTypes.append(getFileType(fileName));			
		}
		params.put("fileTypes",sbFileTypes.toString());
		params.put("method", "upload");
		UploadService uploadService=new UploadService(mHandler);
		uploadService.uploadFileToServer(data,params, files);
	}
	/**
	 * 获取文件的类型
	 * @param fileName ：文件名
	 * @return 文件类型
	 */
	private String getFileType(String fileName) {
		// TODO Auto-generated method stub
		return fileName.substring(fileName.lastIndexOf("."), fileName.length());
	}
/**
 * 二进制转文件	
 */
	public static File getFileFromBytes(byte[] b, String outputFile,String fileName) {
	      BufferedOutputStream stream = null;
	       File file = null;
	       try {
	    	   
	      //file = new File(outputFile);
	      file = new File(outputFile, fileName);
	           FileOutputStream fstream = new FileOutputStream(file);
	           stream = new BufferedOutputStream(fstream);
	           stream.write(b);
	       } catch (Exception e) {
	           e.printStackTrace();
	      } finally {
	          if (stream != null) {
	               try {
	                  stream.close();
	               } catch (IOException e1) {
	                  e1.printStackTrace();
	              }
	          }
	      }
	       return file;
	   }
	
	
}
