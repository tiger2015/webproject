package com.dao;

import java.util.List;

import org.hibernate.Query;

import com.entity.ChatClass;
import com.util.BaseHibernateDAO;
import com.util.MySplitePage;

public class ChatClassDAO extends BaseHibernateDAO {
	private static final String TABLENAME = "chatclass";
	private static final String CLASSNAME = "ChatClass";

	// 添加班级信息
	public boolean save(ChatClass chatClass) {
		try {
			getSession().beginTransaction();
			getSession().save(chatClass);
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}

	// 删除班级信息
	public boolean delete(String chatClassId) {
		try {
			getSession().beginTransaction();
			// ChatClass chatClass=new ChatClass();
			// chatClass.setClassId(chatClassId);
			ChatClass chatClass = (ChatClass) getSession().get(ChatClass.class,
					chatClassId);
			// System.out.println(chatClass.getClassId());
			if (chatClass != null)
				getSession().delete(chatClass);
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			// System.out.println(re.getMessage());
			throw re;
		} finally {
			closeSession();
		}
	}

	// 分页查询
	public List findByProperty(String propertyName, Object value, int flag,
			MySplitePage splitePage) {
		try {
			getSession().beginTransaction();
			String queryString = "";
			// 根据编号查找
			if (flag == 0 || flag == 2) {
				queryString = "from " + CLASSNAME + " where " + propertyName
						+ "='" + value + "' order by createDate desc";
			} else
				// 根据名称模糊查询
				queryString = "from " + CLASSNAME + " where " + propertyName
						+ " like '%" + value + "%'"
						+ " order by createDate desc";
			Query query = getSession().createQuery(queryString);
			// 根据教师编号查找或者模糊查询
			if ((flag == 1 || flag == 2) && splitePage != null
					&& splitePage.getTotalPage() > 0) {
				query.setFirstResult(splitePage.getPageSize()
						* (splitePage.getCurrentPage() - 1));
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

	public long getTotalChatClass(String propertyName, Object value, int flag) {
		long amount = 0;
		try {
			getSession().beginTransaction();
			String queryString = "";
			// 根据编号查找
			if (flag == 0)
				queryString = "select count(*) from " + CLASSNAME + " where "
						+ propertyName + "='" + value + "'";
			else
				// 根据名称模糊查询
				queryString = "select count(*) from " + CLASSNAME + " where "
						+ propertyName + " like '%" + value + "%'";
			Query query = getSession().createQuery(queryString);
			amount = (long) query.uniqueResult();
			return amount;
		} catch (RuntimeException re) {
			return 0;
		} finally {
			closeSession();
		}
	}

	// 更改班级加入人数
	public boolean updateTotal(String properytName, Object value) {
		try {
			getSession().beginTransaction();
			String queryString = "update " + CLASSNAME
					+ " set total=total+1 where " + properytName + "='" + value
					+ "'";
			Query query = getSession().createQuery(queryString);
			query.executeUpdate();
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			return false;
		} finally {
			closeSession();
		}
	}

	// 减少人数
	public boolean decreaseTotal(String properytName, Object value) {
		try {
			getSession().beginTransaction();
			String queryString = "update " + CLASSNAME
					+ " set total=total-1 where " + properytName + "='" + value
					+ "'";
			Query query = getSession().createQuery(queryString);
			query.executeUpdate();
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			return false;
		} finally {
			closeSession();
		}
	}

	// 更新班级信息
	public boolean updateChatClass(ChatClass chatClass) {
		try {
			getSession().beginTransaction();
			ChatClass temp = (ChatClass) getSession().get(ChatClass.class,
					chatClass.getClassId());
			if (temp != null) {
				temp.setCapacity(chatClass.getCapacity());
				temp.setClassDescribe(chatClass.getClassDescribe());
				temp.setClassName(chatClass.getClassName());
				getSession().saveOrUpdate(temp);
			}
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			return false;
		} finally {
			closeSession();
		}
	}
}
