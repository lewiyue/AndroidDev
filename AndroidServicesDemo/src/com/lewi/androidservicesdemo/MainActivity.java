package com.lewi.androidservicesdemo;

import com.lewi.androidservicesdemo.services.LocalService;
import com.lewi.androidservicesdemo.services.LocalService.LocalBinder;
import com.lewi.androidservicesdemo.services.MessengerService;
import com.lewi.androidservicesdemo.services.StartedService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author lyue
 * 功能：
 * 1 UI界面，用于和用户交互
 * 2 LocalService的交互
 * 3 MessengerService的交互
 * 4 AIDL Service的启动，UI操作部分在另一个app里面
 */
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
	
	//------------------Messenger Service
	Messenger mService = null;
	Button mMessengerButton,mBindButton,mUnBindButton;
	Boolean mMessengerBound=false;
	TextView mCallBackText;
	Messenger mMessenger = new Messenger(new IncomingHandler());
	int n=0;
	
	class IncomingHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case MessengerService.MSG_SAY_HELLO:
					mCallBackText.setText("Received from Servcie : " + msg.arg1);
			default:
					super.handleMessage(msg);
			
			}
		}
	}
	
	private ServiceConnection mMessengerConn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			if(mMessengerBound){
				mService = null;
				mMessengerBound = false;
				mCallBackText.setText("Disconnected.");
			}		
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mService = new Messenger(service);
			mMessengerBound = true;		
			mCallBackText.setText("Attached");
			try {
				Message msg = Message.obtain(null, MessengerService.MSG_REGISTER_CLIENT);
				
				mService.send(msg);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	};
	
	//发送hello
	public void sayHello(){
		if(!mMessengerBound) {
			Toast.makeText(this, "Bind Service First", Toast.LENGTH_SHORT).show();
			return;
		}
		Message mMessage = Message.obtain(null, MessengerService.MSG_SAY_HELLO, n++, 0);
		mMessage.replyTo = mMessenger;
		try {
			mService.send(mMessage);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void doBindService(){
		//绑定到Messenger Service
		Intent MessengerServiceIntent = new Intent(this, MessengerService.class);
		bindService(MessengerServiceIntent,mMessengerConn,Context.BIND_AUTO_CREATE);
	}
	
	void doUnBindService(){
		//解绑Messenger Service
		if(mMessengerBound){
			if(mService!=null){
				Message msg = Message.obtain(null, MessengerService.MSG_UNREGISTER_CLIENT);
				try {
					mService.send(msg);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			unbindService(mMessengerConn);
			mMessengerBound = false;
			mCallBackText.setText("Unbinding.");
		}
	}
		
	//------------------IntentService
	
	Button mIntentButton;
	
	//----------------------
	
	
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
		mCallBackText = (TextView)findViewById(R.id.callbackText);
		
		mBindButton = (Button)findViewById(R.id.bindButton);
		mBindButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				doBindService();
				
			}
		});
		
		mUnBindButton = (Button)findViewById(R.id.unBindButton);
		mUnBindButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				doUnBindService();		
			}
		});
		
		mIntentButton = (Button)findViewById(R.id.startedButton);
		mIntentButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent startedServiceIntent = new Intent(MainActivity.this, StartedService.class);
				startService(startedServiceIntent);		
				Log.i(StartedService.StartedTag, "Trigger button");
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();		
		//绑定到LocalService
		Intent LocalServiceIntent = new Intent(this, LocalService.class);
		bindService(LocalServiceIntent, LocalServiceConn, Context.BIND_AUTO_CREATE);
		
	}

	@Override
	protected void onPause() {
		super.onPause();		
		//解绑LocalService
		if(mLocalServiceBound){
			unbindService(LocalServiceConn);
			mLocalServiceBound = false;
		}	
	}	
}
