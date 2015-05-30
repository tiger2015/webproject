package com.student.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.MessageDAO;
import com.dao.StudentClassDAO;
import com.entity.Message;
import com.entity.StudentClass;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class FindMessage extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -864576279764972151L;
	private HttpServletRequest request;
	private String classId="";

	public String findMessage() {
		String userId = request.getSession().getAttribute("userid").toString();
		//查找加入的班级
		List list = new StudentClassDAO().findByProperty("studentId", userId,null);
		request.setAttribute("result", list);
		//首次为查到的第一个班级
		if((classId==null||classId.trim().length()==0)&&list.size()>0)
			classId=((StudentClass)list.get(0)).getStudentClassId().getChatClass().getClassId();	
		String current=request.getParameter("currentpage");
		MySplitePage splitePage=null;
		if(request.getSession().getAttribute("splitepage")==null)
			splitePage=new MySplitePage();
		else
			splitePage=(MySplitePage) request.getSession().getAttribute("splitepage");
		splitePage.setTotalRecords((new MessageDAO()).getTotalMessage("classId", classId));
		//翻页
		if(current==null||current.trim().length()==0)
			splitePage.setPage(1);
		else
			splitePage.setPage(Integer.parseInt(current));	
		//根据班级编号查找留言
		List message = new MessageDAO().findAllByProperty("classId", classId,splitePage);
		//获取回复数
		for (int i = 0; i < message.size(); i++)
			((Message) message.get(i)).setResponseCount(((Message) message
					.get(i)).getResponses().size());
		request.setAttribute("messages", message);
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
