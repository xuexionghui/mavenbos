package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.domain.bc.Standard;
import cn.itcast.bos.service.staffService;

public class staffServiceImpl implements staffService {
   
	@Resource(name="staffDao")
	private   GenericDAO<Staff>  staffDao;
 	public void saveOrUpdate(Staff staff) {
	staffDao.saveOrUpdateStandard(staff);
	}
 	
 	/*
 	 * 分页查询取派员数据
 	 * @see cn.itcast.bos.service.staffService#pageQuery(cn.itcast.bos.domain.PageRequestBean)
 	 */
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
		PageResponseBean pageResponseBean=new PageResponseBean();   //新建一个对象
		//查询符合条件的记录总数
		long totalCount = staffDao.findTotalCount(pageRequestBean.getDetachedCriteria());  //根据设置的条件去数据库中查询数据
		pageResponseBean.setTotal(totalCount);
		int maxResult=pageRequestBean.getRows();   //获得要返回的记录条数
		//从哪一条记录开始
		int firstResult=(pageRequestBean.getPage()-1)*pageRequestBean.getRows(); 
		
		//去数据库中查询数据
		List staffs= staffDao.pageQuery(pageRequestBean.getDetachedCriteria(), firstResult, maxResult);
		pageResponseBean.setRows(staffs);
		return pageResponseBean; 
	}

	public void delBatch(String[] ids) {
		for (String string : ids) {
			Staff staff = staffDao.findById(string);    //根据id取周到这些取派员
			staff.setDeltag("1");    //"1"代表改取派员已经被删除
		}
		
	}

	

}
