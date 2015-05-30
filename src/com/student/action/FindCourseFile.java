package com.student.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import oracle.net.aso.s;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.ChatClassDAO;
import com.dao.CourseFileDAO;
import com.dao.StudentClassDAO;
import com.entity.ChatClass;
import com.entity.StudentClass;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class FindCourseFile extends ActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -366800078076647246L;
	private HttpServletRequest request;
    private String classId="";
	public String findCourseFile() {
		String userId = request.getSession().getAttribute("userid").toString();
		// 找到学生加入的班级
		/*
		 * List list = new StudentClassDAO().findByProperty("studentId",
		 * userId); Set teacherId = new HashSet(); List coursefile = new
		 * ArrayList(); for (int i = 0; i < list.size(); i++)
		 * teacherId.add(((StudentClass) list.get(i)).getStudentClassId()
		 * .getChatClass().getTeacher().getTeacherId());
		 * //System.out.println(teacherId.size()); for(Iterator
		 * it=teacherId.iterator();it.hasNext();){ List temp=new
		 * CourseFileDAO().findByPropertyName("teacherId",
		 * it.next().toString()); coursefile.addAll(temp); }
		 */
         //查找所加入的班级
/*		List result = new StudentClassDAO().findByProperty("studentId", userId);
		request.setAttribute("result", result);
		//选择班级
		String classId = request.getParameter("classid");
		//System.out.println(classId);
		if (classId == null) {
			List list = new StudentClassDAO().findByProperty("studentId", userId);
			if(list.size()>0)
				classId=((StudentClass)list.get(0)).getStudentClassId().getChatClass().getClassId();
			else
				classId=" ";
		}
		//如果有加入的班级则查找对应的班级的课件
		if(result.size()>0){
		List list = new ChatClassDAO().findByProperty("classId", classId, 0);
		List coursefile = new CourseFileDAO().findByPropertyName("teacherId",
				((ChatClass) list.get(0)).getTeacher().getTeacherId(),classId);
		request.setAttribute("coursefile", coursefile);	
		}else{
			request.setAttribute("coursefile", null);	
		}	*/
		//查找所加入的班级
		List result = new StudentClassDAO().findByProperty("studentId", userId,null);
		request.setAttribute("result", result);
		//第一次
		if((classId==null||classId.trim().length()==0)&&result.size()>0){	
			classId=((StudentClass)result.get(0)).getStudentClassId().getChatClass().getClassId();
		}
		MySplitePage splitePage=null;
		if(request.getSession().getAttribute("splitepage")==null)
			splitePage=new MySplitePage();
		else
			splitePage=(MySplitePage) request.getSession().getAttribute("splitepage");
		splitePage.setTotalRecords((new CourseFileDAO()).getTotalCourseFile("classId",classId));	
		String current=request.getParameter("currentpage");
		//翻页
		if(current==null||current.trim().length()==0)
			splitePage.setPage(1);
		else
			splitePage.setPage(Integer.parseInt(current));
		//查找课件信息
		List coursefile = new CourseFileDAO().findByPropertyName("classId",classId,splitePage);
		request.setAttribute("coursefile", coursefile);	
		request.setAttribute("classid", classId);
		request.getSession().setAttribute("splitepage", splitePage);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

}
