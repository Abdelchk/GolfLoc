package fr.ensitech.golfloc.model.dao;

import java.util.List;

import fr.ensitech.golfloc.entity.Category;

public interface ICategoryDao {

	Integer addCategory(String name, int discount, String isCumulative) throws Exception;
	void removeCategory(int id) throws Exception;
	void updateCategory(Category category) throws Exception;
	void updateDiscountCategory(int id, int discount) throws Exception;
	Category getCategoryByName(String name) throws Exception;
	List<Category> getCategories() throws Exception;
}
