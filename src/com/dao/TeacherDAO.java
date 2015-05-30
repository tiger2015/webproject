package com.dao;

import java.util.List;

import org.hibernate.Query;

import com.entity.Teacher;
import com.util.BaseHibernateDAO;

public class TeacherDAO extends BaseHibernateDAO {
	private static final String TABLENAME = "teacher";
	private static final String CLASSNAME = "Teacher";

	public boolean save(Teacher teacher) {
		try {
			getSession().beginTransaction();
			getSession().save(teacher);
			getSession().getTransaction().commit();
			return true;

		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}

	}

	public boolean delete(Teacher teacher) {

		try {
			getSession().beginTransaction();
			getSession().delete(teacher);
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}

	public List findByProperty(String propertyName, Object value, int flag) {
		try {
			getSession().beginTransaction();
			String queryString = "";
			if (flag == 0)
				queryString = "from " + CLASSNAME + " where " + propertyName
						+ "='" + value+"'";
			else
				queryString = "from " + CLASSNAME + " where " + propertyName
						+ " like '%" + value + "%'";
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
