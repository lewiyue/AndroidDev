package com.lewi.sharedpreferences.test;

import com.lewi.anroidtestapp.R;
import com.lewi.sharedpreferences.SharedPreferencesDemoActivity;

import android.content.Intent;
import android.os.SystemClock;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.Smoke;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class InstrumentationTest extends InstrumentationTestCase {

	SharedPreferencesDemoActivity mActivity = null;
	Button testButton;
	TextView resultTextView;
	
	private static final String tag = "notepad";
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		
		Intent intent = new Intent();
		intent.setClassName("com.lewi.anroidtestapp", SharedPreferencesDemoActivity.class.getName());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		mActivity = (SharedPreferencesDemoActivity) getInstrumentation().startActivitySync(intent);
		testButton = (Button)mActivity.findViewById(R.id.button1);
		resultTextView = (TextView)mActivity.findViewById(R.id.textView1);
	}
	
	@Smoke
	public void testButton(){
		for(int i=0;i < 4; i ++){
			getInstrumentation().runOnMainSync(new PerformClick(testButton));
			SystemClock.sleep(1000);
			Log.i(tag, "Click button "+i);
		}
		assertEquals("Week View Mode",resultTextView.getText().toString());
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		mActivity.finish();
		super.tearDown();
	}

}
