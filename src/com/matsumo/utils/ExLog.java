/**
 * ExLog Copyright (C) 2012 matsumo All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.matsumo.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Environment;

public class ExLog {
	public static boolean useLog = /*true*/false;
	public static boolean useSD = /*true*/false;
	public static String logFilename = "debug.txt";

	private static String log_filename = Environment.getExternalStorageDirectory().getPath() + "/";

	public static void d(String tag, String message){
		if(useLog) android.util.Log.d(tag, message);
		write_log(message);
	}

	public static void d(String tag, String message, Throwable tr){
		if(useLog) android.util.Log.i(tag, message, tr);
		write_trace(tr);
	}

	static private void write_trace(Throwable e){
		if(!useSD || e == null) return;
		try{
			StringWriter sw = null;
			PrintWriter pw = null;
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String trace = sw.toString();
			write_log(trace);
			pw.close();
			sw.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	static private void write_log(String str){
		if(!useSD || str == null) return;
		try{
			File file = new File(log_filename + logFilename);
			OutputStream os = new FileOutputStream(file, true);
			os.write(("---"+new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime())+"\n").getBytes());
			os.write((str+"\n").getBytes());
			os.write(("------\n").getBytes());
			os.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
/*
通常のLogクラスの代わりにExLogを使用します。
useLogのON/OFFでlogcatへの出力を制御できます。
useSDのON/OFFでSDCARDへの出力を制御できます。

SD出力使うときは、AndriodManifest.xmlに
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
忘れずに!!
*/
