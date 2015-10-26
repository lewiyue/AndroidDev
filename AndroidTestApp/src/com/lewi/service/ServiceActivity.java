package com.lewi.service;

import com.lewi.aidl.myData;
import com.lewi.anroidtestapp.R;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 
 * @author lyue
 *
 * 实现功能：
 *  - 绑定和解绑service
 *  - 获取service的进程id信息
 *  - Service的实现在另一个app进程中
 *
 */

public class ServiceActivity extends Activity {

	Button bindButton,unbindButton,getProcessButton;
	
	myData mMyDataService;
	boolean mIsBound = false;
	
	private final static String ServiceTag = "ServiceActivity";
	
	private ServiceConnection mConn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			mMyDataService=null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mMyDataService = myData.Stub.asInterface(service);
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);
		
		bindButton = (Button)findViewById(R.id.bind);
		bindButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent mIntent = new Intent();
				mIntent.setAction("lewiyue.Service");
				Log.i(ServiceTag, "Action : "+myData.class.getName());
				bindService(mIntent, mConn, Service.BIND_AUTO_CREATE);
				
				Toast.makeText(ServiceActivity.this, "binding success", Toast.LENGTH_SHORT).show();	
				mIsBound = true;
			}
		});
		
		unbindButton = (Button)findViewById(R.id.unbind);
		unbindButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mIsBound){
					unbindService(mConn);
					mIsBound = false;
					Toast.makeText(ServiceActivity.this, "unbinding success", Toast.LENGTH_SHORT).show();	
				}	
			}
		});
		
		getProcessButton = (Button)findViewById(R.id.getValue);
		getProcessButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mIsBound){
					try {
						int mProcessId = mMyDataService.getPid();
						Toast.makeText(ServiceActivity.this, "Service进程号为 ： "+ mProcessId, Toast.LENGTH_SHORT).show();	
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else{
					Toast.makeText(ServiceActivity.this, "请先绑定对应service", Toast.LENGTH_SHORT).show();	
				}
				
			}
		});
	}
}
