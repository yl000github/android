package com.yang.affairs;

import com.yang.exception.MyException;
import com.yang.utils.DialogUtils;

import android.content.Context;

public class ExceptionHandler {
	public static void  handle(Context context,MyRun run){
		try {
			run.run();
		} catch (MyException e) { 
			// TODO: handle exception
			e.printStackTrace();
			DialogUtils.showDialog(context, e.toString());
		}
	}
}
