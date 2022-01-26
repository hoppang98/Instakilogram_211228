package com.instakilogram.project.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instakilogram.project.common.EncryptUtils;
import com.instakilogram.project.user.dao.UserDAO;
import com.instakilogram.project.user.model.User;

@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;
	
	public int addUser(String loginId, String password, String name, String email) {
		String encPassword = EncryptUtils.md5(password);
		
		return userDAO.insertUser(loginId, encPassword, name, email);
	}
	
	public boolean isDuplicateId(String loginId) {
		int count = userDAO.selectCountLoginId(loginId);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public User getUser(String loginId, String password) {
		String encPassword = EncryptUtils.md5(password);
		
		return userDAO.selectUser(loginId, encPassword);
	}
}
