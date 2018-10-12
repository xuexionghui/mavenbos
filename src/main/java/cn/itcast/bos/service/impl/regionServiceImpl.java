package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.domain.bc.Standard;
import cn.itcast.bos.service.regionService;

public class regionServiceImpl  implements regionService{
   
	@Resource(name="regionDao")
	private GenericDAO<Region>  regionDao;
	public void saveOrUpdate(Region region) {
		regionDao.saveOrUpdateRegion(region);
	}
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
		 PageResponseBean pageResponseBean=new PageResponseBean();  //这个是结果回复
		 //根据request中的条件去查询符合的记录总数
		 long totalCount = regionDao.findTotalCount(pageRequestBean.getDetachedCriteria());
		 pageResponseBean.setTotal(totalCount);
		 
		 //找出第一条的记录          (页数-1)*每页的条数
		 int firstResult=(pageRequestBean.getPage()-1)*pageRequestBean.getRows();
		 //清除投影，每次都忘记了
		 pageRequestBean.getDetachedCriteria().setProjection(null);
		 int pageResults=pageRequestBean.getRows();   //每一页的条数
		 List regions = regionDao.pageQuery(pageRequestBean.getDetachedCriteria(), firstResult,pageResults);
	   
		 pageResponseBean.setRows(regions);   //封装数据
		 return pageResponseBean;             //返回数据
	}
	public void deleteBatch(Region region) {
		String id = region.getId();
		String[] ids = id.split(",");
		for (String string : ids) {
			Region region2 = regionDao.findById(id);
			regionDao.delete(region2);
		}
	}
	/*
	 *ajax查询所有的区域
	 */
	public List<Region> findAll() {
		List<Region> regions = regionDao.findAll();
		return regions;
	}
    
}
