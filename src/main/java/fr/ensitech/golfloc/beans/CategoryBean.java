package fr.ensitech.golfloc.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import fr.ensitech.golfloc.entity.Category;
import fr.ensitech.golfloc.metier.CategoryMetier;

import javax.faces.application.FacesMessage;

@ManagedBean(name = "categorybean")
@SessionScoped
public class CategoryBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
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
	
	// Méthodes de Gestion
	
	public String createCategory() {
		
		categoryMetier = new CategoryMetier();
		
		try {
			category = new Category();
			
			category.setName(name);
			category.setDiscount(discount);
			category.setIsCumulative(isCumulative);
			
			categoryMetier.createCategory(category);
			
			return "magasinier.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String removeCategory() {
		categoryMetier = new CategoryMetier();
		
		try {
			
			categoryMetier.removeCategory(name);
			return "magasinier.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String updateDiscountCategory() {
		
		categoryMetier = new CategoryMetier();
		
		try {
			
			categoryMetier.updateDiscountCategory(name, discount);
			return "magasinier.xhtml";
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
			
			return "magasinier.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getCategoryByName() {
		
		categoryMetier = new CategoryMetier();
		
		try {
			
			categoryMetier.getCategoryByName(name);
			
			return "magasinier.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getCategories() {
		
		categoryMetier = new CategoryMetier();
		
		try {
			
			categoryMetier.getCategories();
			
			return "magasinier.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
