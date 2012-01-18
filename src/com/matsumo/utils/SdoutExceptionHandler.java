/**
 * SdoutExceptionHandler Copyright (C) 2012 matsumo All rights reserved.
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

import java.lang.Thread.UncaughtExceptionHandler;

public class SdoutExceptionHandler implements UncaughtExceptionHandler {
	private static final String TAG = "SdoutExceptionHandler";
	private UncaughtExceptionHandler mDefaultUEH;
	public SdoutExceptionHandler() {
		mDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
	}
 
	public void uncaughtException(Thread th, Throwable t) {
		try {
			ExLog.d(TAG, "", t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mDefaultUEH.uncaughtException(th, t);
	}
}
/*
SD出力対応例外ハンドラー
Activity起動時(onCreateの前の方)に
Thread.setDefaultUncaughtExceptionHandler(new SdoutExceptionHandler());
を記載しておくと、例外発生時のスタックトレースがSDカードに出力されます。
 */
