package com.teacher.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.ChatClassDAO;
import com.dao.JoinRequestDAO;
import com.dao.MessageDAO;
import com.dao.ResponseDAO;
import com.dao.StudentClassDAO;
import com.entity.ChatClass;
import com.entity.JoinRequest;
import com.entity.JoinRequestId;
import com.entity.Message;
import com.entity.Response;
import com.entity.Student;
import com.entity.StudentClass;
import com.entity.StudentClassId;
import com.entity.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class RemoveStudent extends ActionSupport implements ServletRequestAware{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8107865332494670843L;
	private HttpServletRequest request;
    private String classId;
    private String studentId;
    
    public String removeStudent()
    {
    	
    	StudentClass studentClass=new StudentClass();
    	StudentClassId studentClassId=new StudentClassId();
    	
    	Student student=new Student();
    	student.setStudentId(studentId);
    	studentClassId.setStudent(student);
    	
    	ChatClass chatClass=new ChatClass();
    	chatClass.setClassId(classId);
    	studentClassId.setChatClass(chatClass);
    	studentClass.setStudentClassId(studentClassId);
         //ChatClassDAO chatClassDAO=new ChatClassDAO();
    
    	//从班级中删除
		new StudentClassDAO().delete(studentClass);
    	//班级人数减少
		//new ChatClassDAO().decreaseTotal("classId", classId);
        
    	JoinRequest joinRequest=new JoinRequest();
    	JoinRequestId joinRequestId=new JoinRequestId();
    	joinRequestId.setChatClass(chatClass);
    	joinRequestId.setStudent(student);
    	joinRequest.setJoinRequestId(joinRequestId);
    	//从申请表中删除
    	new JoinRequestDAO().delete(joinRequest);
    	Message message=new Message();
    	message.setChatClass(chatClass);
    	UserInfo userInfo=new UserInfo();
    	userInfo.setUserId(studentId);
    	message.setUserinfo(userInfo);	
        //删除发布信息
       	new MessageDAO().deleteByProperty(message);
       	//删除回复信息    	
        List messages=new MessageDAO().findAllByProperty("classId", classId, null);
       	if(messages!=null&&messages.size()>0){
       		for(int i=0;i<messages.size();i++){
       			List temp=new ResponseDAO().findByChatClassAndUser(studentId, ((Message)messages.get(i)).getMessageId());
       		    if(temp!=null&&temp.size()>0)
       		    	for(int j=0;j<temp.size();j++)
       		    		new ResponseDAO().delete(((Response)temp.get(0)).getResponseId());
       		}
       		}
       	return SUCCESS;
    }
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
}
