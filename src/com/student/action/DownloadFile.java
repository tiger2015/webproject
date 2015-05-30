package com.student.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadFile extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5172719289341593531L;
	private String fileName;
	private String fileId;
	
	public String downloadFile() throws IOException
	{		
		    return SUCCESS;
	}
	
	public InputStream getInputStream() throws IOException {
		ServletContext context = ServletActionContext.getServletContext();
		String dir = context.getRealPath("/coursefile");
		File file=new File(dir + "\\" + fileId + fileName);
		InputStream inputStream=null;
		if(file.exists())
		 inputStream=new FileInputStream(file);
		return inputStream;
	}
	
	
	
	
	public String getFileName() throws IOException {
		this.fileName = new String(fileName.getBytes(), "ISO8859-1");
		return fileName;
	}
	public void setFileName(String fileName) throws IOException {
		//this.fileName = new String(fileName.getBytes("ISO8859-1"), "utf-8");
		this.fileName=fileName;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
}
