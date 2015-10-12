package com.lewi.intent;

import java.net.URI;

import com.lewi.anroidtestapp.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 
 * @author lyue
 * 实现功能：
 * （1） 通过ACTION_MAIN + CATEGORY_HOME 启动主屏
 * （2） 通过ACTION_SEND 发送Mail
 * （3） 通过ACTION_PICK打开通讯录，并选中一个item，得到返回值
 * （4） 通过ACTION_DIAL拨打指定的号码
 */
public class IntentActivity extends Activity{

	
	Button mHomeScreen, mMailButton,mAddressButton, mDailButton;
	int REQUEST_CONTACT;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_intent);
		
		mHomeScreen = (Button)findViewById(R.id.homescreen);
		mHomeScreen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setAction(Intent.ACTION_MAIN);
				i.addCategory(Intent.CATEGORY_HOME);
				startActivity(i);
			}
		});
		
		
		mMailButton = (Button)findViewById(R.id.mail);
		mMailButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setAction(Intent.ACTION_SEND);
				i.setType("text/plain");
				i.putExtra(Intent.EXTRA_TEXT, "hi team, test text.");
				i.putExtra(Intent.EXTRA_SUBJECT, "Test Mail");
				i= Intent.createChooser(i, "Please choose..");
				startActivity(i);
				
			}
		});
		
		
		mAddressButton = (Button)findViewById(R.id.address);
		mAddressButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setAction(Intent.ACTION_PICK);
				i.setData(ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(i, REQUEST_CONTACT);	
			}
		});
		
		mDailButton = (Button)findViewById(R.id.dail);
		mDailButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setAction(Intent.ACTION_DIAL);
				i.setData(Uri.parse("tel:13726218236"));
				startActivity(i);
				
			}
		});
		
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode != Activity.RESULT_OK) return;
		
		if(requestCode == REQUEST_CONTACT){
			Uri contactUri = data.getData();
			
			String[] queryFilds = new String[]{
					ContactsContract.Contacts.DISPLAY_NAME
			};
			
			Cursor c = this.getContentResolver().query(contactUri, queryFilds, null, null, null);
			
			if(c.getCount() == 0){
				c.close();
				return;
			}
			
			c.moveToFirst();
			Toast.makeText(this, "选中的项为： "+ c.getString(0), Toast.LENGTH_SHORT).show();
			c.close();
		}
		
	}

}
