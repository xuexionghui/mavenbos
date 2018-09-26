package cn.itcast.bos.web;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.event.SaveOrUpdateEvent;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

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

}
