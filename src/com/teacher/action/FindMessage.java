package com.teacher.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.ChatClassDAO;
import com.dao.MessageDAO;
import com.entity.ChatClass;
import com.entity.Message;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class FindMessage extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -148204624384535431L;
	private HttpServletRequest request;
	private String classId="";

	public String findMessage() {
		String userId = request.getSession().getAttribute("userid").toString();
		List list = new ChatClassDAO().findByProperty("teacherId", userId, 0,null);
		request.setAttribute("result", list);
		classId=request.getParameter("classId");
		if((classId==null||classId.trim().length()==0)&&list.size()>0){
			classId=((ChatClass)list.get(0)).getClassId();
		}
		String current=request.getParameter("currentpage");
		MySplitePage splitePage=null;
		if(request.getSession().getAttribute("splitepage")==null)
			splitePage=new MySplitePage();
		else
			splitePage=(MySplitePage) request.getSession().getAttribute("splitepage");
		
		splitePage.setTotalRecords((new MessageDAO()).getTotalMessage("classId", classId));
		if(current==null||current.trim().length()==0)
			splitePage.setPage(1);
		else
			splitePage.setPage(Integer.parseInt(current));
		
		List message = new MessageDAO().findAllByProperty("classId", classId,splitePage);
		for (int i = 0; i < message.size(); i++)
			((Message) message.get(i)).setResponseCount(((Message) message
					.get(i)).getResponses().size());
		request.setAttribute("messages", message);
		request.setAttribute("classId", classId);
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
