package com.lewi.ui;

import com.lewi.anroidtestapp.R;
import com.lewi.service.ServiceActivity;
import com.lewi.sharedpreferences.SharedPreferencesDemoActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button mSPsButton,mServiceButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//使用此按钮跳转到sharedpreferences Demo页面
		mSPsButton = (Button)findViewById(R.id.sharedpreferences);
		mSPsButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,SharedPreferencesDemoActivity.class);
				startActivity(i);
			}		
		});
		
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
	}
}