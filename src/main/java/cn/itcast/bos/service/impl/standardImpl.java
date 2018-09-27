package cn.itcast.bos.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.ImportResource;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.Standard;
import cn.itcast.bos.service.standardService;

public class standardImpl  implements standardService  {
    
	@Resource(name="standardDao")
	private GenericDAO<Standard>  standardDao;
	public void saveOrUpdateStandard(Standard standard) {
		standardDao.saveOrUpdateStandard(standard);
	}
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
	    PageResponseBean pageResponseBean = new PageResponseBean();
	    long total = standardDao.findTotalCount(pageRequestBean.getDetachedCriteria());
		pageResponseBean.setTotal(total);   //查出总数
		
		//得出要返回的记录条数
		int maxResults = pageRequestBean.getRows();
		//得出从那一条开始
		int firstResult= (pageRequestBean.getPage() - 1) * pageRequestBean.getRows();
		pageRequestBean.getDetachedCriteria().setProjection(null); // 清除之前 rowCount的投影效果
		List<Standard> data = standardDao.pageQuery(pageRequestBean.getDetachedCriteria(), firstResult, maxResults);
		pageResponseBean.setRows(data);

		return pageResponseBean;
		
	}
	
	
	public void deleteBatch(String[] ids) {
		for (String id : ids) {
			Standard standard = standardDao.findById(id);
			//采用逻辑删除的方式进行管理
			standard.setDeltag("1");   //0 为正常，1标志删除  
			standardDao.update(standard);
		}
	}

	
   

}
