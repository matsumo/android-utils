/**
 * HoneyToast Copyright (C) 2012 matsumo All rights reserved.
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
/**
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.matsumo.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A toast is a view containing a quick little message for the user. The toast
 * class helps you create and show those. {@more}
 * 
 * <p>
 * When the view is shown to the user, appears as a floating view over the
 * application. It will never receive focus. The user will probably be in the
 * middle of typing something else. The idea is to be as unobtrusive as
 * possible, while still showing the user the information you want them to see.
 * Two examples are the volume control, and the brief message saying that your
 * settings have been saved.
 * <p>
 * The easiest way to use this class is to call one of the static methods that
 * constructs everything you need and returns a new Toast object.
 */
public class HoneyToast {

	/**
	 * Show the view or text notification for a short period of time. This time
	 * could be user-definable. This is the default.
	 * 
	 * @see #setDuration
	 */
	public static final int LENGTH_SHORT = 0;

	/**
	 * Show the view or text notification for a long period of time. This time
	 * could be user-definable.
	 * 
	 * @see #setDuration
	 */
	public static final int LENGTH_LONG = 1;

	private Toast toast;
	private static Drawable iconCache;
	private static final int ICON_PADDING = 4;

	/**
	 * Construct an empty Toast object. You must call {@link #setView} before
	 * you can call {@link #show}.
	 * 
	 * @param context
	 *            The context to use. Usually your
	 *            {@link android.app.Application} or
	 *            {@link android.app.Activity} object.
	 */
	public HoneyToast(Context context) {
		// toast = new Toast(context);
	}

	/**
	 * Show the view for the specified duration.
	 */
	public void show() {
		if (toast != null)
			toast.show();
	}

	/**
	 * Close the view if it's showing, or don't show it if it isn't showing yet.
	 * You do not normally have to call this. Normally view will disappear on
	 * its own after the appropriate duration.
	 */
	public void cancel() {
		if (toast != null)
			toast.cancel();
	}

	/**
	 * Set the view to show.
	 * 
	 * @see #getView
	 */
	public void setView(View view) {
		if (toast != null)
			toast.setView(view);
	}

	/**
	 * Return the view.
	 * 
	 * @see #setView
	 */
	public View getView() {
		if (toast != null)
			return toast.getView();
		return null;
	}

	/**
	 * Set how long to show the view for.
	 * 
	 * @see #LENGTH_SHORT
	 * @see #LENGTH_LONG
	 */
	public void setDuration(int duration) {
		if (toast != null)
			toast.setDuration(duration);
	}

	/**
	 * Return the duration.
	 * 
	 * @see #setDuration
	 */
	public int getDuration() {
		if (toast != null)
			return toast.getDuration();
		return 0;
	}

	/**
	 * Set the margins of the view.
	 * 
	 * @param horizontalMargin
	 *            The horizontal margin, in percentage of the container width,
	 *            between the container's edges and the notification
	 * @param verticalMargin
	 *            The vertical margin, in percentage of the container height,
	 *            between the container's edges and the notification
	 */
	public void setMargin(float horizontalMargin, float verticalMargin) {
		if (toast != null)
			toast.setMargin(horizontalMargin, verticalMargin);
	}

	/**
	 * Return the horizontal margin.
	 */
	public float getHorizontalMargin() {
		if (toast != null)
			return toast.getHorizontalMargin();
		return 0;
	}

	/**
	 * Return the vertical margin.
	 */
	public float getVerticalMargin() {
		if (toast != null)
			return toast.getVerticalMargin();
		return 0;
	}

	/**
	 * Set the location at which the notification should appear on the screen.
	 * 
	 * @see android.view.Gravity
	 * @see #getGravity
	 */
	public void setGravity(int gravity, int xOffset, int yOffset) {
		if (toast != null)
			toast.setGravity(gravity, xOffset, yOffset);
	}

	/**
	 * Get the location at which the notification should appear on the screen.
	 * 
	 * @see android.view.Gravity
	 * @see #getGravity
	 */
	public int getGravity() {
		if (toast != null)
			return toast.getGravity();
		return 0;
	}

	/**
	 * Return the X offset in pixels to apply to the gravity's location.
	 */
	public int getXOffset() {
		if (toast != null)
			return toast.getXOffset();
		return 0;
	}

