package com.me.neta;

import java.io.File;

public interface Native {
	public void openWebPage(String address);
	

	public void setForEmail(String to, File attachment, String subject);

	
	public void showInterstitial();
}
