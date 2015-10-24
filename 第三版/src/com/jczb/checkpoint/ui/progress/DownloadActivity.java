package com.jczb.checkpoint.ui.progress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.jczb.checkpoint.R;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

/**
 * 下载功能
 * 
 * @author wlc
 * 
 */
public class DownloadActivity extends Activity {

	ProgressDialog progressDialog;
	//CustomListViewAdapter listViewAdapter;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mian);

		listView = (ListView) findViewById(R.id.imageList);
		DownloadTask task = new DownloadTask(this);
		task.execute(new String[] { URL, URL1, URL2 });

		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle("In progress...");// 设置Title
		progressDialog.setMessage("Loading...");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setIndeterminate(false);
		progressDialog.setMax(100);
		progressDialog.setCancelable(true);
		progressDialog.show();
	}

	private class DownloadTask extends
			AsyncTask<String, Integer, List<RowItem>> {

		private Activity context;
		List<RowItem> rowItems;
		int taskCount;

		public DownloadTask(Activity context) {
			this.context = context;
		}

		/**
		 * 通过url地址下载
		 */
		@Override
		protected List<RowItem> doInBackground(String... urls) {
			taskCount = urls.length;
			rowItems = new ArrayList<RowItem>();
			Bitmap map = null;
			/*String result = AgentApi.dopost(parmas,
					Constants.LOGIN_URL);
			// 解析返回的Json数据为对应的对象
			UserVo userVo = JSON.parseObject(result,
					UserVo.class);*/
			
			//遍历url地址
			for (String url : urls) {
				map = downloadImage(url);
				rowItems.add(new RowItem(map));
			}
			return rowItems;
		}
		@Override
		protected void onProgressUpdate(Integer... progress) {
			Log.i("progress",progress[0].toString());
			progressDialog.setProgress(progress[0]);
			if (rowItems != null) {
				progressDialog.setMessage("Loading " + (rowItems.size() + 1)
						+ "/" + taskCount);
			}
		}

		@Override
		protected void onPostExecute(List<RowItem> rowItems) {
			//listViewAdapter = new CustomListViewAdapter(context, rowItems);
			//listView.setAdapter(listViewAdapter);
			progressDialog.dismiss();
		}

		/**
		 * 下载Image
		 * 
		 * @param urlString
		 * @return
		 */
		private Bitmap downloadImage(String urlString) {
			int count = 0;
			Bitmap bitmap = null;

			URL url;
			InputStream in = null;
			BufferedOutputStream out = null;

			try {
				
				//获取url地址中的内容长度
				url = new URL(urlString);
				URLConnection conn = url.openConnection();
				conn.setRequestProperty("Accept-Encoding", "identity"); 
				int lengthOfFile = conn.getContentLength();

				//读取数据到输出流中
				in = new BufferedInputStream(url.openStream());
				ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
				out = new BufferedOutputStream(dataStream);
				Log.i("lengthOfFile", lengthOfFile +"");
				byte[] data = new byte[512];
				long total = 0L;
				
				//从输出流中读取数据，直到数据返回值为-1（即没有数据了）
				while ((count = in.read(data)) != -1) {
					total += count;
					int sample=(int) ((total * 100) / lengthOfFile);
					Log.i("count", count +"");
					Log.i("progress1", sample +"");
					publishProgress((int) ((total * 100) / lengthOfFile));
					out.write(data, 0, count);
				}
				
				out.flush();
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 1;

				byte[] bytes = dataStream.toByteArray();
				bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return bitmap;
		}

	}

	// URL地址
	public static final String URL = "http://su.bdimg.com/static/superpage/img/logo_white.png";
	public static final String URL1 = "http://su.bdimg.com/static/superpage/img/logo_white.png";
	public static final String URL2 = "http://su.bdimg.com/static/superpage/img/logo_white.png";
}
