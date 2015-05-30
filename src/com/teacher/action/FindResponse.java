package com.teacher.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.MessageDAO;
import com.dao.ResponseDAO;
import com.entity.Message;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class FindResponse extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request;

	public String findResponse() {
		String messageId = request.getParameter("messageid");
		if (messageId == null)
			messageId = request.getAttribute("messageid").toString();
		String current=request.getParameter("currentpage");
		MySplitePage splitePage=null;
		if(request.getSession().getAttribute("splitepage")==null)
			splitePage=new MySplitePage();
		else
			splitePage=(MySplitePage) request.getSession().getAttribute("splitepage");
		splitePage.setTotalRecords((new ResponseDAO()).getTotalResponse("messageId",messageId));
		
		if(current==null||current.trim().length()==0)
			splitePage.setPage(splitePage.getCurrentPage());
		else
			splitePage.setPage(Integer.parseInt(current));
		
		List message = new MessageDAO().findAllByProperty("messageId",
				messageId,null);
		List response = new ResponseDAO()
				.findByProperty("messageId", messageId,splitePage);
		// System.out.println(messageId);
		((Message) message.get(0)).setResponses(null);
		request.setAttribute("message", message.get(0));
		request.setAttribute("responses", response);
		request.setAttribute("messageid", messageId);
		request.getSession().setAttribute("splitepage", splitePage);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
