package cn.itcast.bos.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.domain.bc.Standard;

/**
 * 通用 DAO 设计接口
 * 
 * @author seawind
 * 
 */
public interface GenericDAO<T> {

	/**
	 * 保存
	 * 
	 * @param obj
	 */
	public void save(T obj);

	/**
	 * 修改
	 * 
	 * @param obj
	 * @return 
	 */
	public void update(T obj);

	/**
	 * 删除
	 * 
	 * @param obj
	 */
	public void delete(T obj);

	/**
	 * 根据id 查询
	 * 
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);

	/**
	 * 查询 所有数据
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 条件查询
	 */
	public List<T> findByNamedQuery(String queryName, Object... values); // 根据hql 查询

	public List<T> findByCriteria(DetachedCriteria detachedCriteria); // 面向对象条件查询
    
	/*
	 * 根据条件查出总数
	 */
	public long findTotalCount(DetachedCriteria detachedCriteria);

	public List pageQuery(DetachedCriteria detachedCriteria, int firstResult, int maxResults);

	public void saveOrUpdateStandard(Standard standard);

	public void saveOrUpdateStandard(Staff staff);

	public void saveOrUpdateRegion(Region region);
}
