package com.nikolegames;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import com.me.neta.Native;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class AndroidNative implements Native{
	String ALBUM_NAME="Nikole&CO";
	
	private Activity activity;
	
	public AndroidNative(Activity androidApp){
		activity = androidApp;
	}
	
	@Override
	public void openWebPage(String address) {
		Intent intent = new Intent(Intent.ACTION_VIEW, 
			     Uri.parse(address));
		activity.startActivity(intent);
		
	}

	


	@Override
	public void setForEmail(String to, File attachment, String subject) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] {to});
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		Uri uri = Uri.parse("file://" + attachment);
		intent.putExtra(Intent.EXTRA_STREAM, uri);
		activity.startActivity(Intent.createChooser(intent, "Отправка письма..."));		
	}

	@Override
	public void showInterstitial() {
		// TODO Auto-generated method stub
		
	}
	

}
