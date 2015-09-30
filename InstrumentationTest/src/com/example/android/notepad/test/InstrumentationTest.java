package com.example.android.notepad.test;


import com.lewi.sharedpreferences.SharedPreferencesDemoActivity;

import android.content.Intent;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.Smoke;
import android.widget.Button;

public class InstrumentationTest extends InstrumentationTestCase {

	SharedPreferencesDemoActivity mActivity = null;
	Button testButton;
	
	private static final String tag = "notepad";
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		
		Intent intent = new Intent();
		intent.setClassName("com.lewi.anroidtestap", SharedPreferencesDemoActivity.class.getName());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		mActivity = (SharedPreferencesDemoActivity) getInstrumentation().startActivitySync(intent);
	}
	
	@Smoke
	public void testButton(){
		Runnable runner;
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

}
