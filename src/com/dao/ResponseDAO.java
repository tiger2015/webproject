package com.dao;

import java.util.List;

import org.hibernate.Query;

import com.entity.Response;
import com.util.BaseHibernateDAO;
import com.util.MySplitePage;

public class ResponseDAO extends BaseHibernateDAO {
	private final static String CLASSNAME = "Response";
	private final static String TABLENAME = "response";

	public boolean save(Response response) {
		try {
			getSession().beginTransaction();
			getSession().save(response);
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}

	// 根据留言编号查找回复
	public List findByProperty(String propertyName, Object value,
			MySplitePage splitePage) {

		try {
			String queryString = "from " + CLASSNAME + " where " + propertyName
					+ "='" + value + "' order by responseDate desc";
			getSession().beginTransaction();
			Query query = getSession().createQuery(queryString);
			if (splitePage != null && splitePage.getTotalPage() > 0) {
				query.setFirstResult((splitePage.getCurrentPage() - 1)
						* splitePage.getPageSize());
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

	public long getTotalResponse(String propertyName, Object value) {
		long amount = 0;

		try {
			String queryString = "select count(*) from " + CLASSNAME
					+ " where " + propertyName + "='" + value + "'";
			getSession().beginTransaction();
			Query query = getSession().createQuery(queryString);
			amount = (long) query.uniqueResult();
			getSession().getTransaction().commit();
			return amount;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}
	
	public boolean delete(String responseId) {
		try {
			getSession().beginTransaction();
			Response response=(Response) getSession().get(Response.class, responseId);
			if(response!=null)
				getSession().delete(response);
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}
//通过班级和用户编号查找回复
	public List findByChatClassAndUser(String userId,String messageId) {

		try {
			String queryString = "from " + CLASSNAME + " where  userId='" 
					+  userId + "' and messageId='"+messageId+"'";
			getSession().beginTransaction();
			Query query = getSession().createQuery(queryString);
			List list = query.list();
			getSession().getTransaction().commit();
			return list;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}

	}

	
}