package com.jczb.checkpoint.common;

import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;



public class ImageGetFromHttp {
	private static final String LOG_TAG = "ImageGetFromHttp";
    public static byte[] getImage(String path) throws Exception {  
        URL url = new URL(path);  
        HttpURLConnection httpURLconnection =  (HttpURLConnection)url.openConnection();  
        httpURLconnection.setRequestMethod("GET");  
        httpURLconnection.setReadTimeout(6*1000);  
        InputStream in = null;  
        byte[] b = new byte[1024];  
        int len = -1;  
        if (httpURLconnection.getResponseCode() == 200) {  
             in = httpURLconnection.getInputStream();  
             byte[] result = readStream(in);  
             in.close();  
             return result;  
               
        }  
        return null;  
    }  
      
    public static byte[] readStream(InputStream in) throws Exception{  
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = -1;  
        while((len = in.read(buffer)) != -1) {  
            outputStream.write(buffer, 0, len);  
        }  
        outputStream.close();  
        in.close();  
        return outputStream.toByteArray();  
    }  

	public static Bitmap downloadBitmap(String url) {
		final HttpClient client = new DefaultHttpClient();
		final HttpGet getRequest = new HttpGet(url);
		try {
			HttpResponse response = client.execute(getRequest);
			final int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.w(LOG_TAG, "Error " + statusCode
						+ " while retrieving bitmap from " + url);
				return null;
			}
			final HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = null;
				try {
					inputStream = entity.getContent();
					FilterInputStream fit = new FlushedInputStream(inputStream);
					//return BitmapFactory.decodeStream(fit);
					//------王向阳添加，主要是为了处理内存溢出的问题,但是此方法最好用在读取本地大图片---
//					BitmapFactory.Options opt = new BitmapFactory.Options();
//
//			        opt.inPreferredConfig = Bitmap.Config.RGB_565;
//
//			        opt.inPurgeable = true;
//
//			        opt.inInputShareable = true;

			        BitmapFactory.Options options = new BitmapFactory.Options();

			        options.inJustDecodeBounds = false;

			        options.inSampleSize = 4;   // width，hight设为原来的十分一


			        //----------------------------------
					return BitmapFactory.decodeStream(fit,null,options);
				} finally {
					if (inputStream != null) {
						inputStream.close();
						inputStream = null;
					}
					entity.consumeContent();
				}

			}
		} catch (IOException e) {
			getRequest.abort();
			Log.w(LOG_TAG, "I/O error while retrieving bitmap from " + url, e);
		} catch (IllegalStateException e) {
			getRequest.abort();
			Log.w(LOG_TAG, "Incorrect URL: " + url);
		} catch (Exception e) {
			getRequest.abort();
			Log.w(LOG_TAG, "Error while retrieving bitmap from " + url, e);
		} finally {
			client.getConnectionManager().shutdown();
		}
		return null;
	}

	/*
	 *  * An InputStream that skips the exact number of bytes provided, unless it
	 * reaches EOF.
	 */
	static class FlushedInputStream extends FilterInputStream {
		public FlushedInputStream(InputStream inputStream) {
			super(inputStream);
		}

		@Override
		public long skip(long n) throws IOException {
			long totalBytesSkipped = 0L;
			while (totalBytesSkipped < n) {
				long bytesSkipped = in.skip(n - totalBytesSkipped);
				if (bytesSkipped == 0L) {
					int b = read();
					if (b < 0) {
						break; // we reached EOF
					} else {
						bytesSkipped = 1; // we read one byte
					}
				}
				totalBytesSkipped += bytesSkipped;
			}
			return totalBytesSkipped;
		}
	}
}