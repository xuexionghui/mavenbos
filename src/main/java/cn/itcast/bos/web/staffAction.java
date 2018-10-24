package cn.itcast.bos.web;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.ImportResource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.service.staffService;

public class staffAction  extends ActionSupport implements ModelDriven<Staff>{
    
	//模型驅動 
	private Staff staff =new Staff();
	public Staff getModel() {
		return staff;
	}
	//属性驱动
	private  int page;
	private  int rows;
	
	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	@Resource(name="staffService")
	private staffService staffServiceImpl;
	/*
	 * 1、保存或者更新取派员
	 */
	public String  saveStaff() {
		staffServiceImpl.saveOrUpdate(staff);
		return "saveStaffSuccess";
	}
	
	/*
	 * 2、查询取派员，列表展示
	 */
     
	public String  pageQuery() {
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);
	    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
	    detachedCriteria.add(Restrictions.eq("deltag", "0"));  //查找未删除的取派员
	    pageRequestBean.setDetachedCriteria(detachedCriteria);  //别忘记设置查询条件
	    PageResponseBean pageResponseBean= staffServiceImpl.pageQuery(pageRequestBean);
	    //放入域中
	    ActionContext.getContext().put("pageResponseBean", pageResponseBean);       //结果集还没有配置
		return "pageQuerySuccess";
	}
	
	/*
	 * 3、取派员的删除
	 */
	public String    delBatch() {
		String idString = staff.getId();
		String[] ids = idString.split(",");
		staffServiceImpl.delBatch(ids);
		return "delBatchSuccess";
	}
	
	/*
	 * 为定区数据的保存提供取派员的下拉选数据
	 *      使用命名查询
	 */
	public  String ajaxStaff() {
		
		List<Staff>  staffs=staffServiceImpl.ajaxStaff();
		//将取派员的数据压入到值栈中
		ActionContext.getContext().put("staffs", staffs);
		return "ajaxStaffSuccess";
	}
}
