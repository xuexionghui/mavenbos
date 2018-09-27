package cn.itcast.bos.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.ui.Model;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.service.userService;
import cn.itcast.bos.utils.MD5Utils;

public class UserAction  extends ActionSupport implements ModelDriven<User> {
    
   //模型驱动的方式接受数据
	private User user=new User();
	
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	@Resource(name="userService")
	private userService  userServiceImpl;
	//修改密码的业务逻辑
	public String editpassword() {
		User user1 = (User)ServletActionContext.getContext().getSession().get("user");
		System.out.println("当前用户："+user1.getUsername());
		User   dbUser  = userServiceImpl.findUserById(user1.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		if (dbUser==null) {
			map.put("result", "failure");
			map.put("msg", "没有这个用户");
		}else {
			dbUser.setPassword(MD5Utils.md5(user.getPassword()));
			userServiceImpl.editPassword(dbUser);
			map.put("result", "success");
			map.put("msg", "密码修改成功");
		}
		//放入到上下文中
		ActionContext.getContext().put("map", map);
		return "editpasswordSUCCESS";
	}

}
