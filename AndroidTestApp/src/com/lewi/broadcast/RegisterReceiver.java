package com.lewi.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.util.Log;

/**
 * 
 * @author lyue
 * 实现功能：
 *  实现了一个BroadcastReceiver， 
 *  此Receiver是在程序中通过register动态注册的，
 *  外部的app可以发送ActionName为"com.lewi.broadcast.registerReceiver"的广播到此Recevier中
 *  当收到广播后，此receiver会触发一个通知到通知栏
 */
public class RegisterReceiver extends BroadcastReceiver {

	private final static String mTag = "RegisterReceiver";
	
	@Override
	public void onReceive(Context context, Intent intent) {
        
        Log.i(mTag, "onReceive  : --");
        
        Notification onti = new Notification.Builder(context)
							.setContentTitle("触发了一个通知")
							.setContentText("此通知存放在Receive里面")
							.setSmallIcon(com.lewi.anroidtestapp.R.drawable.ic_launcher)
        					.build();
        NotificationManager notificationmanager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationmanager.notify(0, onti);
	}

}
