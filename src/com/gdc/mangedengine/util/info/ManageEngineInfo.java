package com.gdc.mangedengine.util.info;

public class ManageEngineInfo {
	private String ipDatabase;
	private String portDatabase;
	private String lasIdConsult;
	private String manageName;
	public String getIpDatabase() {
		return ipDatabase;
	}
	public void setIpDatabase(String ipDatabase) {
		this.ipDatabase = ipDatabase;
	}
	public String getPortDatabase() {
		return portDatabase;
	}
	public void setPortDatabase(String portDatabase) {
		this.portDatabase = portDatabase;
	}
	public String getLasIdConsult() {
		return lasIdConsult;
	}
	public void setLasIdConsult(String lasIdConsult) {
		this.lasIdConsult = lasIdConsult;
	}
	public String getManageName() {
		return manageName;
	}
	public void setManageName(String manageName) {
		this.manageName = manageName;
	}
	@Override
	public String toString() {
		return "ManageEngineInfo [ipDatabase=" + ipDatabase + ", portDatabase=" + portDatabase + ", lasIdConsult="
				+ lasIdConsult + ", manageName=" + manageName + "]";
	}
	
	
	
	
	
}
