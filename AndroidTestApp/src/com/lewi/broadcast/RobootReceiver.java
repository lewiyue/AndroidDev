package com.lewi.broadcast;

import com.lewi.tools.Utils;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 * @author lyue
 * 实现功能：
 *  实现了一个BroadcastReceiver， 在这个receiver里面记录了重启设备的次数
 *  此Receiver是在AndroidManifest.xml中注册的
 *  当设备重启时，会发送一个此receiver可以接收到到的广播
 *  也可以通过本app ui上的按钮触发一个此receiver可以接收到到的广播
 *  
 */
public class RobootReceiver extends BroadcastReceiver {

	public final static String mTag = "RobootReceiver";
	
	private SharedPreferences mPrefs;
	
	@Override
	public void onReceive(Context context, Intent intent) {
        
        Log.i(mTag, "onReceive  : --");
        mPrefs = context.getSharedPreferences(Utils.SharedPreferencesName, Context.MODE_PRIVATE);    
        SharedPreferences.Editor ed = mPrefs.edit();
        ed.putInt(Utils.RebootCount, mPrefs.getInt(Utils.RebootCount, 0) + 1);
        ed.commit();    
        Toast.makeText(context, "Received reboot broadcast", Toast.LENGTH_SHORT).show();
	}

}
