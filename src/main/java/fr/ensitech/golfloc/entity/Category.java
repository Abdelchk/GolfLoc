package fr.ensitech.golfloc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import fr.ensitech.golfloc.enums.Type;

@Entity(name = "category")
@XmlRootElement
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "discount", nullable = false)
	private int discount;
	
	@Column(name = "is_cumulative", nullable = false, columnDefinition = "boolean default false")
	private boolean isCumulative;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Item> item;
	
	public Category() {
	}

	public Category(Integer id, String name, int discount, boolean isCumulative) {
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

	@Override
	public String toString() {
		return "Categorie : Id = " + id + " Nom = " + name + " Remise = " + discount + " Remise cumulative = " + isCumulative;
	}
	
	
}
