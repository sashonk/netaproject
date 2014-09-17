package com.me.neta;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
 
public class WinNative implements Native{

	@Override
	public void openWebPage(String address) {
		if(Desktop.isDesktopSupported())
		{
			try{
				Desktop.getDesktop().browse(new URI(address));
			}
			catch(Exception ex){
				System.err.println(ex);
			}
		}
	
	}



	@Override
	public void setForEmail(String to, File attachment,String subject) {
		// TODO Auto-generated method stub
		if(Desktop.isDesktopSupported())
		{
			try {
				Desktop.getDesktop().mail(new URI("mailto:"+to));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException();
			} 
		}
	}



	@Override
	public void showInterstitial() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean showLock() {
		// TODO Auto-generated method stub
		return false;
	}

}
