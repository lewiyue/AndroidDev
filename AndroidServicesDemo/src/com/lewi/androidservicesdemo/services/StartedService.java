package com.lewi.androidservicesdemo.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

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
