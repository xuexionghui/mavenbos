package cn.itcast.bos.web.Interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import cn.itcast.bos.domain.user.User;

public class loginInterceptor  extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		User user = (User) ServletActionContext.getContext().getSession().get("user");
		if(user==null) {
			ActionSupport actionSupport= (ActionSupport) invocation.getAction();
			actionSupport.addActionError("您还没有登录");
			return "login";  //跳转到登录页面
		}
		 
		return invocation.invoke();   //已经登录，放行
	}

}
