package com.lewi.asynctask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.lewi.anroidtestapp.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 
 * @author lyue
 * 实现功能：
 * 	通过AsyncTask类实现下载一个网络图片在ImageView中显示
 */
public class AsyntaskActivity extends Activity {

	private final static String mTag = "AsyntaskActivity";
	Button button1;
	public ImageView textImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asyntask);
		
		button1 = (Button)findViewById(R.id.asyntask_load);
		button1.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				 new DownloadImageTask().execute("http://my.csdn.net/uploads/avatar/4/D/E/1_ericyue83.jpg");
			}		
		});
		
		textImage = (ImageView) findViewById(R.id.asyntask_imageView);
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	
		@Override
		protected Bitmap doInBackground(String... urls) {
				try {
					return loadImageFromNetwork(urls[0]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}				
		}

		private Bitmap loadImageFromNetwork(String string) throws IOException {
			
			URL url = new URL(string);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			
			if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
				return null;
			}
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = connection.getInputStream();
			
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while((bytesRead = in.read(buffer))>0){
				out.write(buffer, 0, bytesRead);;
			}
			
			out.close();
			byte[] outBytes = out.toByteArray();
			
			return BitmapFactory.decodeByteArray(outBytes, 0, outBytes.length);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			textImage.setImageBitmap(result);
		}
	}

}
