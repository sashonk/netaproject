package com.me.neta;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class AndroidPlatform implements Platform{
	String ALBUM_NAME="Nikole&CO";
	
	private Activity activity;
	
	public AndroidPlatform(Activity androidApp){
		activity = androidApp;
	}
	
	@Override
	public void openWebPage(String address) {
		Intent intent = new Intent(Intent.ACTION_VIEW, 
			     Uri.parse(address));
		activity.startActivity(intent);
		
	}

	


	@Override
	public void setForEmail(String to, File attachment, String text) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] {to});
		intent.putExtra(Intent.EXTRA_SUBJECT, "письмо Николь");
		intent.putExtra(Intent.EXTRA_TEXT, "письмо Николь");
		Uri uri = Uri.parse("file://" + attachment);
		intent.putExtra(Intent.EXTRA_STREAM, uri);
		activity.startActivity(Intent.createChooser(intent, "Отправка письма..."));		
	}
	

}
