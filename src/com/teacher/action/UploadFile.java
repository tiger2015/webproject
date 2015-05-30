package com.teacher.action;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.ChatClassDAO;
import com.dao.CourseFileDAO;
import com.entity.ChatClass;
import com.entity.CourseFile;
import com.entity.Teacher;
import com.opensymphony.xwork2.ActionSupport;

public class UploadFile extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1181435567880092916L;
	private HttpServletRequest request;
	private File course;
	private String courseFileName;
	private String courseContentType;
	private String desciption;
	private String fileId;
	private String classId;

	public String uploadFile() {
		ServletContext context = ServletActionContext.getServletContext();
		// System.out.println(courseFile.getCourseName()+"\n"+courseFile.getCourseFileId());
		if (course != null) {
			String dir = context.getRealPath("/coursefile");
			//System.out.println(dir);
			//System.out.println(courseFileName);
		//	System.out.println(courseContentType);
			File file = new File(dir, fileId+courseFileName);
			course.renameTo(file);
			CourseFile courseFile=new CourseFile();
			courseFile.setCourseFileId(fileId);
			//courseFileName=courseFileName.substring(0, courseFileName.lastIndexOf('.'));
			courseFile.setCourseName(courseFileName);
			String userId=request.getSession().getAttribute("userid").toString();
			//设置教师
			Teacher teacher=new Teacher();
			teacher.setTeacherId(userId);
			courseFile.setTeacher(teacher);
			//设置班级
			ChatClass chatClass=new ChatClass();
			chatClass.setClassId(classId);
			courseFile.setChatClass(chatClass);
		
			new CourseFileDAO().save(courseFile);
			return SUCCESS;
		}else{
			
			
			 return INPUT;
			}
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}



	public File getCourse() {
		return course;
	}

	public void setCourse(File course) {
		this.course = course;
	}

	public String getCourseFileName() {
		return courseFileName;
	}

	public void setCourseFileName(String courseFileName) {
		this.courseFileName = courseFileName;
	}

	public String getCourseContentType() {
		return courseContentType;
	}

	public void setCourseContentType(String courseContentType) {
		this.courseContentType = courseContentType;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

}
