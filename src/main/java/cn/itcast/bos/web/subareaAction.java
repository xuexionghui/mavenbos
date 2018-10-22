package cn.itcast.bos.web;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

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
		/*
		 * 使用QBC的方式进行多条件的查询     Query By Criteria   API提供了检索对象的另一种方式
		 *    单表的查询可以直接添加属性，如果是多表的查询，必须创建别名，进行表名关联，这是QBC的强制规定
		 */
		if(subarea.getAddresskey()!=null&&subarea.getAddresskey().trim().length()>0) {
			detachedCriteria.add(Restrictions.like("addresskey", "%"+subarea.getAddresskey()+"%"));//添加关键词的查询
		}
		
		if (subarea.getDecidedZone()!=null&&subarea.getDecidedZone().getId()!=null&&subarea.getDecidedZone().getId().trim().length()>0) {
			detachedCriteria.add(Restrictions.eq("decidedzone", subarea.getDecidedZone()));  //比较id值，其实就是比较两个对象
		}
		
		//QBC的多表查询，必须先创建别名，进行表名的关联，不然查询不到结果
		if(subarea.getRegion()!=null) {
			 //创建别名关联
			detachedCriteria.createAlias("region", "r");    
			//进行省市区的判断
			if(subarea.getRegion().getProvince()!=null&&subarea.getRegion().getProvince().trim().length()>0) {
			detachedCriteria.add(Restrictions.like("r.province","%"+subarea.getRegion().getProvince()+"%"));  
			}
			if(subarea.getRegion().getCity()!=null&&subarea.getRegion().getCity().trim().length()>0) {
			detachedCriteria.add(Restrictions.like("r.city", "%"+subarea.getRegion().getCity()+"%"));
			}
			if (subarea.getRegion().getDistrict()!=null&&subarea.getRegion().getDistrict().trim().length()>0) {
			detachedCriteria.add(Restrictions.like("r.district","%"+subarea.getRegion().getDistrict()+"%" ));	
			}
		}
		PageRequestBean pageRequestBean = initPageRequestBean(detachedCriteria);
		PageResponseBean pageResponseBean=subareaServiceimpl.pageQuery(pageRequestBean);
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		return "pageQuerySuccess";
	}

}
