package com.lewi.androidservicesdemo.services;

import com.lewi.androidservicesdemo.MainActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class MessengerService extends Service {
	
	public static final int MSG_SAY_HELLO = 1;
	
    /**
     * 从客户端接收消息的Handler
     */
	class IncomingHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case MSG_SAY_HELLO:
				Toast.makeText(MessengerService.this, "Hello!", Toast.LENGTH_SHORT).show();
				break;
			default:
				super.handleMessage(msg);
			}
		}
	}
	
	final Messenger mMessenger = new Messenger(new IncomingHandler());
	
	 /**
     * 当绑定到服务时，我们向Messager返回接口，
     * 用于向服务发送消息
     */
	@Override
	public IBinder onBind(Intent intent) {
		Log.i(MainActivity.mTag, "------MessengerService binding");
		return mMessenger.getBinder();
	}	
}
