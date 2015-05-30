package com.dao;

import com.entity.UserInfo;
import com.util.BaseHibernateDAO;

public class UserInfoDAO extends BaseHibernateDAO {
	private static final String TABLENAME = "userinfo";
	private static final String CLASSNAME = "UserInfo";

	// 保存用户的信息
	public boolean save(UserInfo userinfo) {
		try {
			getSession().beginTransaction();
			getSession().save(userinfo);
			getSession().getTransaction().commit();
			return true;

		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}

	// 删除用户信息
	public boolean delete(UserInfo userinfo) {
		try {
			getSession().beginTransaction();
			getSession().delete(userinfo);
			getSession().getTransaction().commit();
			return true;

		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}

	// 修改密码
	public boolean updatePassword(UserInfo userinfo) {
		try {
			getSession().beginTransaction();
			/*
			 * Query query = getSession() .createQuery( "update " + CLASSNAME +
			 * "set userPassword=? where userId=?"); query.setParameter(0,
			 * userinfo.getUserId()); query.setParameter(1,
			 * userinfo.getUserPassword()); query.executeUpdate();
			 */
			UserInfo info = (UserInfo) getSession().get(UserInfo.class,
					userinfo.getUserId());
			info.setUserPassword(userinfo.getUserPassword());
			// getSession().update(info);
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}

	// 通过id查找用户
	public UserInfo findByUserId(String userId) {
		try {
			getSession().beginTransaction();
			UserInfo info = (UserInfo) getSession().get(UserInfo.class, userId);
			getSession().getTransaction().commit();
			return info;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}
	}

	public boolean updateIsOnline(UserInfo userinfo) {
		try {
			getSession().beginTransaction();
			UserInfo info = (UserInfo) getSession().get(UserInfo.class,
					userinfo.getUserId());
			info.setIsOnline(userinfo.getIsOnline());
			getSession().update(info);
			getSession().getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			throw re;
		} finally {
			closeSession();
		}

	}
}
