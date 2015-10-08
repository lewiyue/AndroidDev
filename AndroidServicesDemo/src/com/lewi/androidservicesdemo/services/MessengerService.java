package com.lewi.androidservicesdemo.services;

import com.lewi.androidservicesdemo.MainActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 * @author lyue
 * MessengerService通信：同一个app但不同进程通信
 * Demo功能：
 * 通过UI上的绑定，解绑，发送消息的按钮，可以实现ui和service之间跨进程通信。
 * 数据从UI进程发送到service进程，然后service进程再返回了一个数据到ui进程。
 * 原理：
 * 应用程序组件（客户端）可以通过调用 bindService() 来绑定服务。然后Android系统会调用服务的 onBind() 方法，返回一个用于和服务进行交互的 Messenger中的IBinder。
 * Messenger中的handler用于处理客户端发送过来的消息
 */

public class MessengerService extends Service {
	
	public static final int MSG_REGISTER_CLIENT = 1;
	public static final int MSG_SAY_HELLO = 2;
	public static final int MSG_UNREGISTER_CLIENT= 3;
	
    /**
     * 从客户端接收消息的Handler
     */
	class IncomingHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case MSG_REGISTER_CLIENT:
				Log.i(MainActivity.mTag, "----Accept REGISTER msg");
				break;
			case MSG_UNREGISTER_CLIENT:
				Log.i(MainActivity.mTag, "----Accept UNREGISTER msg");
				break;
			case MSG_SAY_HELLO:
				Toast.makeText(MessengerService.this, "Hello!", Toast.LENGTH_SHORT).show();
				try {
					Messenger mMessenger = msg.replyTo;
					Message mMessage = Message.obtain(null, MSG_SAY_HELLO, msg.arg1, msg.arg2);
					mMessenger.send(mMessage);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
