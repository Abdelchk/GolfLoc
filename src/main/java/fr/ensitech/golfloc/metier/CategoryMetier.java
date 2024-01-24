package fr.ensitech.golfloc.metier;

import java.util.List;

import fr.ensitech.golfloc.entity.Category;
import fr.ensitech.golfloc.model.dao.CategoryDao;

public class CategoryMetier {
	private CategoryDao categoryDao;

	// Méthodes de Gestion
	
		public Integer createCategory(Category category) {
			
			try {
				categoryDao = new CategoryDao();
				Integer createCategory = categoryDao.addCategory(category); 
				
				if (createCategory != null) {
					return createCategory;
				}
				else {
					System.out.println("Une erreur est survenue lors de la creation de la catégorie.");
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Une erreur s'est produite dans CategoryMetier.createCategory : " + e.getMessage());
			}
			
			return null;
			
		}
		
		public void removeCategory(int id) {
			
			try {
				categoryDao = new CategoryDao();
				categoryDao.removeCategory(id);
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Une erreur s'est produite dans CategoryMetier.removeCategory : " + e.getMessage());
			}
		}
		
		public void updateCategory(Category category) {
			
			try {
				
				categoryDao = new CategoryDao();
				categoryDao.updateCategory(category);
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Une erreur s'est produite dans CategoryMetier.updateCategory : " + e.getMessage());
			}
		}
		
		public Category getCategoryById(int id) {
			
			try {
				categoryDao = new CategoryDao();
				return categoryDao.getCategoryById(id);
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Une erreur s'est produite dans CategoryMetier.getCategoryByName : " + e.getMessage());
				return null;
			}
		}
		
		public List<Category> getCategories() {
			
			try {
				categoryDao = new CategoryDao();
				return categoryDao.getCategories();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Une erreur s'est produite dans CategoryMetier.getCategories : " + e.getMessage());
				return null;
			}
		}
}
