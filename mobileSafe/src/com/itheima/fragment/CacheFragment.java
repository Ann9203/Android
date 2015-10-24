package com.itheima.fragment;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itheima.bean.CacheInfo;
import com.itheima.mobileSafe.R;


/**
 * 清理缓存的fragment
 * @author 雪宝宝
 *
 */
public class CacheFragment extends Fragment {

	private List<CacheInfo> list;
	private View view;
	private TextView tv_cachefragment_text;
	private ProgressBar pb_cachefragment_progress;
	private ListView lv_cachefragment_caches;
	private Button bt_cachefragment_caches;
	private PackageManager pm;
	private String cache;
	private String cachepackagename;
	
	//初始化操作
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	//设置fragment的布局
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	
		list=new ArrayList<CacheInfo>();
		list.clear();
		//参数1：布局文件
		//参数2：容器
		//参数3：自动挂在一律false	
		view=inflater.inflate(R.layout.fragment_cache, container, false);
		tv_cachefragment_text=(TextView) view.findViewById(R.id.tv_cachefragment_text);
		pb_cachefragment_progress=(ProgressBar) view.findViewById(R.id.pb_cachefragment_progressbar);
		lv_cachefragment_caches=(ListView) view.findViewById(R.id.lv_cachefragment_caches);
		bt_cachefragment_caches=(Button) view.findViewById(R.id.btn_cachefragment_clear);
		//缓存的清理，单机进入详情的界面
		lv_cachefragment_caches.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//跳入详情的界面
				//跳转到详情页面
				Intent intent = new Intent();
				intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
				intent.setData(Uri.parse("package:"+list.get(position).getPackageName()));
				startActivity(intent);
			}
		});
		
		
		
		return view;
	}
	

	/**
	 * 扫描软件的额缓存
	 */
	public void scanner(){
		//获取包的管理者
		pm=getActivity().getPackageManager();
		tv_cachefragment_text.setText("正在初始化引擎。。。");
		//创建子线程进行扫描
		new Thread(){
			public void run() {
				SystemClock.sleep(1000);
				//获取安装包的信息
				List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
				//获取最大的值进度条
				pb_cachefragment_progress.setMax(installedPackages.size());
				int count=0;
				for(PackageInfo packageinfo:installedPackages){
					SystemClock.sleep(1000);
					count++;
					pb_cachefragment_progress.setProgress(count);
					//获取缓存的大小
					//采用反射进行缓存
					 try {
						Class<?> loadClass = getActivity().getClassLoader().loadClass("android.content.pm.PackageManager");
						//loadClass.getDeclaredMethod("", "getPackageSizeInfo", String.class,IPackageStatsObserver.class);
						Method method = loadClass.getDeclaredMethod("getPackageSizeInfo", String.class,IPackageStatsObserver.class);
						//receiver:类的实例，如果隐藏参数，方法不是静态的必须制定对象
						method.invoke(pm, packageinfo.packageName,mStatsObserver);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					 //扫描的时候显示应用程序的名称
					 final String name = packageinfo.applicationInfo.loadLabel(pm).toString();
					if(getActivity()!=null){
						getActivity().runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								//显示应用程序的名称
								tv_cachefragment_text.setText("正在扫描"+name);
							}
						});
					}
				}
				//扫描完毕后，应该把那个有缓存的÷程序显示到listView 中
				if(getActivity()!=null){
					getActivity().runOnUiThread(new Runnable() {
						
						private MyAdapter myAdapter;

						@Override
						public void run() {
							// TODO Auto-generated method stub
							tv_cachefragment_text.setVisibility(View.GONE);
							pb_cachefragment_progress.setVisibility(View.GONE);
							myAdapter = new MyAdapter();
							lv_cachefragment_caches.setAdapter(myAdapter);
							if(list.size()>0){
								//设置清理缓存
								bt_cachefragment_caches.setVisibility(View.VISIBLE);
								//设置点击事件
								bt_cachefragment_caches.setOnClickListener(new OnClickListener() {
									
									@Override
									public void onClick(View v) {
										//实现真正意义上的清理
										try {
											Class<?> loadClass = getActivity().getClassLoader().loadClass("android.content.pm.PackageManager");
											Method method = loadClass.getDeclaredMethod("freeStorageAndNotify", Long.TYPE,IPackageDataObserver.class);
											method.invoke(pm, Long.MAX_VALUE,new MyIpackageDataObserver());
											
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										//清理缓存
										list.clear();
										//更新界面
										myAdapter.notifyDataSetChanged();
										//隐藏按钮
										bt_cachefragment_caches.setVisibility(View.INVISIBLE);
										
										
									}
								});
							}
						}
					});
				}
			};
		}.start();
	}
	//获取缓存的大小
	IPackageStatsObserver.Stub mStatsObserver=new IPackageStatsObserver.Stub() {
		
	

		@Override
		public void onGetStatsCompleted(PackageStats pStats, boolean succeeded)
				throws RemoteException {
			// TODO Auto-generated method stub
			//获取缓存大小
			long cachesize=pStats.cacheSize;
			/*//获取应用程序大小
			long codesize=pStats.codeSize;
			//获取数据大小
			long  datasize=pStats.dataSize;*/
			if(cachesize>0){
				cache = Formatter.formatFileSize(getActivity(), cachesize);
				cachepackagename=pStats.packageName;
				list.add(new CacheInfo(cachepackagename,cache));
				
				
			}
			
		}
	};
	//清理缓存
	private class MyIpackageDataObserver extends IPackageDataObserver.Stub{

		@Override
		public void onRemoveCompleted(String packageName, boolean succeeded)
				throws RemoteException {
			// TODO Auto-generated method stub
			
		}
		
	}
	//开始Activity设置填充的数据
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		scanner();
	}
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View cacheview = null;
			ViewHolder viewHolder;
			if(convertView!=null){
				view=convertView;
				viewHolder=(ViewHolder) view.getTag();
			}else{
				cacheview=View.inflate(getActivity(), R.layout.item_cache, null);
				viewHolder=new ViewHolder();
				viewHolder.iv_itemcache_icon=(ImageView) cacheview.findViewById(R.id.iv_itemcache_icon);
				viewHolder.tv_itemcache_name=(TextView) cacheview.findViewById(R.id.tv_itemcache_name);
				viewHolder.tv_itemcache_size=(TextView) cacheview.findViewById(R.id.tv_itemcache_size);
				cacheview.setTag(viewHolder);
			}
			//设置显示数据
			CacheInfo cacheinfo=list.get(position);
			//获取图标
			
			try {
				ApplicationInfo applicationInfo = pm.getApplicationInfo(cacheinfo.getPackageName(), 0);
				Drawable loadIcon = applicationInfo.loadIcon(pm);
				String appName=applicationInfo.loadLabel(pm).toString();
				viewHolder.iv_itemcache_icon.setImageDrawable(loadIcon);
				viewHolder.tv_itemcache_name.setText(appName);
				viewHolder.tv_itemcache_size.setText(cacheinfo.getCacheSize());
				
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return cacheview;
		}
		
	}
	
	class ViewHolder{
		ImageView iv_itemcache_icon;
		TextView tv_itemcache_name,tv_itemcache_size;	
	}
}
