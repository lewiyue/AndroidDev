package com.lewi.storage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import com.lewi.anroidtestapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 
 * @author lyue
 *	实现功能
 *	- 写入一个文件到 Internal Storage
 *	- 从Internal Storage中读取上面写入的文件
 *	- 创建一个文件夹到 External Storage
 *	- 从 External Storage中删除上面创建的文件夹
 *	- 打印一些method的返回值
 */
public class StorageActivity extends Activity {

	private final static String mTag = "StorageActivity";
	Button mIbutton1,mIbutton2;
	Button mEbutton1,mEbutton2;
	Button mTestbutton;
	
	String FILENAME = "lewi";
	String albumName = "guoALBUM";
	int mSwitch = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_storage);
		
		mIbutton1 = (Button)findViewById(R.id.storage_ibutton1);
		mIbutton1.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				 String str = "hello world!";
				 try {
					String CurrentStr =str + " \n" + DateFormat.getDateTimeInstance().format(new Date());
					FileOutputStream fos = openFileOutput(FILENAME,MODE_PRIVATE);
					fos.write(CurrentStr.getBytes());
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		});
		
		mIbutton2 = (Button)findViewById(R.id.storage_ibutton2);
		mIbutton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					FileInputStream fis = openFileInput(FILENAME);
					byte[] buffer = new byte[1024];			
					
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					
					int bytesRead = 0;
					bytesRead = fis.read(buffer);					
					fis.close();
					Toast.makeText(StorageActivity.this, "Get Content from file : \n" + new String(buffer,0,bytesRead), Toast.LENGTH_SHORT).show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		mEbutton1 = (Button)findViewById(R.id.storage_ebutton1);
		mEbutton1.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),albumName);
				if(file.exists()){
					Toast.makeText(StorageActivity.this, "Folder exists, please remove it first ", Toast.LENGTH_SHORT).show();	
				}else{
					if(file.mkdirs()){
						Log.e(mTag, "Directory not created");
					}
					Toast.makeText(StorageActivity.this, "Create new folder : " + albumName , Toast.LENGTH_SHORT).show();			
				}			 
			}		
		});
		
		mEbutton2 = (Button)findViewById(R.id.storage_ebutton2);
		mEbutton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),albumName);
				if(file.exists()){
					file.delete();
					Toast.makeText(StorageActivity.this, "folder " + albumName + " is deleted", Toast.LENGTH_SHORT).show();	
				}else{
					Toast.makeText(StorageActivity.this, "Please create folder first", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		mTestbutton = (Button)findViewById(R.id.storage_testMethods);
		mTestbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String output;
				
				if(mSwitch%2==1){
					output = "getCacheDir() : "+ getCacheDir().toString() + "\n";
					output = output + "getFilesDir() : " + getFilesDir().toString() + "\n";
					
					String[] list = fileList();
					int n = 0;
					while(n < list.length){
						output = output + "fileList() "+ n + " : " + list[n] + "\n";
						n++;
					}
					
					Toast.makeText(StorageActivity.this, output, Toast.LENGTH_LONG).show();					
				}else{
					output = "Environment.getExternalStorageState() : " + Environment.getExternalStorageState() + "\n\n";
					output = output + " getExternalStoragePublicDirectory() : " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "\n\n";
					output = output + " getDownloadCacheDirectory() :" + Environment.getDownloadCacheDirectory() + "\n\n";
					output = output + " getExternalCacheDir() :" + getApplicationContext().getExternalCacheDir() + "\n";
					Toast.makeText(StorageActivity.this, output, Toast.LENGTH_LONG).show();	
				}
				mSwitch++;
			}
		});		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
