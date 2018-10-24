package cn.itcast.bos.service;

import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.DecidedZone;

public interface decidedzoneService {

	void saveOrupdate(DecidedZone decidedZone);

	PageResponseBean pageQuery(PageRequestBean pageRequestBean);

}
