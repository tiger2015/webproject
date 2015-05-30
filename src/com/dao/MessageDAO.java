
package com.dao;

import java.util.List;

import oracle.net.aso.q;

import org.hibernate.Query;

import com.entity.Message;
import com.util.BaseHibernateDAO;
import com.util.MySplitePage;

public class MessageDAO extends BaseHibernateDAO {

	private static final String TABLENAME = "message";
	private static final String CLASSNAME = "Message";

	public boolean save(Message message) {
		try {
			getSession().beginTransaction();
			getSession().save(message);
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {

			throw re;
		} finally {
			closeSession();
		}
	}
//根据班级编号查找留言信息
	public List findAllByProperty(String propertyName, Object value,MySplitePage splitePage) {
		try {
			getSession().beginTransaction();
			String queryString = " from " + CLASSNAME + " where "
					+ propertyName + "='" + value + "' order by messageDate desc";
			Query query = getSession().createQuery(queryString);
			if(splitePage!=null&&splitePage.getTotalPage()>0){
				query.setFirstResult((splitePage.getCurrentPage()-1)*splitePage.getPageSize());
				query.setMaxResults(splitePage.getPageSize());
			}
			List list = query.list();
			getSession().getTransaction().commit();
			return list;
		} catch (RuntimeException re) {

			throw re;
		} finally {
			closeSession();
		}
	}

	public long getTotalMessage(String propertyName, Object value){
		long amount=0;
		try {
			getSession().beginTransaction();
			String queryString = "select count(*) from " + CLASSNAME + " where "
					+ propertyName + "='" + value + "'";
			Query query = getSession().createQuery(queryString);
			amount=(long) query.uniqueResult();
			getSession().getTransaction().commit();
			return amount;
		} catch (RuntimeException re) {
		throw re;
		} finally {
			closeSession();
		}
	}
	public boolean delete(Message message) {
		try {
			getSession().beginTransaction();
			Message temp=(Message) getSession().get(Message.class, message.getMessageId());
			getSession().delete(temp);			
			getSession().getTransaction().commit();
			return true;

		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}
	
	public boolean deleteByProperty(Message message)
	{
		try{		
			getSession().beginTransaction();
			String queryString="from "+CLASSNAME+" where userId=? and classId=?";
			Query query=getSession().createQuery(queryString);
			query.setString(0, message.getUserinfo().getUserId());
			query.setString(1, message.getChatClass().getClassId());
			List list=query.list();
			for(int i=0;i<list.size();i++)
				getSession().delete(list.get(i));
			getSession().getTransaction().commit();
			return true;
		}catch(RuntimeException re){
			throw re;
		}finally{
			closeSession();		
		}	
	}
}
