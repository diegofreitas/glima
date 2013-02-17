package br.com.sistemaweb.persistence.type;

import java.io.File;

public class WebImage {

	private File image;
	private String realPath;
	private String relativePath;
	
	public void setImage(File image) {
		this.image = image;
	}
	public File getImage() {
		return image;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	public String getRealPath() {
		return realPath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	public String getRelativePath() {
		return relativePath;
	}
	
	
}
