package cn.itcast.bos.web;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.service.subareaService;

public class subareaAction  extends ActionSupport implements ModelDriven<Subarea>{
   
	private Logger log=Logger.getLogger(subareaAction.class);
	//模型驱动 封装数据
	private Subarea subarea= new Subarea();
	public Subarea getModel() {
		return subarea;
	}
	
	//属性驱动，提供set方法
	private int page;
	private int rows;
	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	// 封装PageRequestBean
	public PageRequestBean initPageRequestBean(DetachedCriteria detachedCriteria) {
		// 将分页查询参数 ，封装 PageRequestBean 对象中
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);

		pageRequestBean.setDetachedCriteria(detachedCriteria);
		return pageRequestBean;
	}
	
	//注入service层
	@Resource(name="subareaService")
	private subareaService   subareaServiceimpl;
	public  String save() {
		subareaServiceimpl.saveOrupdate(subarea);
		return "saveOrUpdateSuccess";
	}
	
	public  String   pageQuery() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		PageRequestBean pageRequestBean = initPageRequestBean(detachedCriteria);
		PageResponseBean pageResponseBean=subareaServiceimpl.pageQuery(pageRequestBean);
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		return "pageQuerySuccess";
	}

}
