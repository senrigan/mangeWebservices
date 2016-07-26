package com.gdc.mangedengine.util;

public class Service {
	private boolean da;
	private boolean sv;
	private String name;
	private String urlDA;
	private String urlSV;
	
	public void setName(String name){
		this.name=name;
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
	
	
	
}
