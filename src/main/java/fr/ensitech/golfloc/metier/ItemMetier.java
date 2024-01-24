package fr.ensitech.golfloc.metier;

import java.util.List;

import fr.ensitech.golfloc.entity.Item;
import fr.ensitech.golfloc.model.dao.ItemDao;

public class ItemMetier {
	private ItemDao itemDao;

	// Méthodes de Gestion 
	
		public Integer createItem(Item item) {
			try {
				itemDao = new ItemDao();
				Integer createItem = itemDao.addItem(item);
				if (createItem != null) {
					return createItem;
				} else {
					System.out.println("Une erreur est survenue lors de la création de l'article.");
					return null;
				}
			} catch	(Exception e) {
				e.printStackTrace();
				System.out.println("Une erreur est survenue dans ItemMetier.CreateItem : " + e.getMessage());
			}
			return null;
		}
		
		public void updateItem(Item item) {
			try {
				itemDao = new ItemDao();
				itemDao.updateItem(item);
			} catch	(Exception e) {
				e.printStackTrace();
				System.out.println("Une erreur est survenue dans ItemMetier.UpdateItem : " + e.getMessage());
			}
		}
		
		public Item getItemByName(String name) {
			try {
				itemDao = new ItemDao();
				return itemDao.getItemByName(name);
			} catch	(Exception e) {
				e.printStackTrace();
				System.out.println("Une erreur est survenue dans ItemMetier.GetItemByName : " + e.getMessage());
				return null;
			}
		}
		
		public List<Item> getItems() {
			try {
				itemDao = new ItemDao();
				return itemDao.getItems();
			} catch	(Exception e) {
				e.printStackTrace();
				System.out.println("Une erreur est survenue dans ItemMetier.GetItems : " + e.getMessage());
				return null;
			}
		}
}
