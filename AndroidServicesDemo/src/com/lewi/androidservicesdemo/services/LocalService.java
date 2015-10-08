package com.lewi.androidservicesdemo.services;

import java.util.Random;

import com.lewi.androidservicesdemo.MainActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * 
 * @author lyue
 * LocalService通信 ： 同一个进程通信
 * Demo功能：通过UI的button触发和localservice的方法通信
 * 原理：
 * 通过Service中的onBind方法，返回IBinder对象。通过ServiceConnection中onServiceConnected的IBinder参数，把Service对象传递实例给UI线程。
 * 在UI线程中，通过Service对象获取对应的方法
 */

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
