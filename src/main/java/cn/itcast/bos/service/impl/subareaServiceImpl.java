package cn.itcast.bos.service.impl;


import javax.annotation.Resource;

import cn.itcast.bos.dao.GenericDAO;
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

}
