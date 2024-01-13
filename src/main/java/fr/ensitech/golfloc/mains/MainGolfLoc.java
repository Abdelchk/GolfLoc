package fr.ensitech.golfloc.mains;

import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.model.dao.UserDao;

public class MainGolfLoc {

	public static void main(String[] args) {
		
		try {
			
			UserDao userDao = new UserDao();
			
			User user = new User();
			
			user.setEmail("camus@gmail.com");
			user.setPassword("Camupass7*");
			
			userDao.verifyUser(user.getEmail(), user.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
