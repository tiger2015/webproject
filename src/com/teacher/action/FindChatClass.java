package com.teacher.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.ChatClassDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class FindChatClass extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 524703272150575329L;
	private HttpServletRequest request;
	private String condition;

	public String findClasses() {
			MySplitePage splitePage = null;	
			if(request.getSession().getAttribute("splitepage")==null){
				splitePage=new MySplitePage();						
			}else{
				splitePage=(MySplitePage) request.getSession().getAttribute("splitepage");
			}	
			//设置总记录数
			splitePage.setTotalRecords((new ChatClassDAO()).getTotalChatClass("className", condition, 1));
			String current=request.getParameter("currentpage");
			//翻页,第一次
			if(current==null)
				splitePage.setPage(1);
			else
				splitePage.setPage(Integer.parseInt(current));
			request.getSession().setAttribute("splitepage", splitePage);
			//根据名称查询
			List list = new ChatClassDAO().findByProperty("className",
					condition, 1,splitePage);
			request.setAttribute("result", list);
			request.setAttribute("condition", condition);
			return SUCCESS;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
