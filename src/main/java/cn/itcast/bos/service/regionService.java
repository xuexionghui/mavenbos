package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.Region;

public interface regionService {

	void saveOrUpdate(Region region);

	PageResponseBean pageQuery(PageRequestBean pageRequestBean);

	void deleteBatch(Region region);

	List<Region> findAll();

}
