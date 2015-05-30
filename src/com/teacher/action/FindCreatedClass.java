package com.teacher.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import oracle.net.aso.s;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.ChatClassDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class FindCreatedClass extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest request;

	public String findCreatedClass() {
		String teacherId = request.getSession().getAttribute("userid")
				.toString();
		String flag = request.getParameter("flag");
		if (flag == null || flag.trim().length() == 0) {
			MySplitePage splitePage = null;
			if (request.getSession().getAttribute("splitepage") == null)
				splitePage = new MySplitePage();
			else
				splitePage = (MySplitePage) request.getSession().getAttribute(
						"splitepage");
			// 设置分页数
			splitePage.setTotalRecords((new ChatClassDAO()).getTotalChatClass(
					"teacherId", teacherId, 0));
			String current = request.getParameter("currentpage");
			if (current == null) {
				splitePage.setPage(1);
			} else {
				splitePage.setPage(Integer.parseInt(current));
			}
			List list = new ChatClassDAO().findByProperty("teacherId",
					teacherId, 2, splitePage);
			request.setAttribute("result", list);
			request.getSession().setAttribute("splitepage", splitePage);
		} else {
			List list = new ChatClassDAO().findByProperty("teacherId",
					teacherId, 0, null);
			request.setAttribute("result", list);
		}
		if ("1".equals(flag))
			return "message";
		/*
		 * else if ("2".equals(flag)) return "getmessage";
		 */
		else if ("3".equals(flag))
			return "coursefile";
		else
			return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
