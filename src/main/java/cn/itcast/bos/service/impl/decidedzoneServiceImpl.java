package cn.itcast.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.DecidedZone;
import cn.itcast.bos.service.decidedzoneService;

public class decidedzoneServiceImpl   implements decidedzoneService {
   
	
	@Resource(name="decidedzoneDao")
	private GenericDAO<DecidedZone>     decidedzoneDaoimpl;

	public void saveOrupdate(DecidedZone decidedZone) {
		decidedzoneDaoimpl.saveOrUpdateDecidedzone(decidedZone);
	}

	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
		PageResponseBean pageResponseBean=new PageResponseBean();
		
		int firstResult=(pageRequestBean.getPage()-1)*pageRequestBean.getRows();
		int maxResults=pageRequestBean.getRows();
		List<DecidedZone> decidedZones = decidedzoneDaoimpl.pageQuery(pageRequestBean.getDetachedCriteria(), firstResult, maxResults);
		pageResponseBean.setRows(decidedZones);
		
		List<Long>  total=decidedzoneDaoimpl.findTotalDecidedZoneTotal(pageRequestBean.getDetachedCriteria(),0,1);
		pageResponseBean.setTotal(total.get(0));
		return pageResponseBean;
	}

	

	
}
