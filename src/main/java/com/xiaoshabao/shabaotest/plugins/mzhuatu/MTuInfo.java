package com.xiaoshabao.shabaotest.plugins.mzhuatu;

import java.io.Serializable;

public class MTuInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String url;
	private String title;
	private String fileNamePath;
	public MTuInfo() {
	}
	public MTuInfo(String url, String fileNamePath) {
		this.url = url;
		this.fileNamePath = fileNamePath;
	}
	
	public void clear(){
		this.url=null;
		this.title=null;
		this.fileNamePath=null;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileNamePath() {
		return fileNamePath;
	}

	public void setFileNamePath(String fileNamePath) {
		this.fileNamePath = fileNamePath;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fileNamePath == null) ? 0 : fileNamePath.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
			
		MTuInfo other = (MTuInfo) obj;
		if (fileNamePath == null) {
			if (other.fileNamePath != null) {
				return false;
			}
		} else if (!fileNamePath.equals(other.fileNamePath)) {
			return false;
		}
			
		if (url == null) {
			if (other.url != null) {
				return false;
			}
				
		} else if (!url.equals(other.url)) {
			return false;
		}
			
		return true;
	}

}
