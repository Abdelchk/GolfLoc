package fr.ensitech.golfloc.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

@ManagedBean(name = "cartbean")
@SessionScoped
public class CartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;
	private Integer itemId;
	private int quantity;
	
	public CartBean() {
		
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	// Méthodes de Gestion
	
	public String addToCart() {
		return null;
	}
	
	public String removeFromCart() {
		return null;
	}
	
	public String clearCart() {
		return null;
	}
	
	public String getCart() {
		return null;
	}
}
