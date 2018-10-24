package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.Subarea;

public interface subareaService {

	void saveOrupdate(Subarea subarea);

	PageResponseBean pageQuery(PageRequestBean pageRequestBean);

	List<Subarea> list();

	

}
