package fr.ensitech.golfloc.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import fr.ensitech.golfloc.entity.Category;
import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.metier.CategoryMetier;
import fr.ensitech.golfloc.enums.Type;

@ManagedBean(name = "categorybean")
@SessionScoped
public class CategoryBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private int discount;
	private boolean isCumulative;
	private Category category;
	private CategoryMetier categoryMetier;
	
	public CategoryBean() {
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

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public boolean getIsCumulative() {
		return isCumulative;
	}

	public void setIsCumulative(boolean isCumulative) {
		this.isCumulative = isCumulative;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	// Méthodes d'affichage des types de catégories
	
	public Type[] getTypes() {
		return Type.values();
	}
	
	public void onRowEdit(RowEditEvent<Category> event) {
        FacesMessage msg = new FacesMessage("Categorie", event.getObject().getName().concat(" modifié"));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Category> event) {
        FacesMessage msg = new FacesMessage("Modification annulée pour la catégorie", event.getObject().getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	
	
	// Méthodes de Gestion
	
	public String createCategory() {
		
		categoryMetier = new CategoryMetier();
		
		try {
			category = new Category();
			
			category.setName(name);
			category.setDiscount(discount);
			category.setIsCumulative(isCumulative);
			
			categoryMetier.createCategory(category);
			
			return "categories.xhtml?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String removeCategory() {
		categoryMetier = new CategoryMetier();
		
		try {
			
			categoryMetier.removeCategory(id);
			return "categories.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String updateCategory(Category category) {
		
		categoryMetier = new CategoryMetier();
		
		try {
			
			System.out.println("Category ID : " + category.getId());
			
			if (category.getDiscount() != discount) {
				category.setDiscount(discount);
			}
			
			if (category.getIsCumulative() != isCumulative) {
				category.setIsCumulative(isCumulative);
			}
			
			categoryMetier.updateCategory(category);
			
			return "categories.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getCategoryById() {
		
		categoryMetier = new CategoryMetier();
		
		try {
			
			categoryMetier.getCategoryById(id);
			
			return "categories.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Category> getCategories() {
		
		categoryMetier = new CategoryMetier();
		
		try {
			List <Category> categories = categoryMetier.getCategories();
			
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
