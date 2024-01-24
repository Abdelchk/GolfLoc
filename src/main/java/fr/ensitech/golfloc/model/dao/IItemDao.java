package fr.ensitech.golfloc.model.dao;

import java.util.List;

import fr.ensitech.golfloc.entity.Item;

public interface IItemDao {

	Integer addItem(String name, String brand, String gender, String mainHand, String flexibility, String description, Float price, int discount, int stock, Integer categoryId, boolean isSellable) throws Exception;
	void removeItem(int id) throws Exception;
	void updateItem(Item item) throws Exception;
	void updateDiscountItem(int id, int discount) throws Exception;
	Item getItemByName(String name) throws Exception;
	List<Item> getItems() throws Exception;
}
