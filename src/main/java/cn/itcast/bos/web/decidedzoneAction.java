package cn.itcast.bos.web;

import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.bridge.MessageWriter;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.ImportResource;

import com.junlaninfo.domain.Customer;
import com.junlaninfo.service.CustomerService;
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
	    if (decidedZone.getId()!=null&&decidedZone.getId().trim().length()>0) {
			detachedCriteria.add(Restrictions.eq("id", decidedZone.getId()));
		}
	    
	    if(decidedZone.getStaff()!=null) {
	    	//创建别名查询
	    	detachedCriteria.createAlias("staff", "s");
	    	//进行取件员所属单位的条件判断
	    	if (decidedZone.getStaff().getStation()!=null&& decidedZone.getStaff().getStation().trim().length()>0) {
			  detachedCriteria.add(Restrictions.like("s.station", "%"+decidedZone.getStaff().getStation()+"%"));
			}
	    }
	    pageRequestBean.setDetachedCriteria(detachedCriteria);
	    PageResponseBean pageResponseBean=decidedzoneserviceIMpl.pageQuery(pageRequestBean);
	    //将结果压入到值栈中
	    ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		return "pageQuerySuccess";
	}
	
	/*
	 * 查询没有关联定区的顾客资料
	 */
	@Resource(name="customerService")
	private  CustomerService  customerServiceImpl;
	public String findCustomerNoConnectDecidedzone() {
		List<Customer> customers = customerServiceImpl.findCustomerNoConnectDecidedzone();
		ActionContext.getContext().put("customers", customers);
		return "findCustomerNoConnectDecidedzoneSuccess";
	}
	/*
	 * 查询关联了定区的顾客资料
	 */
	public String findCustomerConnectDecidedzone() {
		List<Customer> customers = customerServiceImpl.findCustomerConnectDecidedzone(decidedZone.getId());
		ActionContext.getContext().put("customers",customers);
		return "findCustomerConnectDecidedzoneSuccess";
	}
	
	private String[]   customerIds;
	public void setCustomerIds(String[] customerIds) {
		this.customerIds = customerIds;
	}
	public String   makeCustomerConnectDecidedzone() {
		customerServiceImpl.makeCustomerConnectDecidedzone(customerIds, decidedZone.getId());
		return "makeCustomerConnectDecidedzoneSuccess";
	}
	

}
