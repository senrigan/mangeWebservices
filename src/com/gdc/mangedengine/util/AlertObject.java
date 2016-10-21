package com.gdc.mangedengine.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AlertObject {
	private Date creationTime;
	private Date modTime;
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
		SimpleDateFormat dt=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		return dt.format(creationTime);
	}
	
	public Date getDateTime(){
		return this.creationTime;
	}
	
	public void setModTime(Date modTime){
		this.modTime=modTime;
	}
	
	public Date getModTime(){
		return this.modTime;
	}
	
	
	public String getModTimeFornate(){
		SimpleDateFormat dt=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		return dt.format(modTime);
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
		return "AlertObject [creationTime=" + creationTime + ", modTime=" + modTime + ", typeAlert=" + typeAlert
				+ ", message=" + message + ", idSource=" + idSource + ", idAlert=" + idAlert + "]";
	}

	@Override
	public boolean equals(Object obj) {
		AlertObject alert=(AlertObject)obj;
		return creationTime.equals(alert.creationTime) && modTime.equals(alert.modTime) && 
				(typeAlert==alert.typeAlert) && message.equals(alert.message) && idSource.equals(alert.idSource) && (idAlert==alert.idAlert);
	}

	@Override
	public int hashCode() {
		return creationTime.hashCode()+modTime.hashCode()+message.hashCode()+idSource.hashCode();
	}
	
	
	
	
	
	
	
	
}
