package com.lewi.sharedpreferences.test;

import android.widget.Button;

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
