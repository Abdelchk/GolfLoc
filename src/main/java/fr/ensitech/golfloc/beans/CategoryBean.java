package fr.ensitech.golfloc.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

@ManagedBean(name = "categorybean")
@SessionScoped
public class CategoryBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private int discount;
	private String isCumulative;
	
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

	public String isCumulative() {
		return isCumulative;
	}

	public void setCumulative(String isCumulative) {
		this.isCumulative = isCumulative;
	}
	
	// Méthodes de Gestion
	
	public String createCategory() {
		return null;
		
	}
	
	public String removeCategory() {
		return null;
	}
	
	public String updateDiscountCategory() {
		return null;
	}
	
	public String updateCategory() {
		return null;
	}
	
	public String getCategoryByName() {
		return null;
	}
	
	public String getCategories() {
		return null;
	}
}
