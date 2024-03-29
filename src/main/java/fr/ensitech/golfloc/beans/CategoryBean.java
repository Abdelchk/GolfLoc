package fr.ensitech.golfloc.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.ensitech.golfloc.entity.Category;
import fr.ensitech.golfloc.metier.CategoryMetier;
import fr.ensitech.golfloc.enums.Type;

@ManagedBean(name = "categorybean")
@SessionScoped
public class CategoryBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Type name;
	private int discount;
	private String isCumulative;
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

	public Type getName() {
		return name;
	}

	public void setName(Type name) {
		this.name = name;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getIsCumulative() {
		return isCumulative;
	}

	public void setIsCumulative(String isCumulative) {
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
	
	public String updateCategory() {
		
		categoryMetier = new CategoryMetier();
		
		try {
			
			category = new Category();
			
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
