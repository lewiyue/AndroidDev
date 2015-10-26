package com.lewi.androidservicesdemo.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 * @author lyue
 *
 * 这是Service类的子类，它使用了工作（worker）线程来处理所有的启动请求，
 * 每次请求都会启动一个线程。如果服务不需要同时处理多个请求的话，这是最佳的选择。 所有你要做的工作就是实现onHandleIntent()即可，它会接收每个启动请求的intent，然后就可在后台完成工作。 
 * 
 */
public class StartedService extends IntentService {

	public static final String StartedTag = "StartedService";
	
	public StartedService(){
		super("StartedService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i(StartedTag, "onHandleIntent start-");
		long endTime = System.currentTimeMillis() + 5*1000;
		while (System.currentTimeMillis() < endTime){
			synchronized (this) {
				try {
					Log.i(StartedTag, "onHandleIntent start wait-");
					wait(endTime - System.currentTimeMillis());
					Log.i(StartedTag, "onHandleIntent end while-");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}
		Log.i(StartedTag, "onHandleIntent finish-");
	}

	@Override
	public void onCreate() {
		Log.i(StartedTag, "onCreate-");
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(StartedTag, "onStartCommand-");
		Toast.makeText(this, "Start Service", Toast.LENGTH_SHORT).show();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.i(StartedTag, "onDestroy-");
		Toast.makeText(this, "Destroy Service", Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}
}
