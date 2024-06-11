package fr.ensitech.golfloc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "cart")
@XmlRootElement
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CartId id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public Cart() {}

    public Cart(CartId id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public CartId getId() {
        return id;
    }

    public void setId(CartId id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	@Override
	public String toString() {
		return "Cart [id=" + id + ", quantity=" + quantity + "]";
	}
}
