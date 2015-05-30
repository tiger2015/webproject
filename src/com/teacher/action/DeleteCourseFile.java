package com.teacher.action;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.CourseFileDAO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCourseFile extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6741383054014891506L;
	private String fileId;
	private String fileName;
	private HttpServletRequest request;
	private final static String FILEPATH="/coursefile";
  
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) throws IOException {
		//this.fileName = new String(fileName.getBytes("ISO8859-1"),"UTF-8");
		this.fileName=fileName;
	}

	public String deleteCourseFile()
	{
		ServletContext context = ServletActionContext.getServletContext();
		String current=request.getParameter("currentpage");
		String classId=request.getParameter("classid");
		String dir=context.getRealPath(FILEPATH);
		File file=new File(dir,fileId+fileName);
	//	System.out.println(file.getAbsolutePath());
		if(file.exists())
			file.delete();
		new CourseFileDAO().delete(fileId);
		request.setAttribute("currentpage", current);
		request.setAttribute("classid", classId);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
		
	}

}
