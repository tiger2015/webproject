package com.dao;

import java.util.List;

import oracle.net.aso.q;

import org.hibernate.Query;

import com.entity.Student;
import com.util.BaseHibernateDAO;

public class StudentDAO extends BaseHibernateDAO {
	private static final String TABLENAME = "student";
	private static final String CLASSNAME = "Student";

	public boolean save(Student student) {
		try {
			getSession().beginTransaction();
			getSession().save(student);
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}
	
	public boolean delete(String studentId)
	{
		try {
			getSession().beginTransaction();
			Student student=(Student) getSession().get(Student.class, studentId);
			getSession().delete(student);
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}
	
	public List findByProperty(String propertyName,Object value,int flag)
	{
		try {
			getSession().beginTransaction();
			String queryString="";
			//根据学号查找
			if(flag==0)
				queryString="from "+CLASSNAME+" where "+propertyName+"='"+value+"'";
			else 
				queryString="from "+CLASSNAME+" where "+propertyName+" like '%"+value+"%'";
			Query query=getSession().createQuery(queryString);
			List list=query.list();			
			getSession().getTransaction().commit();
			return list;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
		
	}
}
