package com.lewi.template;

import com.lewi.anroidtestapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 
 * @author lyue
 * 
 * 用于创建新的Activity的模板
 * - 增加后，需要在AndroidManifest中增加对应的Activity声明
 * 
 */
public class TemplateActivity extends Activity {

	private final static String mTag = "TemplateActivity";
	Button button1,button2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_template_linearlayout);
		
		button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				 
			}		
		});
		
		button2 = (Button)findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
