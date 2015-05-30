package com.dao;

import java.util.List;

import oracle.net.aso.q;

import org.hibernate.Query;

import com.entity.JoinRequest;
import com.util.BaseHibernateDAO;
import com.util.MySplitePage;

public class JoinRequestDAO extends BaseHibernateDAO {
	private static final String TABLENAME = "request";
	private static final String CLASSNAME = "JoinRequest";

	// 添加申请信息
	public boolean save(JoinRequest joinRequest) {
		try {
			getSession().beginTransaction();
			getSession().save(joinRequest);
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			// re.printStackTrace();
			throw re;
		} finally {
			closeSession();
		}
	}

	// 删除申请信息
	public boolean delete(JoinRequest joinRequest) {
		try {
			getSession().beginTransaction();
			JoinRequest request = (JoinRequest) getSession().get(
					JoinRequest.class, joinRequest.getJoinRequestId());
			if (request != null)
				getSession().delete(request);
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}

	// 审核申请
	public boolean updateIsAllow(JoinRequest joinRequest) {
		try {
			getSession().beginTransaction();
			Query query = getSession().createQuery(
					"update " + CLASSNAME
							+ " set isAllow=? where studentId=? and classId=?");
			query.setInteger(0, joinRequest.getIsAllow());
			query.setString(1, joinRequest.getJoinRequestId().getStudent()
					.getStudentId());
			query.setString(2, joinRequest.getJoinRequestId().getChatClass()
					.getClassId());
			query.executeUpdate();
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			// System.out.println(re.getMessage());
			throw re;
		} finally {
			closeSession();
		}
	}

	// 根据班号或学号查找申请信息
	public List findAllByProperty(String propertyName, Object value,
			MySplitePage splitePage) {
		try {
			getSession().beginTransaction();
			String queryString = " from " + CLASSNAME + " where "
					+ propertyName + "='" + value
					+ "' order by requestDate desc";
			Query query = getSession().createQuery(queryString);
			;
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

	public long getTotalJoinRequest(String propertyName, Object value) {
		long amount = 0;
		try {
			getSession().beginTransaction();
			String queryString = "select count(*) from " + CLASSNAME
					+ " where " + propertyName + "='" + value + "'";
			Query query = getSession().createQuery(queryString);
			;
			amount = (long) query.uniqueResult();
			getSession().getTransaction().commit();
			return amount;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}

	}

	// 根据申请编号删除申请信息
	public boolean deleteByProperty(String propertyName, Object value) {

		try {
			String queryString = "delete from " + CLASSNAME + " where "
					+ propertyName + "='" + value + "'";
			getSession().beginTransaction();
			int deleteEntities = getSession().createQuery(queryString)
					.executeUpdate();
			getSession().getTransaction().commit();
			return true;

		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}
}
