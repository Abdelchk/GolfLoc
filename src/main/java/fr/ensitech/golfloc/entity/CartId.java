package fr.ensitech.golfloc.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CartId implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id", unique = true)
    private Item item;

    // Default constructor
    public CartId() {}

    // Parameterized constructor
    public CartId(User user, Item item) {
        this.user = user;
        this.item = item;
    }

    // Getters and setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    // hashCode and equals methods
    @Override
    public int hashCode() {
        return Objects.hash(user, item);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CartId that = (CartId) obj;
        return Objects.equals(user, that.user) &&
               Objects.equals(item, that.item);
    }

	@Override
	public String toString() {
		return "CartId [user=" + user + ", item=" + item + "]";
	}
    
    
}


