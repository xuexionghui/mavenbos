package cn.itcast.bos.service;

import cn.itcast.bos.domain.user.User;

public interface userService {

	User login(User user);

	User findUserById(String id);

	void editPassword(User dbUser);

}
