package cn.itcast.bos.web;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.event.SaveOrUpdateEvent;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.Standard;
import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.service.standardService;

public class standardAction  extends ActionSupport implements ModelDriven<Standard>{
     //模型驱动，接收用户的数据
	private Standard  standard=new Standard();
	public Standard getModel() {
		return standard;
	}
	@Resource(name="standardService")
	private standardService  standardServiceImpl;
	public String save() {
		//补全属性
		User user=(User) ServletActionContext.getContext().getSession().get("user");
	 
	    standard.setUser(user);
	    standard.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		standardServiceImpl.saveStandard(standard);
		
		return "standardSuccess";
	}
	 //属性驱动
    private int page;
    private int rows;
 	/*public int getPage() {
		return page;
	}*/
	public void setPage(int page) {
		this.page = page;
	}
	/*public int getRows() {
		return rows;
	}*/
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public  String   pageQuery() {
		//封装数据
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Standard.class);
		detachedCriteria.add(Restrictions.eq("deltag", "0"));   //eq相等
		pageRequestBean.setDetachedCriteria(detachedCriteria);  //要将条件设置回去，不然会出现空指针异常
		//调用业务层
		PageResponseBean pageResponseBean=standardServiceImpl.pageQuery(pageRequestBean);
		System.out.println("--查询到的数据：  "+pageRequestBean);
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		return "pageQuerySUCCESS";     //返回结果
	} 

}
