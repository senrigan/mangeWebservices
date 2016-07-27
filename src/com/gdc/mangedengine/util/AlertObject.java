package com.gdc.mangedengine.util;

import java.util.Date;

public class AlertObject {
	private Date creationTime;
	private long typeAlert;
	private String message;
	private String idSource;
	private long idAlert;
	
	
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

	public String getIdSource() {
		return idSource;
	}

	public void setIdSource(String idSource) {
		this.idSource = idSource;
	}
	
	

	public long getIdAlert() {
		return idAlert;
	}

	public void setIdAlert(long idAlert) {
		this.idAlert = idAlert;
	}

	@Override
	public String toString() {
		return "AlertObject [creationTime=" + creationTime + ", typeAlert=" + typeAlert + ", message=" + message
				+ ", idSource=" + idSource + ", idAlert=" + idAlert + "]";
	}
	
	
	
	
	
	
}
