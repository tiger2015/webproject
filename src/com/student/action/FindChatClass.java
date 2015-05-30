package com.student.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.ChatClassDAO;
import com.dao.JoinRequestDAO;
import com.entity.ChatClass;
import com.entity.JoinRequest;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class FindChatClass extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 679627954313674407L;
	private String className = "";
	private HttpServletRequest request;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) throws IOException {
		this.className =new String(className.getBytes(),"UTF-8");
	}

	public String findChatClass() {
		MySplitePage splitePage = null;
		if(request.getSession().getAttribute("splitepage")==null){
			splitePage=new MySplitePage();						
		}else{
			splitePage=(MySplitePage)request.getSession().getAttribute("splitepage");
		}
		//设置总记录数
		splitePage.setTotalRecords((new ChatClassDAO()).getTotalChatClass("className", className, 1));
		String current=request.getParameter("currentpage");
		//翻页,第一次
		if(current==null)
			splitePage.setPage(1);
		else
			splitePage.setPage(Integer.parseInt(current));
		request.getSession().setAttribute("splitepage", splitePage);
		//根据班级名称查找
		List list = new ChatClassDAO()
				.findByProperty("className", className, 1,splitePage);
		
		request.setAttribute("result", list);
		request.setAttribute("condition", className);
		String userId = request.getSession().getAttribute("userid").toString();
		List request = new JoinRequestDAO().findAllByProperty("studentId",
				userId,null);
		//判断是否申请该班级
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < request.size(); j++)
				if (((ChatClass) list.get(i)).getClassId().equals(
						((JoinRequest) request.get(j)).getJoinRequestId()
								.getChatClass().getClassId())) {
					((ChatClass) list.get(i)).setState(((JoinRequest) request
							.get(j)).getIsAllow());
					break;
				}
		}
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}

}
