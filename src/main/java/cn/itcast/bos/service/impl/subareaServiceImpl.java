package cn.itcast.bos.service.impl;


import java.util.List;

import javax.annotation.Resource;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.service.subareaService;

public class subareaServiceImpl  implements subareaService{
    
	
	/*
	 * 将subarea 分区的信息保存到数据库中
	 */
	@Resource(name="subareaDao")
	private   GenericDAO<Subarea>   subareaDaoImpl;
	public void saveOrupdate(Subarea subarea) {
	 subareaDaoImpl.saveOrUpdateSubarea(subarea);
	}
	
	/*
	 * 分页查询
	 *  分区条件查询步骤
	 *  5、涉及到多表查询的，不要清除投影效果，不然会造成 分区数据返回不成功
	 */
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
		PageResponseBean pageResponseBean = new PageResponseBean();
        
		// 查询当前页显示数据
		int firstResult = (pageRequestBean.getPage() - 1) * pageRequestBean.getRows(); // 　从哪条开始
		//pageRequestBean.getDetachedCriteria().setProjection(null); // 清除之前 rowCount的投影效果
		int maxResults = pageRequestBean.getRows(); // 返回记录数
		List<Subarea> data = subareaDaoImpl.pageQuery(pageRequestBean.getDetachedCriteria(), firstResult, maxResults);
		pageResponseBean.setRows(data);

		// 满足当前条件，记录总条数
		List<Long> total = subareaDaoImpl.findTotalCount1(pageRequestBean.getDetachedCriteria(),0,1);
		pageResponseBean.setTotal(total.get(0));

		return pageResponseBean;
	}
}
