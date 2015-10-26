package com.lewi.androidservicesdemo.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import com.lewi.aidl.myData;

/**
 * 
 * @author lyue
 * 实现了aidl Service接口
 * 对外提供了两个接口类： getPid 和 touchEvent 方法
 * 
 */
public class aidlService extends Service {

	MyCommand myData;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return myData;
	}

	@Override
	public void onCreate() {
		myData = new MyCommand();
		super.onCreate();
	}

	class MyCommand extends myData.Stub{

		@Override
		public int getPid() throws RemoteException {
			// TODO Auto-generated method stub
			return Process.myPid();
		}

		@Override
		public String touchEvent() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
