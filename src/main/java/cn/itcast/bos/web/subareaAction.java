package cn.itcast.bos.web;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

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
	
	//注入service层
	@Resource(name="subareaService")
	private subareaService   subareaServiceimpl;
	public  String save() {
		subareaServiceimpl.saveOrupdate(subarea);
		return "saveOrUpdateSuccess";
	}
	
	public PageResponseBean  pageQuery() {
		return null;
	}

}
