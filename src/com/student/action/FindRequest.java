package com.student.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import oracle.net.aso.s;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.JoinRequestDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class FindRequest extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8770325445201661956L;
	private HttpServletRequest request;

	public String findRequest() {
		String userId=request.getSession().getAttribute("userid").toString();
		MySplitePage splitePage=null;
		if(request.getSession().getAttribute("splitepage")==null){
			splitePage=new MySplitePage();
		}else{
			splitePage=(MySplitePage) request.getSession().getAttribute("splitepage");
		}
		splitePage.setTotalRecords((new JoinRequestDAO()).getTotalJoinRequest("studentId", userId));
		String current=request.getParameter("currentpage");
		if(current==null||current.trim().length()==0)
			splitePage.setPage(1);
		else
			splitePage.setPage(Integer.parseInt(current));
		
    	List list=new JoinRequestDAO().findAllByProperty("studentId", userId,splitePage);
        request.setAttribute("result", list);
        request.getSession().setAttribute("splitepage", splitePage);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
