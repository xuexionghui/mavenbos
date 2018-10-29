package cn.itcast.bos.web;
/**
*  @author xuexionghui E-mail:413669152@qq.com
*  @version 创建时间：2018年10月29日 上午11:01:31 
*  
**/

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.qp.NoticeBill;
import cn.itcast.bos.service.NoticeBillService;

public class NoticeBillAction extends ActionSupport implements ModelDriven<NoticeBill>{
	
	
	//模型驱动   接收数据
	private NoticeBill  noticeBill=new NoticeBill();
	@Override
	public NoticeBill getModel() {
		// TODO Auto-generated method stub
		return noticeBill;
	}
	
	
	@Resource(name="noticeBillService")
	private  NoticeBillService   noticeBillServiceImpl;
    
	public String  save() {
		noticeBillServiceImpl.save(noticeBill);
		return "saveNoticeBillSuccess";
	}

	
 
}
