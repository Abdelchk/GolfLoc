package fr.ensitech.golfloc.entity;

import fr.ensitech.golfloc.enums.Type;

public class Category {
	private Integer id;
	private Type name;
	private int discount;
	private String isCumulative;
	
	public Category() {
	}

	public Category(Integer id, Type name, int discount, String isCumulative) {
		this.id = id;
		this.name = name;
		this.discount = discount;
		this.isCumulative = isCumulative;
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
	
}
