package com.teacher.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadFile extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3562101179038227010L;
	private HttpServletRequest request;
	private String fileName;
	private String fileId;

	public String downloadFile() {
	//	System.out.println(fileName);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public InputStream getInputStream() throws IOException {
		ServletContext context = ServletActionContext.getServletContext();
		//String str = request.getParameter("filename");
		//if (str != null)
		//	fileName = str;
		String dir = context.getRealPath("/coursefile");
		//String fileId = request.getParameter("fileid");
		//fileName = new String(fileName.getBytes("ISO8859-1"), "utf-8");
		// System.out.println(fileName);
		 //System.out.println(dir + "\\" + fileId + fileName);
		//InputStream inputStream=context.getResourceAsStream(dir + "\\" + fileId + fileName);
		//String fileName = new String(this.fileName.getBytes(), "UTF-8");
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
		//this.fileName = new String(fileName.getBytes(), "utf-8");
		this.fileName=fileName;
		System.out.println(fileName);
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

}
