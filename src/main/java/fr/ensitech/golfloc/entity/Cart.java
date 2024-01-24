package fr.ensitech.golfloc.entity;

public class Cart {
	
	private Integer userId;
	private Integer itemId;
	private int quantity;
	
	public Cart() {
		
	}
	
	public Cart(Integer userId, Integer itemId, int quantity) {
		this.userId = userId;
		this.itemId = itemId;
		this.quantity = quantity;
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
}
