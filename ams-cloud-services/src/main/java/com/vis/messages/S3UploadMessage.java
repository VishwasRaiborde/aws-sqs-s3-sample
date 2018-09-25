
package com.vis.messages;


public class S3UploadMessage {
	
	
	String fileReference;
	String path;
	
	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	/**
	 * @return the fileReference
	 */
	public String getFileReference() {
		return fileReference;
	}



	/**
	 * @param fileReference the fileReference to set
	 */
	public void setFileReference(String fileReference) {
		this.fileReference = fileReference;
	}


	
}
