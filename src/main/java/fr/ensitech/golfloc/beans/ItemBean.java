package fr.ensitech.golfloc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.ensitech.golfloc.entity.Category;
import fr.ensitech.golfloc.entity.Item;
import fr.ensitech.golfloc.enums.Flexibility;
import fr.ensitech.golfloc.enums.Gender;
import fr.ensitech.golfloc.enums.MainHand;
import fr.ensitech.golfloc.metier.ItemMetier;

@ManagedBean(name = "itembean")
@SessionScoped
public class ItemBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String brand;
	private Gender gender;
	private MainHand mainHand;
	private Flexibility flexibility;
	private String description;
	private Float price;
	private int discount;
	private int stock;
	private Category categoryId;
	private boolean isSellable;
	private String categoryName;
	private Item item;
	private String selectedCategory;
	private ItemMetier itemMetier;
	
	public ItemBean() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public MainHand getMainHand() {
		return mainHand;
	}

	public void setMainHand(MainHand mainHand) {
		this.mainHand = mainHand;
	}

	public Flexibility getFlexibility() {
		return flexibility;
	}

	public void setFlexibility(Flexibility flexibility) {
		this.flexibility = flexibility;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	public boolean getIsSellable() {
		return isSellable;
	}

	public void setIsSellable(boolean isSellable) {
		this.isSellable = isSellable;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public String getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	
	// Méthode pour récupérer les enums
	

	public Gender[] getGenders() {
		return Gender.values();
	}
	
	public MainHand[] getMainHands() {
		return MainHand.values();
	}
	
	public Flexibility[] getFlexibilities() {
		return Flexibility.values();
	}
	
	// Méthodes de Gestion 

	public String createItem() {
		itemMetier = new ItemMetier();
		try {
			item = new Item();
			item.setName(name);
			item.setBrand(brand);
			item.setGender(gender);
			item.setMainHand(mainHand);
			item.setFlexibility(flexibility);
			item.setDescription(description);
			item.setPrice(price);
			item.setDiscount(discount);
			item.setStock(stock);
			item.setCategory(categoryId);
			item.setIsSellable(isSellable);
			itemMetier.createItem(item);
			
			return "items.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String updateItem() {
		itemMetier = new ItemMetier();
		try {
			item = new Item();
			itemMetier.updateItem(item);
			return "items.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getItemByName() {
		itemMetier = new ItemMetier();
		try {
			itemMetier.getItemByName(name);
			return "items.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Item> getItems() {
		itemMetier = new ItemMetier();
		try {
			List<Item> items = itemMetier.getItems();
			
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	 public List<Item> getFilteredItems() {
	        itemMetier = new ItemMetier();
	        try {
	            if (selectedCategory == null || selectedCategory.isEmpty()) {
	                return itemMetier.getItems();
	            } else {
	                return itemMetier.getFilteredItems(selectedCategory);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ArrayList<>();
	        }
	    }
}
