package fr.ensitech.golfloc.model.dao;

import java.util.List;

import fr.ensitech.golfloc.entity.Item;

public interface IItemDao {

	Integer addItem(Item item) throws Exception;
	void updateItem(Item item) throws Exception;
	void removeItem(Item item) throws Exception;
	Item getItemByName(String name) throws Exception;
	Item getItemById(int id) throws Exception;
	List<Item> getItems() throws Exception;
	List<Item> getFilteredItems(String selectedCategory) throws Exception;
}
