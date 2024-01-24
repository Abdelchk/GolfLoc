package fr.ensitech.golfloc.model.dao;

import java.util.List;

import fr.ensitech.golfloc.entity.Category;

public class CategoryDao implements ICategoryDao {

	@Override
	public Integer addCategory(String name, int discount, String isCumulative) {
		return null;
	}

	@Override
	public void removeCategory(int id) {
		
	}

	@Override
	public void updateCategory(Category category) {
	}

	@Override
	public void updateDiscountCategory(int id, int discount) {
	}

	@Override
	public Category getCategoryByName(String name) {
		return null;
	}

	@Override
	public List<Category> getCategories() {
		return null;
	}

}
