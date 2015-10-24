package com.jczb.checkpoint.ui.progress;

import android.graphics.Bitmap;

public class RowItem {
	private Bitmap bitmapImage;

	public RowItem(Bitmap bitmap) {
		this.bitmapImage = bitmap;
	}

	public Bitmap getBitmap() {
		return bitmapImage;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmapImage = bitmap;
	}

}