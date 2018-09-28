package cn.itcast.bos.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.domain.bc.Standard;

public class GenericDAOImpl<T> extends HibernateDaoSupport implements GenericDAO<T> {

	private String className;

	public GenericDAOImpl(String className) {
		this.className = className;
	}

	public void save(T obj) {
		// this.getSession(); // 使用原始 hibernate编程方式
		// this.getHibernateTemplate(); // 使用 Spring 提供模板工具类
		this.getHibernateTemplate().save(obj);
	}

	public void update(T obj) {
		this.getHibernateTemplate().update(obj);
	}

	public void delete(T obj) {
		this.getHibernateTemplate().delete(obj);
	}

	
	// DbUitls new BeanHandler<User>(User.class);
	public T findById(Serializable id) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	public List<T> findAll() {
		return this.getHibernateTemplate().find("from " + className);// 注意空格
	}

	public List<T> findByNamedQuery(String queryName, Object... values) {
		return this.getHibernateTemplate().findByNamedQuery(queryName, values);
	}

	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	public long findTotalCount(DetachedCriteria detachedCriteria) {
	detachedCriteria.setProjection(Projections.rowCount());   //条件查询总数
	List<Long> list = this.getHibernateTemplate().findByCriteria(detachedCriteria);
	return list.get(0);
	}

	public List pageQuery(DetachedCriteria detachedCriteria, int firstResult, int maxResults) {
		return this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
	}

	public void saveOrUpdateStandard(Standard standard) {
		this.getHibernateTemplate().saveOrUpdate(standard);
	}

	public void saveOrUpdateStandard(Staff staff) {
		this.getHibernateTemplate().saveOrUpdate(staff);
		
	}
	


}
