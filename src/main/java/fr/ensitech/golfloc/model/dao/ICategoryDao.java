package fr.ensitech.golfloc.model.dao;

import java.util.List;

import fr.ensitech.golfloc.entity.Category;

public interface ICategoryDao {

	Integer addCategory(Category category) throws Exception;
	void removeCategory(String name) throws Exception;
	void updateCategory(Category category) throws Exception;
	void updateDiscountCategory(String name, int discount) throws Exception;
	Category getCategoryByName(String name) throws Exception;
	List<Category> getCategories() throws Exception;
}
