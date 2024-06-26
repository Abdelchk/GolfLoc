package fr.ensitech.golfloc.mains;

import java.util.List;

import fr.ensitech.golfloc.entity.Cart;
import fr.ensitech.golfloc.entity.Category;
import fr.ensitech.golfloc.entity.Item;
import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.metier.CartMetier;
import fr.ensitech.golfloc.model.dao.CartDao;
import fr.ensitech.golfloc.model.dao.CategoryDao;
import fr.ensitech.golfloc.model.dao.IUserDao;
import fr.ensitech.golfloc.model.dao.ItemDao;
import fr.ensitech.golfloc.model.dao.UserDao;
import fr.ensitech.golfloc.utils.DESUtil;

public class MainGetAll {

	public static void main(String[] args) {

		IUserDao userDao = new UserDao();
		
		try {
			
			String originalText = "Test1234"; // 8 caractères
	        String encryptedText = DESUtil.encrypt(originalText);
	        String decryptedText = DESUtil.decrypt(encryptedText);

	        System.out.println("Original Text: " + originalText);
	        System.out.println("Encrypted Text: " + encryptedText);
	        System.out.println("Decrypted Text: " + decryptedText);
			
//			List<User> users = userDao.getUsers();
//			users.forEach(u -> System.out.println(u));
			
//			User user = userDao.getUserById(6);
//			
//			System.out.println("Carte de paiement : " + user.getCarteDePaiement());
//			
//			user.setIsActive(true);
//			
//			userDao.updateUser(user);
//			
//			System.out.println(user);
			
//			CategoryDao categoryDao = new CategoryDao();
//			List <Category> categories = categoryDao.getCategories();
//			
//			for (Category category : categories) {
//				System.out.println(category.getName());
//			}
			
//			categories.forEach(u -> System.out.println(u));
			
//			ItemDao itemDao = new ItemDao();
//			List <Item> items = itemDao.getFilteredItems("DRIVER");
//			items.forEach(u -> System.out.println(u));
			
//			CartDao cartDao = new CartDao();
//			
//			CartMetier cartMetier = new CartMetier();
//			
//			List<Cart> cart = cartDao.getCart(user.getId());
//			
//			cart.forEach(u -> System.out.println("Panier validé : " + u.getIsValide()));
//			
//			cartMetier.updateCartValide(user);
			
//			cart.forEach(u -> System.out.println("Panier validé : " + u.getIsValide()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
