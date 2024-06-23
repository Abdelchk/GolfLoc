package fr.ensitech.golfloc.service;

import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.model.dao.IUserDao;

public class UserService {
    private IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    public boolean register(User user) {
    	try {
        if (userDao.getUserByEmail(user.getEmail()) != null) {
            return false; // Email already exists
        }
        userDao.addUser(user);
        return true;
    	} catch (Exception e) {
			e.printStackTrace();
			return false;
    	}
    }

    public User login(String email, String password) {
    	try {
        User user = userDao.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
        return null;
    }
}
