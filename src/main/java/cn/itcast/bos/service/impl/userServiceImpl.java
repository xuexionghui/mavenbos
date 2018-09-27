package cn.itcast.bos.service.impl;



import java.util.List;

import javax.annotation.Resource;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.service.userService;
import cn.itcast.bos.utils.MD5Utils;

public class userServiceImpl  implements userService{
    
	@Resource(name="userDAO")
	private GenericDAO<User>   userDao;
	
	
	public User login(User user) {
		List<User> list = userDao.findByNamedQuery("User.login", user.getUsername(),MD5Utils.md5(user.getPassword()));
		if (list==null) {
			return null;
		}
		return list.get(0);
	}
    /*
     * 去数据库中查询User
     * @see cn.itcast.bos.service.userService#findUserById(java.lang.String)
     */
	
	public User findUserById(String id) {
		User user = userDao.findById(id);
		return user;
	}
	
	public void editPassword(User dbUser) {
		userDao.update(dbUser);
		
	}

}
