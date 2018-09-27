package cn.itcast.bos.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.Standard;

/*
 * 收派标准的接口
 */
public interface standardService {

	//void saveStandard(Standard standard);

	PageResponseBean pageQuery(PageRequestBean pageRequestBean);

	void saveOrUpdateStandard(Standard standard);

	void deleteBatch(String[] ids);

	

}
