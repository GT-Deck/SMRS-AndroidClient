package com.decker.smrsremote;

import android.content.Context;
import android.widget.Toast;

public class ToastMessage 
{
	static void showToast(Context context, String message)
	{
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
		toast.show();
	}
}