	/**
	 * Return the Y offset in pixels to apply to the gravity's location.
	 */
	public int getYOffset() {
		if (toast != null)
			return toast.getYOffset();
		return 0;
	}

	/**
	 * Make a standard toast that just contains a text view.
	 * 
	 * @param context
	 *            The context to use. Usually your
	 *            {@link android.app.Application} or
	 *            {@link android.app.Activity} object.
	 * @param text
	 *            The text to show. Can be formatted text.
	 * @param duration
	 *            How long to display the message. Either {@link #LENGTH_SHORT}
	 *            or {@link #LENGTH_LONG}
	 * 
	 */
	public static HoneyToast makeText(Context context, CharSequence text,
			int duration) {
		HoneyToast toast = new HoneyToast(context);
		toast.toast = Toast.makeText(context, text, duration);
		Drawable d = getAppIcon(context);
		if (d != null) {
			LinearLayout v = (LinearLayout) toast.getView();
			v.setOrientation(LinearLayout.HORIZONTAL);
			v.setGravity(Gravity.CENTER_VERTICAL);
			ImageView i = new ImageView(context);
			i.setImageDrawable(d);
			i.setPadding(0, 0, ICON_PADDING, 0);
			v.addView(i, 0);
		}
		return toast;
	}

	/**
	 * Make a standard toast that just contains a text view with the text from a
	 * resource.
	 * 
	 * @param context
	 *            The context to use. Usually your
	 *            {@link android.app.Application} or
	 *            {@link android.app.Activity} object.
	 * @param resId
	 *            The resource id of the string resource to use. Can be
	 *            formatted text.
	 * @param duration
	 *            How long to display the message. Either {@link #LENGTH_SHORT}
	 *            or {@link #LENGTH_LONG}
	 * 
	 * @throws Resources.NotFoundException
	 *             if the resource can't be found.
	 */
	public static HoneyToast makeText(Context context, int resId, int duration)
			throws Resources.NotFoundException {
		return makeText(context, context.getResources().getText(resId),
				duration);
	}

	public static void makeTextAndShow(Context context, int resId, int duration)
			throws Resources.NotFoundException {
		makeText(context, context.getResources().getText(resId), duration)
				.show();
	}

	public static void makeTextAndShow(Context context, CharSequence text,
			int duration) throws Resources.NotFoundException {
		makeText(context, text, duration).show();
	}

	/**
	 * Update the text in a Toast that was previously created using one of the
	 * makeText() methods.
	 * 
	 * @param resId
	 *            The new text for the Toast.
	 */
	public void setText(int resId) {
		if (toast != null)
			toast.setText(resId);
	}

	/**
	 * Update the text in a Toast that was previously created using one of the
	 * makeText() methods.
	 * 
	 * @param s
	 *            The new text for the Toast.
	 */
	public void setText(CharSequence s) {
		if (toast != null)
			toast.setText(s);
	}

	private static Drawable getAppIcon(Context context) {
		if (iconCache != null)
			return iconCache;
		Drawable icon = null;
		PackageManager pm = context.getPackageManager();
		try {
			ApplicationInfo applicationinfo = pm.getApplicationInfo(
					context.getPackageName(), 0);
			icon = pm.getApplicationIcon(applicationinfo);
			if (icon != null)
				icon = resizeIcon(context, icon);
			iconCache = icon;
		} catch (NameNotFoundException e) {
		}
		return icon;
	}

	private static Drawable resizeIcon(Context context, Drawable icon) {
		WindowManager windowmanager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display disp = windowmanager.getDefaultDisplay();
		int w = (int) (28 * Math.min(disp.getWidth(), disp.getHeight()) / 320f);
		Bitmap.Config c = (icon.getOpacity() != PixelFormat.OPAQUE) ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		Bitmap thumb = Bitmap.createBitmap(w, w, c);
		Canvas canvas = new Canvas(thumb);
		canvas.setDrawFilter(new PaintFlagsDrawFilter(Paint.DITHER_FLAG, 0));
		Rect oldBounds = new Rect();
		oldBounds.set(icon.getBounds());
		icon.setBounds(0, 0, w, w);
		icon.draw(canvas);
		icon.setBounds(oldBounds);
		icon = new BitmapDrawable(thumb);
		return icon;
	}
}
