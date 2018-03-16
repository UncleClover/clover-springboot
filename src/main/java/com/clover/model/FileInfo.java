package com.clover.model;

import java.io.Serializable;

public class FileInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String fileExt;
	private String filePath;

	public FileInfo(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
