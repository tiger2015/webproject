package com.dao;

import java.util.List;

import oracle.net.aso.s;

import org.hibernate.Query;

import com.entity.StudentClass;
import com.util.BaseHibernateDAO;
import com.util.MySplitePage;

public class StudentClassDAO extends BaseHibernateDAO {
	private static final String TABLENAME = "studentclass";
	private static final String CLASSNAME = "StudentClass";

	public boolean save(StudentClass studentClass) {
		try {
			getSession().beginTransaction();
			getSession().save(studentClass);
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}

	public boolean delete(StudentClass studentClass) {

		try {
			getSession().beginTransaction();
			StudentClass temp = (StudentClass) getSession().get(
					StudentClass.class, studentClass.getStudentClassId());
			if (temp != null) {
				getSession().delete(temp);
				getSession().getTransaction().commit();
			}
			return true;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}

	// 根据班级编号或学号查找加入的学生信息
	public List findByProperty(String propertyName, Object value,
			MySplitePage splitePage) {
		try {
			getSession().beginTransaction();
			String queryString = " from " + CLASSNAME + " where "
					+ propertyName + "='" + value + "' order by joinDate desc";
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

	public long getTotalStudentClass(String propertyName, Object value) {
		long amount = 0;
		try {
			getSession().beginTransaction();
			String queryString = "select count(*) from " + CLASSNAME
					+ " where " + propertyName + "='" + value
					+ "'";
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
}
