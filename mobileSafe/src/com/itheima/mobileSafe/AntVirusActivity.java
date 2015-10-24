package com.itheima.mobileSafe;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itheima.Utils.MyMD5;
import com.itheima.dao.QueryVirus;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class AntVirusActivity extends Activity {
	@ViewInject(R.id.iv_virus_icon)
	private ImageView iv_virus_icon;
	@ViewInject(R.id.tv_virus_result)
	private TextView tv_virus_result;
	@ViewInject(R.id.pb_virus_progressbar)
	private ProgressBar pb_virus_progressbar;
	@ViewInject(R.id.ll_virus_safeapks)
	private LinearLayout ll_virus_safeapks;
	private PackageManager pm;
	private List<String> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_virus);
		//注册控件
		ViewUtils.inject(this);
		//旋转动画
		RotateAnimation rotateAnimation=new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation.setDuration(1000);
		rotateAnimation.setRepeatCount(Animation.INFINITE);
		//设置插补动画
		LinearInterpolator linearInterpolator=new LinearInterpolator();
		rotateAnimation.setInterpolator(linearInterpolator);
		iv_virus_icon.startAnimation(rotateAnimation);
		//扫描病毒，并且更新进度条和提示文本
		scanner();
		
	}

	private void scanner() {
		pm = getPackageManager();
		tv_virus_result.setText("正在初始化64核杀毒引擎....");
		//扫描所有的应用程序
		new Thread(){
			

			public void run() {
				
				List<PackageInfo> installedPackages = pm.getInstalledPackages(PackageManager.GET_SIGNATURES);
				list = new ArrayList<String>();
				pb_virus_progressbar.setMax(installedPackages.size());
				int count=0;
				for(final PackageInfo packageinfo:installedPackages){
					SystemClock.sleep(500);
					count++;
					pb_virus_progressbar.setProgress(count);
					//获取应用程序名称
					final String name=packageinfo.applicationInfo.loadLabel(pm).toString();
					//对主界面进行更改
					runOnUiThread(new Runnable(){
						@Override
						public void run() {
							// TODO Auto-generated method stub
							tv_virus_result.setText("正在扫描："+name);
							//获取应用程序的signatures
							Signature[] signatures= packageinfo.signatures;//获取签名的信息
							String charsString = signatures[0].toCharsString();
							
							String signmd5 = MyMD5.passwordMD5(charsString);
							System.out.println(name+"  : "+signmd5);
							//System.out.println(packageinfo.packageName+":"+charsString+":"+charsString);
							boolean queryDao = QueryVirus.queryDao(signmd5,getApplicationContext());
							TextView textView=new TextView(AntVirusActivity.this);
							if(queryDao){
								textView.setTextColor(Color.RED);	
								list.add(packageinfo.packageName);
							}else{
								textView.setTextColor(Color.BLACK);	
							}
							
							textView.setText(name);
							ll_virus_safeapks.addView(textView ,0);//从哪个地方开始加载
						}
					});
				};
				/**
				 * 扫描完毕后进行弹出提示框
				 */
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if(list.size()>0){
							showDialogVirus();
							tv_virus_result.setText("病毒已经处理完毕！！");
						}else{
							tv_virus_result.setText("电脑状态很好,没有病毒！");
						}
						iv_virus_icon.clearAnimation();
						
					}
				});
			};
		}.start();
		
		
		
	}
	/**
	 * 弹出对话框
	 */
	public void showDialogVirus(){
		AlertDialog.Builder builder=new Builder(this);
		builder.setTitle("提示：有危险！！");
		builder.setMessage("发现病毒："+list.size()+"个，请及时处理！！");
		builder.setPositiveButton("处理", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				for(String packageName:list){
					Intent intent=new Intent();
					intent.setAction("android.intent.action.DELETE");
					intent.addCategory("android.intent.category.DEFAULT");
					intent.setData(Uri.parse("package:"+packageName));
					startActivity(intent);
				}
				dialog.dismiss();

			}
		});
		builder.setNegativeButton("取消", null);
		builder.show();
	}
}
