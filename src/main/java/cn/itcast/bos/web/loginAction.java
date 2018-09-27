package cn.itcast.bos.web;



import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Resource;


import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.service.userService;

public class loginAction extends ActionSupport implements ModelDriven<User>{
   
	@Resource(name="userService")
    private userService userServiceImpl;
	//模型驱动接收数据
	private  User user=new User();
	
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	//属性方式 ，接收验证码
    private String checkcode; // 用户输入验证码
	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
    
	public String execute() {
		String checkCodeSession=(String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if(checkCodeSession==null || !checkCodeSession.equals(checkcode)) {
			this.addActionError("验证码错误");
			return INPUT;
			}
		User loginUser=userServiceImpl.login(user);
		//System.out.println("----------------登录的用户：" +loginUser);
		if(loginUser==null) {
			this.addActionError("密码或者用户名不对");
			return INPUT;
		}
		InetAddress localHost =null;
		 try {
		 localHost = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将缓存放入到session中保存
		ServletActionContext.getRequest().getSession().setAttribute("user", loginUser);
		ServletActionContext.getRequest().getSession().setAttribute("localHost", localHost);
		return SUCCESS;
	}
}
