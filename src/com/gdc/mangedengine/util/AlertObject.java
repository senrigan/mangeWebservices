package com.gdc.mangedengine.util;

import java.util.Date;

public class AlertObject {
	private Date creationTime;
	private long typeAlert;
	private String message;
	
	
	
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public void setTypeAlert(long typeAlert) {
		this.typeAlert = typeAlert;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTypeAlert(){
		return typeAlert;
	}
	
	public String getMessage(){
		return message;
	}
	
	public String getDateFormate(){
		return creationTime.toString();
	}
	
	public Date getDateTime(){
		return this.creationTime;
	}
}
