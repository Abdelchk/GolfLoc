package fr.ensitech.golfloc.model.dao;

import java.util.List;

import fr.ensitech.golfloc.entity.Item;

public class ItemDao implements IItemDao {

	@Override
	public Integer addItem(String name, String brand, String gender, String mainHand, String flexibility,
			String description, Float price, int discount, int stock, Integer categoryId, boolean isSellable)
			throws Exception {
		return null;
	}

	@Override
	public void removeItem(int id) throws Exception {
		
	}

	@Override
	public void updateItem(Item item) throws Exception {
		
	}

	@Override
	public void updateDiscountItem(int id, int discount) throws Exception {
		
	}

	@Override
	public Item getItemByName(String name) throws Exception {
		return null;
	}

	@Override
	public List<Item> getItems() throws Exception {
		return null;
	}

}
