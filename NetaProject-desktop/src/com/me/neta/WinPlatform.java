package com.me.neta;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
 
public class WinPlatform implements Platform{

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
	public void setForEmail(String to, File attachment, String text) {
		// TODO Auto-generated method stub
			System.out.println("win::setEmail");
	}

}
