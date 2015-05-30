package com.dao;

import java.util.List;

import org.hibernate.Query;

import com.entity.CourseFile;
import com.util.BaseHibernateDAO;
import com.util.MySplitePage;

public class CourseFileDAO extends BaseHibernateDAO {

	private static final String CLASSNAME = "CourseFile";
	private static final String TABLENAME = "coursefile";
//添加课件信息
	public boolean save(CourseFile courseFile) {
		try {
			getSession().beginTransaction();
			getSession().save(courseFile);
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {

			throw re;
		} finally {
			closeSession();
		}
	}

	//根据班级号来查找
	public List findByPropertyName(String propertyName, Object value,MySplitePage splitePage) {
		try {
			String queryString = " from " + CLASSNAME + " where "
					+ propertyName + "='" + value+"'  order by uploadDate desc";
			getSession().beginTransaction();
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
	
	public long getTotalCourseFile(String propertyName, Object value){
		long amount=0;
		try {
			String queryString = " select count(*) from " + CLASSNAME + " where "
					+ propertyName + "='" + value+"'";
			getSession().beginTransaction();
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
	
	public boolean delete(String courseFileId)
	{
		try{
		getSession().beginTransaction();
		CourseFile file=(CourseFile) getSession().get(CourseFile.class, courseFileId);
			if(file!=null)
				getSession().delete(file);
			getSession().getTransaction().commit();
			return true;
		}catch(RuntimeException re){
				return false;		
		}finally{
			closeSession();		
		}	
	}
}
