package fr.ensitech.golfloc.model.dao;

import java.util.List;

import fr.ensitech.golfloc.entity.Category;

public interface ICategoryDao {

	Integer addCategory(Category category) throws Exception;
	void removeCategory(int id) throws Exception;
	void updateCategory(Category category) throws Exception;
	Category getCategoryById(int id) throws Exception;
	List<Category> getCategories() throws Exception;
}
