package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.Staff;

public interface staffService {

	void saveOrUpdate(Staff staff);

	PageResponseBean pageQuery(PageRequestBean pageRequestBean);

	void delBatch(String[] ids);

	List<Staff> ajaxStaff();

}
