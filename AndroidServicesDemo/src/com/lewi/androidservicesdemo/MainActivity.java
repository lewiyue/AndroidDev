package com.lewi.androidservicesdemo;

import com.lewi.androidservicesdemo.services.LocalService;
import com.lewi.androidservicesdemo.services.LocalService.LocalBinder;
import com.lewi.androidservicesdemo.services.MessengerService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	public final static String mTag = "lewi";
	
	//-----------------Local Service
	LocalService mLocalService;
	boolean mLocalServiceBound = false;
	Button LocalServiceButton;
	
	
	private ServiceConnection LocalServiceConn = new ServiceConnection() {	
		@Override
		public void onServiceDisconnected(ComponentName name) {
			mLocalServiceBound = false;	
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			LocalBinder binder = (LocalBinder)service;
			mLocalService = binder.getService();
			mLocalServiceBound = true;
		}
	};	
	//--------------------
	
	//------------------Messenger Service
	Messenger mService = null;
	Button mMessengerButton;
	Boolean mMessengerBound=false;
	
	private ServiceConnection mMessengerConn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			if(mMessengerBound){
				mService = null;
				mMessengerBound = false;
			}
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mService = new Messenger(service);
			mMessengerBound = true;	
		}
	};
	
	//发送hello
	public void sayHello(){
		if(!mMessengerBound) return;
		Message mMessage = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
		try {
			mService.send(mMessage);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//---------------------
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		LocalServiceButton = (Button)findViewById(R.id.localservicebutton);
		LocalServiceButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(mTag, "click on Local Service");
				if(mLocalServiceBound){
					int num = mLocalService.getRandomNumber();
					Toast.makeText(MainActivity.this, "number : "+num, Toast.LENGTH_SHORT).show();;					
				}
			}
		});
		
		mMessengerButton = (Button)findViewById(R.id.messengerbutton);
		mMessengerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(mTag, "click on Messenger Service");
				sayHello();	
			}
		});		
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		//绑定到LocalService
		Intent LocalServiceIntent = new Intent(this, LocalService.class);
		bindService(LocalServiceIntent, LocalServiceConn, Context.BIND_AUTO_CREATE);
		
		//绑定到Messenger Service
		Intent MessengerServiceIntent = new Intent(this, MessengerService.class);
		bindService(MessengerServiceIntent,mMessengerConn,Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		//解绑LocalService
		if(mLocalServiceBound){
			unbindService(LocalServiceConn);
			mLocalServiceBound = false;
		}
		
		//解绑Messenger Service
		if(mMessengerBound){
			unbindService(mMessengerConn);
			mMessengerBound = false;
		}
	}	
}
