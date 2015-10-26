package com.lewi.contentprovider;

import java.util.Calendar;

import com.lewi.anroidtestapp.R;
import com.lewi.contentprovider.NotePad.NoteColumns;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * @author lyue
 *
 * 实现功能：
 * 1 获取日历的数据
 * - 通过Intent方式获取系统提供的ContentProvider
 * - 通过ContentResolver对象获取和更新系统提供的ContentProvder
 *
 * 2 获取自己定义的NotePadProvider的数据
 * - 实现了ContentProvider中的Query，Insert，Update，Delete操作
 * - NotePadProvider的实现在另一个app中
 */

public class ContentProviderActivity extends Activity {

	Button mIntentGetButton, mContentP_GetButton, mContentP_SetButton;
	Button mQueryButton,mInsertButton,mUpdateButton,mDeleteButton;
	EditText mEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_contentprovider);
		
		
		mIntentGetButton = (Button)findViewById(R.id.intentgetbutton);
		mIntentGetButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//通过Intent方式获取数据，不需要增加权限
				Calendar beginTime = Calendar.getInstance();
				beginTime.set(2012, 1, 19, 7, 30);
				long startMillis = beginTime.getTimeInMillis();
				
				Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
				builder.appendPath("time");
				
				ContentUris.appendId(builder, startMillis);
				
				Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());
				startActivity(intent);
			}
		});
		
		mContentP_GetButton = (Button)findViewById(R.id.contentR_getbutton);
		mContentP_GetButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//通过contentResolver方式增加数据，需要在Manifest里面增加WRITE_CALENDAR权限
				QueryCalenderData();
				
				
			}
		});
		
		mContentP_SetButton = (Button)findViewById(R.id.contentR_setbutton);
		mContentP_SetButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//通过contentResolver方式增加数据，需要在Manifest里面增加WRITE_CALENDAR权限
				updateCalenderData();
			}
		});
		
		//访问NotePad中定义的Content Provider
		mEditText = (EditText)findViewById(R.id.editText);
		
		mQueryButton = (Button)findViewById(R.id.contentprovider_query_button);
		mQueryButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//通过contentResolver方式访问，需要在Manifest里面增加权限
				QueryNotepadData();
			}
		});
		
		mInsertButton = (Button)findViewById(R.id.contentprovider_insert_button);
		mInsertButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri mUri = NoteColumns.CONTENT_URI;
				String title = mEditText.getText().toString();
				String note = "Use this note for working items";
				long now = System.currentTimeMillis();
				long createDate = now;
				long updateData = now;
				InsertNotepadData(mUri,title,note,createDate,updateData);
			}
		});
		
		mUpdateButton = (Button)findViewById(R.id.contentprovider_update_button);
		mUpdateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String title =mEditText.getText().toString();	
				UpdateNotepadData(title);
				
			}
		});
		
		mDeleteButton = (Button)findViewById(R.id.contentprovider_delete_button);
		mDeleteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String title =mEditText.getText().toString();			
				DeleteNotepadData(title);			
			}
		});
	}
	
	//---------------------------using in Notepad Demo
    private static final String[] NOTEPAD_PROJECTION = new String[] {
        NoteColumns._ID, // 0
        NoteColumns.TITLE, // 1
    };
    

    private void DeleteNotepadData(String title){
		Cursor cur = null;
		ContentResolver cr = getContentResolver();
		Uri uri = NoteColumns.CONTENT_URI;
		String selection = "TITLE=?";
		String[] selectionArgs = new String[]{title};
		cur = cr.query(uri, NOTEPAD_PROJECTION, selection, selectionArgs, null);
		long updateID;
		int n = 0;
		while(cur.moveToNext()){
			updateID = cur.getLong(0);
			Uri uriDelete = ContentUris.withAppendedId(NoteColumns.CONTENT_URI, updateID);
			getContentResolver().delete(uriDelete, null, null);
			n++;
		}
		Toast.makeText(ContentProviderActivity.this, "Delete items : " + n, Toast.LENGTH_SHORT).show();
    }
    
    private void UpdateNotepadData(String title){
		Cursor cur = null;
		ContentResolver cr = getContentResolver();
		Uri uri = NoteColumns.CONTENT_URI;
		String selection = "TITLE=?";
		String[] selectionArgs = new String[]{title};
		cur = cr.query(uri, NOTEPAD_PROJECTION, selection, selectionArgs, null);
		long updateID;
		if(cur.moveToFirst()){
			updateID = cur.getLong(0);
		}else{
			Toast.makeText(ContentProviderActivity.this, "Do not find item with title : " + title, Toast.LENGTH_SHORT).show();
			return;
		}
    	
		ContentValues values = new ContentValues();
		values.put(NoteColumns.TITLE, "unkown");
		Uri uriUpdate = ContentUris.withAppendedId(uri, updateID);
		int row = getContentResolver().update(uriUpdate, values, null, null);
		
		Toast.makeText(ContentProviderActivity.this, "Update row :" + updateID + "\n id = " + updateID, Toast.LENGTH_SHORT).show();
    }
    
    private void InsertNotepadData(Uri uri,String title, String note, long createDate, long updateDate){
    	ContentValues values = new ContentValues();
    	values.put(NoteColumns.TITLE, title);
    	values.put(NoteColumns.NOTE, note);
    	values.put(NoteColumns.CREATED_DATE, createDate);
    	values.put(NoteColumns.MODIFIED_DATE, updateDate);
    	getContentResolver().insert(uri, values);
    	Toast.makeText(ContentProviderActivity.this, "Insert item that title is : " + title + "  successfully", Toast.LENGTH_SHORT).show();
    }
	
	private void QueryNotepadData(){
		Cursor cur = null;
		ContentResolver cr = getContentResolver();
		Uri uri = NoteColumns.CONTENT_URI;
		String selection = "";
		String[] selectionArgs = new String[]{};
		
		cur = cr.query(uri, NOTEPAD_PROJECTION, selection, selectionArgs, null);
		
		
		String output = "";
		
		while(cur.moveToNext()){
			long mID = cur.getLong(0);
			String mTitle = cur.getString(1);
			output = output + "\n mTitle : " + mTitle + " mID : " + mID;
		}

		Toast.makeText(this, "Total number is" + cur.getCount() + "\n" + output , Toast.LENGTH_SHORT).show();
	}
	
	//----------------------------
	public static final String[] EVENT_PROJECTION = new String[]{
			Calendars._ID, //0
			Calendars.ACCOUNT_NAME, //1
			Calendars.CALENDAR_DISPLAY_NAME, //2
			Calendars.OWNER_ACCOUNT //3
	};
		
	private void QueryCalenderData(){
		Cursor cur = null;
		ContentResolver cr = getContentResolver();
		Uri uri = Calendars.CONTENT_URI;
		String selection = "";
		String[] selectionArgs = new String[]{};
		
		cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);
		
		
		String output = null;
		
		while(cur.moveToNext()){
			long calID = cur.getLong(0);
			String accountName = cur.getString(1);
			String calendarDisplayName = cur.getString(2);
			String ownerAccount = cur.getString(3);
			output = output + "\ncalendarDisplayName : " + calendarDisplayName + " calID : " + calID;
		}

		Toast.makeText(this, "Total number is" + cur.getCount() + "\n" + output , Toast.LENGTH_SHORT).show();
			
	}
	
	private void updateCalenderData(){
		long calID = 2;
		ContentValues values = new ContentValues();
		values.put(Calendars.CALENDAR_DISPLAY_NAME, "lewi's Calendar");
		Uri updateUri = ContentUris.withAppendedId(Calendars.CONTENT_URI, calID);
		int rows = getContentResolver().update(updateUri, values, null, null);
		Toast.makeText(this, "Rows updated :" + rows, Toast.LENGTH_SHORT).show();
	}
	
}
