package com.me.neta;

import java.io.File;

public interface Platform {
	public void openWebPage(String address);
	

	public void setForEmail(String to, File attachment, String subject);

}
