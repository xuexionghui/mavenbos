package cn.itcast.bos.service.impl;



import javax.annotation.Resource;

import org.springframework.context.annotation.ImportResource;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.bc.Standard;
import cn.itcast.bos.service.standardService;

public class standardImpl  implements standardService  {
    
	@Resource(name="standardDao")
	private GenericDAO<Standard>  standardDao;
	public void saveStandard(Standard standard) {
		standardDao.save(standard);
	}
   

}
