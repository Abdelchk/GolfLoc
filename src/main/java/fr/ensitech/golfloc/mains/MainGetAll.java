package fr.ensitech.golfloc.mains;

import java.util.List;

import fr.ensitech.golfloc.entity.Category;
import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.model.dao.CategoryDao;
import fr.ensitech.golfloc.model.dao.IUserDao;
import fr.ensitech.golfloc.model.dao.UserDao;

public class MainGetAll {

	public static void main(String[] args) {

		IUserDao userDao = new UserDao();
		
		try {
			List<User> users = userDao.getUsers();
			users.forEach(u -> System.out.println(u));
			
			CategoryDao categoryDao = new CategoryDao();
			List <Category> categories = categoryDao.getCategories();
			
			for (Category category : categories) {
				System.out.println(category.getName());
			}
			
//			categories.forEach(u -> System.out.println(u));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
