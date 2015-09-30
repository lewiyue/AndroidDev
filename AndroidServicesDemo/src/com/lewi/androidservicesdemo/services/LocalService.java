package com.lewi.androidservicesdemo.services;

import java.util.Random;

import com.lewi.androidservicesdemo.MainActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LocalService extends Service {

	private final IBinder mBinder = new LocalBinder();
	
	private final Random mGen = new Random();
	
	public class LocalBinder extends Binder{
		public LocalService getService(){
			return LocalService.this;
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.i(MainActivity.mTag, "------LocalService binding");
		return mBinder;
	}

	//为client提供的方法
	public int getRandomNumber(){
		return mGen.nextInt(100);
	}
	
}
