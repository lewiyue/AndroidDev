package com.lewi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import com.lewi.anroidtestapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 
 * @author lyue
 * 实现功能：
 * 	通过Runtime实例执行一条命令，并获取到执行此命令的process对象
 * 	执行了2条命令，一条是启动另一个进程，一条是查看某个目录
 */
public class ProcessActivity extends Activity {

	private final static String mTag = "ProcessActivity";
	Button button1,button2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_process);
		
		button1 = (Button)findViewById(R.id.processbutton1);
		button1.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {

						//String commandStr = "am start -n com.lewi.androidservicesdemo/.MainActivity";
						//package name为com.example.android.notepad, class为包名下的NotesList
						String commandStr = "am start -n com.example.android.notepad/.NotesList";
						try {
							Process process = Runtime.getRuntime().exec(commandStr);
							
							InputStreamReader ir = new InputStreamReader(process.getInputStream());
							LineNumberReader input = new LineNumberReader(ir);
							
							String line;
							String output ="";
							
							while((line = input.readLine())!=null){
								output = output + line;
							}
							
							Log.i(mTag, "output : "+output);
						} catch (IOException e) {
							
						}
			}		
		});
		
		button2 = (Button)findViewById(R.id.processbutton2);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String commandStr = "ls /mnt";
				try {
					Process process = Runtime.getRuntime().exec(commandStr);
					
					InputStreamReader ir = new InputStreamReader(process.getInputStream());
					LineNumberReader input = new LineNumberReader(ir);
					
					String line;
					String output ="";
					
					while((line = input.readLine())!=null){
						output = output + line +"\n";
					}
					
					Toast.makeText(ProcessActivity.this, "ls /mnt : \n" + output, Toast.LENGTH_SHORT).show();
				} catch (IOException e) {
					Toast.makeText(ProcessActivity.this, "Process does not exit", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
