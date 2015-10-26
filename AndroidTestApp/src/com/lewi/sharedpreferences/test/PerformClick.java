package com.lewi.sharedpreferences.test;

import android.widget.Button;

/**
 * 
 * @author lyue
 *
 * 使用线程来执行按钮的点击操作
 *
 */
public class PerformClick implements Runnable {

	Button btn;
	
	public PerformClick(Button testButton) {
		// TODO Auto-generated constructor stub
		btn = testButton;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		btn.performClick();
	}

}
