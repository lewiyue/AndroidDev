package com.lewi.broadcast;

import com.lewi.anroidtestapp.R;
import com.lewi.tools.Utils;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 
 * @author lyue
 * 功能1： UI界面
 * 	一个按钮 触发统计重启的次数
 * 	一个按钮 发送一个广播
 * 功能2：动态注册和注销RegisterReceiver
 *
 */
public class BroadcastActivity extends Activity {

	private final static String mTag = "BroadcastActivity";
	Button button1,button2;
	
	private SharedPreferences mPrefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boardcast);
		
		button1 = (Button)findViewById(R.id.boardcast_button1);
		button1.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				mPrefs = getSharedPreferences(Utils.SharedPreferencesName,MODE_PRIVATE);
				int count = mPrefs.getInt(Utils.RebootCount, 100);
				Toast.makeText(BroadcastActivity.this, "Reboot Count is : "+ count, Toast.LENGTH_SHORT).show();
			}		
		});
		
		button2 = (Button)findViewById(R.id.boardcast_button2);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Utils.BoardCastActionName);
				sendBroadcast(i);
				
				Log.i(RobootReceiver.mTag, "Send Broadcast");
			}
		});
		
		//动态注册一个boardcast receiver
		IntentFilter filter = new IntentFilter(Utils.RegisterActionName);
		registerReceiver(mRegisterReceiver, filter);
		Log.i(mTag, "registerReceiver");
		
	}
	
	private RegisterReceiver mRegisterReceiver = new RegisterReceiver();

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mRegisterReceiver);
		Log.i(mTag, "unregisterReceiver");
	}
	
}
