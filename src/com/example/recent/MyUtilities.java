package com.example.recent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MyUtilities {
	
	public static Bitmap giveBitmap(byte barr[]){
		return BitmapFactory.decodeByteArray(barr,0,barr.length);
	}
	
}