package com.gdc.mangedengine.util;

public class Service {
	private long idService;
	private boolean da;
	private boolean sv;
	private String name;
	private String urlDA;
	private String urlSV;
	private long idResource;
	private long lastIdConsult;
	
	public void setName(String name){
		this.name=name;
	}
	
	public long getIdService() {
		return idService;
	}

	public void setIdService(long idService) {
		this.idService = idService;
	}

	public String getName(){
		return this.name;
	}
	public String geturlDA(){
		return this.urlDA;
	}	
	
	public String geturlSV(){
		return this.urlSV;
	}
	
	
	public boolean isEnableDA(){
		return da;
	}
	
	public boolean isEnableSV(){
		return sv;
	}

	public void setDa(boolean da) {
		this.da = da;
	}

	public void setSv(boolean sv) {
		this.sv = sv;
	}

	public void setUrlDA(String urlDA) {
		this.urlDA = urlDA;
	}

	public void setUrlSV(String urlSV) {
		this.urlSV = urlSV;
	}
	
	
	

	public long getIdResource() {
		return idResource;
	}

	public void setIdResource(long idResource) {
		this.idResource = idResource;
	}
	
	

	public long getLastIdConsult() {
		return lastIdConsult;
	}

	public void setLastIdConsult(long lastIdConsult) {
		this.lastIdConsult = lastIdConsult;
	}

	@Override
	public String toString() {
		return "Service [da=" + da + ", sv=" + sv + ", name=" + name + ", urlDA=" + urlDA + ", urlSV=" + urlSV
				+ ", idResource=" + idResource + ", lastIdConsult=" + lastIdConsult + "]";
	}
	
	
	
}
