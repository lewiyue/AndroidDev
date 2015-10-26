package com.lewi.ui;

import com.lewi.anroidtestapp.R;
import com.lewi.asynctask.AsyntaskActivity;
import com.lewi.broadcast.BroadcastActivity;
import com.lewi.contentprovider.ContentProviderActivity;
import com.lewi.intent.IntentActivity;
import com.lewi.service.ServiceActivity;
import com.lewi.sharedpreferences.SharedPreferencesDemoActivity;
import com.lewi.storage.StorageActivity;
import com.lewi.tools.Utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 
 * @author lyue
 *
 * App的主页面，提供了跳转到各个子页面的入口
 * 
 */

public class MainActivity extends Activity {

	Button mServiceButton,mIntentButton,mContentProviderButton, broadcastButton;
	
	Button mSPsButton,mAsyntaskButton,mStorageButton,mProcessButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//使用此按钮跳转到Process Demo页面
		mProcessButton = (Button)findViewById(R.id.Processdemo);
		mProcessButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setComponent(new ComponentName(Utils.packageName, "com.lewi.ProcessActivity"));
				startActivity(i);				
			}
		});		
		
		//使用此按钮跳转到Storage Demo页面
		mStorageButton = (Button)findViewById(R.id.storagedemo);
		mStorageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,StorageActivity.class);
				startActivity(i);				
			}
		});		
		
		//使用此按钮跳转到Async Task Demo页面
		mAsyntaskButton = (Button)findViewById(R.id.asyntaskdemo);
		mAsyntaskButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,AsyntaskActivity.class);
				startActivity(i);				
			}
		});
		
		//使用此按钮跳转到sharedpreferences Demo页面
		mSPsButton = (Button)findViewById(R.id.sharedpreferences);
		mSPsButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,SharedPreferencesDemoActivity.class);
				startActivity(i);
			}		
		});
		
		
		//-----------------------------------------四大组件------------------------------------
		//使用此按钮跳转到Service Demo页面
		mServiceButton = (Button)findViewById(R.id.service);
		mServiceButton.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,ServiceActivity.class);
				startActivity(i);
			}
		});
		
		
		//使用此按钮跳转到Intent Demo页面
		mIntentButton = (Button)findViewById(R.id.intent);
		mIntentButton.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,IntentActivity.class);
				startActivity(i);
			}
		});
		
		//使用此按钮跳转到Content Provider Demo页面
		mContentProviderButton = (Button)findViewById(R.id.contentproviderdemo);
		mContentProviderButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,ContentProviderActivity.class);
				startActivity(i);				
			}
		});

		//使用此按钮跳转到Broadcast Demo页面
		broadcastButton = (Button)findViewById(R.id.broadcastdemo);
		broadcastButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,BroadcastActivity.class);
				startActivity(i);				
			}
		});
		
		
	}
}