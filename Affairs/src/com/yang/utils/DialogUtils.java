package com.yang.utils;

import android.app.AlertDialog;
import android.content.Context;

public class DialogUtils {
	public static void showDialog(Context context,String message){
		new AlertDialog.Builder(context).setMessage(message).create().show();
	}
}
