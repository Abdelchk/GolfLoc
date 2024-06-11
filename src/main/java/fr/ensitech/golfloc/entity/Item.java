package fr.ensitech.golfloc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import fr.ensitech.golfloc.enums.Flexibility;
import fr.ensitech.golfloc.enums.Gender;
import fr.ensitech.golfloc.enums.MainHand;

@Entity(name = "item")
@XmlRootElement
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", length = 45, nullable = false)
	private String name;
	
	@Column(name = "brand", length = 45, nullable = false)
	private String brand;
	
	@Column(name = "gender", nullable = false)
	private String gender;
	
	@Column(name = "main_hand", nullable = false)
	private String mainHand;
	
	@Column(name = "flexibility", nullable = false)
	private String flexibility;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "price")
	private Float price;
	
	@Column(name = "discount", columnDefinition = "0")
	private int discount;
	
	@Column(name = "stock")
	private int stock;
	
	@ManyToOne
	@JoinColumn(name = "category_id", columnDefinition = "0")
	private Category category;
	
	@Column(name = "is_sellable", nullable = false, columnDefinition = "boolean default true")
	private boolean isSellable = true;
	
	@OneToMany(mappedBy = "id.item")
    private List<Cart> carts;
	
	@OneToMany(mappedBy = "item")
    private List<Comment> comments;
	
	public Item() {
	}

	public Item(Integer id, String name, String brand, String gender, String mainHand, String flexibility,
			String description, Float price, int discount, int stock, Category category, boolean isSellable) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.gender = gender;
		this.mainHand = mainHand;
		this.flexibility = flexibility;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
		this.category = category;
		this.isSellable = isSellable;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean getIsSellable() {
		return isSellable;
	}

	public void setIsSellable(boolean isSellable) {
		this.isSellable = isSellable;
	}
	
	@Override
	public String toString() {
		return "Item [name=" + name + ", brand=" + brand + ", gender=" + gender + ", mainHand="
				+ mainHand + ", flexibility=" + flexibility + ", description=" + description + ", price=" + price
				+ ", discount=" + discount + ", stock=" + stock + ", isSellable="
				+ isSellable + "Catégorie : " + category.getName() +  "]";
	}
	
	
}
