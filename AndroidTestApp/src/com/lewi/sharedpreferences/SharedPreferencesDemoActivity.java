package com.lewi.sharedpreferences;

import com.lewi.anroidtestapp.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 * @author lyue
 *
 * 实现功能：
 * 使用SharedPreferences存储和更新数据，确保数据可以持久化
 *
 */
public class SharedPreferencesDemoActivity extends Activity {

	static final int DAY_VIEW_MODE = 0;
	static final int WEEK_VIEW_MODE = 1;
	
	private SharedPreferences mPrefs;
	private int mCurViewMode;
	
	Button updateButton;
	TextView contentTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sharedpreferences);
		
		mPrefs = getSharedPreferences(null, MODE_PRIVATE);
		mCurViewMode = mPrefs.getInt("view_mode", DAY_VIEW_MODE);
		
		contentTextView = (TextView)findViewById(R.id.textView1);
		if(mCurViewMode==DAY_VIEW_MODE){
			contentTextView.setText("Day View Mode");
		}else{
			contentTextView.setText("Week View Mode");
		}
		
		updateButton = (Button)findViewById(R.id.button1);
		updateButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				if(mCurViewMode==DAY_VIEW_MODE){
					mCurViewMode = WEEK_VIEW_MODE;
					contentTextView.setText("Week View Mode");
				}else{
					mCurViewMode = DAY_VIEW_MODE;
					contentTextView.setText("Day View Mode");
				}
				 
				
				
			}		
		});
		
		System.out.println("onCreate");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		SharedPreferences.Editor ed = mPrefs.edit();
		ed.putInt("view_mode", mCurViewMode);
		ed.commit();
		System.out.println("onPause");
	}
}
