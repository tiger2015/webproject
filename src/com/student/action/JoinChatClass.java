package com.student.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.ChatClassDAO;
import com.dao.JoinRequestDAO;
import com.entity.ChatClass;
import com.entity.JoinRequest;
import com.entity.JoinRequestId;
import com.entity.Student;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class JoinChatClass extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;

	public String joinClass() {
		JoinRequest joinRequest = new JoinRequest();
		String userid = request.getParameter("studentid");
		String classid = request.getParameter("classid");
		String className = request.getParameter("condition");

		ChatClass chatClass = new ChatClass();
		chatClass.setClassId(classid.trim());

		Student student = new Student();
		student.setStudentId(userid.trim());

		JoinRequestId joinRequestId = new JoinRequestId();
		joinRequestId.setChatClass(chatClass);
		joinRequestId.setStudent(student);

		joinRequest.setJoinRequestId(joinRequestId);
		// 加入班级
		new JoinRequestDAO().save(joinRequest);
		MySplitePage splitePage = null;
		if (request.getSession().getAttribute("splitepage") == null) {
			splitePage = new MySplitePage();
		} else {
			splitePage = (MySplitePage) request.getSession().getAttribute(
					"splitepage");
		}
		// 设置总记录数
		splitePage.setTotalRecords((new ChatClassDAO()).getTotalChatClass(
				"className", className, 1));
		String current = request.getParameter("currentpage");
		// 翻页,第一次
		if (current == null)
			splitePage.setCurrentPage(1);
		else
			splitePage.setPage(Integer.parseInt(current));
		request.getSession().setAttribute("splitepage", splitePage);

		List list = new ChatClassDAO().findByProperty("className", className,
				1, splitePage);
		request.setAttribute("result", list);
		request.setAttribute("condition", className);

		String userId = request.getSession().getAttribute("userid").toString();
		List request = new JoinRequestDAO().findAllByProperty("studentId",
				userId, null);
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
		// TODO Auto-generated method stub
		request = arg0;
	}

}
