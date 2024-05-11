package fr.ensitech.golfloc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import fr.ensitech.golfloc.entity.Category;
import fr.ensitech.golfloc.entity.Item;
import fr.ensitech.golfloc.enums.Flexibility;
import fr.ensitech.golfloc.enums.Gender;
import fr.ensitech.golfloc.enums.MainHand;
import fr.ensitech.golfloc.metier.CategoryMetier;
import fr.ensitech.golfloc.metier.ItemMetier;

@ManagedBean(name = "itembean")
@SessionScoped
public class ItemBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String brand;
	private String gender;
	private String mainHand;
	private String flexibility;
	private String description;
	private Float price;
	private int discount;
	private int stock;
	private int categoryId;
	private boolean isSellable;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMainHand() {
		return mainHand;
	}

	public void setMainHand(String mainHand) {
		this.mainHand = mainHand;
	}

	public String getFlexibility() {
		return flexibility;
	}

	public void setFlexibility(String flexibility) {
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public boolean getIsSellable() {
		return isSellable;
	}

	public void setIsSellable(boolean isSellable) {
		this.isSellable = isSellable;
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
	
	// Méthodes qui gère l'edit du formulaire des articles
	
	public void onRowEdit(RowEditEvent<Item> event) {
		FacesMessage msg = new FacesMessage("Article", event.getObject().getName().concat(" a bien été modifié"));
	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    }

    public void onRowCancel(RowEditEvent<Item> event) {
        FacesMessage msg = new FacesMessage("Modification annulée pour l'article", event.getObject().getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	// Méthodes de Gestion 

	public String createItem() {
		itemMetier = new ItemMetier();
		CategoryMetier catMetier = new CategoryMetier();
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
			item.setCategory(catMetier.getCategoryById(categoryId));
			item.setIsSellable(isSellable);
			itemMetier.createItem(item);
			
			return "items.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String updateItem(Item item) {
		itemMetier = new ItemMetier();
		CategoryMetier catMetier = new CategoryMetier();
		try {
			
			System.out.println("Item ID : " + item.getId());
			System.out.println("Category ID : " + item.getCategory().getId());
			System.out.println("New Category ID : " + categoryId);
			System.out.println("Flexibility : " + flexibility);
			System.out.println("Description de la vue : " + description);
			System.out.println("Description de la BDD : " + item.getDescription());
			
			item = itemMetier.getItemById(item.getId());
			
			System.out.println(item);
			
			
			if (item.getName() != name && name != null && name != "") {
				item.setName(name);
			}
			
			if (item.getBrand() != brand && brand != null && brand != "") {
				item.setBrand(brand);
			}
			
			if (item.getGender() != gender && gender != null && gender != "") {
				item.setGender(gender);
			}
			
			if (item.getMainHand() != mainHand && mainHand != null && mainHand != "") {
				item.setMainHand(mainHand);
			}
			
			if (item.getFlexibility() != flexibility && flexibility != null && flexibility != "") {
				item.setFlexibility(flexibility);
			}
			
			String currentDescription = item.getDescription();

			switch (currentDescription) {
			    case "":
			        if (description != null && description != "") {
                    	item.setDescription(description);
                    }
			        break;
			    default:
					if (description != null && description != "") {
						item.setDescription(description);
					} else {
						item.setDescription(currentDescription);
					}
			        break;
			}

			if (item.getPrice() != price) {
				item.setPrice(price);
			}
			
			if (item.getDiscount() != discount) {
				item.setDiscount(discount);
			}
			
			if (item.getStock() != stock) {
				item.setStock(stock);
			}
			
			if (item.getCategory().getId() != categoryId && categoryId != 0) {
				item.setCategory(catMetier.getCategoryById(categoryId));
			}
			
			if (item.getIsSellable() != isSellable) {
				item.setIsSellable(isSellable);
			}
			
			System.out.println("Category ID modifié : " + item.getCategory().getId());
			System.out.println("Flexibility modifié : " + item.getFlexibility());
			System.out.println("Description modifié : " + item.getDescription());
			
			itemMetier.updateItem(item);
			return "items.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String removeItem() {
		itemMetier = new ItemMetier();
		try {
			System.out.println(item);
			itemMetier.removeItem(item);
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
	
//	public List<Item> getItems() {
//		itemMetier = new ItemMetier();
//		try {
//			List<Item> items = itemMetier.getItems();
//			
//			return items;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
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
