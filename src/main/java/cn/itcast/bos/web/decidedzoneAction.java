package cn.itcast.bos.web;

import javax.annotation.Resource;

import org.aspectj.bridge.MessageWriter;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.ImportResource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.DecidedZone;
import cn.itcast.bos.service.decidedzoneService;

public class decidedzoneAction  extends ActionSupport  implements ModelDriven<DecidedZone>{

	
	//模型注入
	private DecidedZone decidedZone =new DecidedZone();
	public DecidedZone getModel() {
		// TODO Auto-generated method stub
		return decidedZone;
	}
	
	@Resource(name="decidedzoneService")
	private decidedzoneService  decidedzoneserviceIMpl;
	public String   saveOrupdate() {
	decidedzoneserviceIMpl.saveOrupdate(decidedZone);
		return "saveOrupdateSuccess";
	}
	
	//属性驱动 提供set方法
	private int page;
	private int rows;
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	
	public String pageQuery() {
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);
		
		//QBC的离线条件查询
	    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DecidedZone.class);
	    pageRequestBean.setDetachedCriteria(detachedCriteria);
	    PageResponseBean pageResponseBean=decidedzoneserviceIMpl.pageQuery(pageRequestBean);
	    //将结果压入到值栈中
	    ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		return "pageQuerySuccess";
	}
	

}
