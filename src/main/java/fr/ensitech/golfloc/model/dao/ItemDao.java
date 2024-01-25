package fr.ensitech.golfloc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.ensitech.golfloc.entity.Item;
import fr.ensitech.golfloc.model.connection.UserDataSource;

public class ItemDao implements IItemDao {

	public ItemDao() {	
	}
	
	@Override
	public Integer addItem(Item item) throws Exception {
		if (item == null) {
			throw new NullPointerException("L'article à créer ne doit pas être NULL !");
		}
		if (item.getName() == null || item.getName().trim().isEmpty() ||
				item.getBrand() == null || item.getBrand().trim().isEmpty() ||
				item.getGender() == null || item.getGender().trim().isEmpty() ||
				item.getMainHand() == null || item.getMainHand().trim().isEmpty() ||
				item.getFlexibility() == null || item.getFlexibility().trim().isEmpty() ||
				item.getDescription() == null || item.getDescription().trim().isEmpty() ||
				Float.toString(item.getPrice()) == null || Float.toString(item.getPrice()).trim().isEmpty() ||
				Integer.toString(item.getDiscount()) == null || Integer.toString(item.getDiscount()).trim().isEmpty() ||
				Integer.toString(item.getStock()) == null || Integer.toString(item.getStock()).trim().isEmpty() ||
				Integer.toString(item.getCategoryId()) == null || Integer.toString(item.getCategoryId()).trim().isEmpty() ||
				item.getIsSellable() == null || item.getIsSellable().trim().isEmpty())
		{
			throw new IllegalArgumentException("Tous les paramètres sont obligatoires !");
		}
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "Insert into item Values ('', '?', '?', '?', '?', '?', '?', ?, ?, ?, ?, '?');";
			PreparedStatement ps = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getName());
			ps.setString(2, item.getBrand());
			ps.setString(3, item.getGender());
			ps.setString(4, item.getMainHand());
			ps.setString(5, item.getFlexibility());
			ps.setString(6, item.getDescription());
			ps.setFloat(7, item.getPrice());
			ps.setInt(8, item.getDiscount());
			ps.setInt(9, item.getStock());
			ps.setInt(10, item.getCategoryId());
			ps.setString(11, item.getIsSellable());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs != null && rs.next()) {
				return rs.getInt(1); 
			}
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		return null;
	}

	@Override
	public void updateItem(Item item) throws Exception {
		if (item == null) {
			throw new NullPointerException("L'article à créer ne doit pas être NULL !");
		}
		if (item.getName() == null || item.getName().trim().isEmpty() ||
				item.getBrand() == null || item.getBrand().trim().isEmpty() ||
				item.getGender() == null || item.getGender().trim().isEmpty() ||
				item.getMainHand() == null || item.getMainHand().trim().isEmpty() ||
				item.getFlexibility() == null || item.getFlexibility().trim().isEmpty() ||
				item.getDescription() == null || item.getDescription().trim().isEmpty() ||
				Float.toString(item.getPrice()) == null || Float.toString(item.getPrice()).trim().isEmpty() ||
				Integer.toString(item.getDiscount()) == null || Integer.toString(item.getDiscount()).trim().isEmpty() ||
				Integer.toString(item.getStock()) == null || Integer.toString(item.getStock()).trim().isEmpty() ||
				Integer.toString(item.getCategoryId()) == null || Integer.toString(item.getCategoryId()).trim().isEmpty() ||
				item.getIsSellable() == null || item.getIsSellable().trim().isEmpty())
		{
			throw new IllegalArgumentException("Tous les paramètres sont obligatoires !");
		}
		Connection connection = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "Update item Set name = '?', brand = '?', gender = '?', main_hand = '?', flexibility = '?', description = '?', price = ?, discount = ?, stock = ?, category_id = ?, is_sellable = ? Where id = ?";
			PreparedStatement ps = connection.prepareStatement(requete);
			ps.setString(1, item.getName());
			ps.setString(2, item.getBrand());
			ps.setString(3, item.getGender());
			ps.setString(4, item.getMainHand());
			ps.setString(5, item.getFlexibility());
			ps.setString(6, item.getDescription());
			ps.setFloat(7, item.getPrice());
			ps.setInt(8, item.getDiscount());
			ps.setInt(9, item.getStock());
			ps.setInt(10, item.getCategoryId());
			ps.setString(11, item.getIsSellable());
			ps.setInt(12, item.getId());
			ps.executeUpdate();
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}	

	@Override
	public Item getItemByName(String name) throws Exception {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("Le nom d'article doit être renseigné.");
		}
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "SELECT * FROM item WHERE name = ?";
			PreparedStatement ps = connection.prepareStatement(requete);
			ps.setString(1, name);
			ps.execute();
			rs = ps.getResultSet();
			if (rs != null && rs.next()) {
				Item item = new Item();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setBrand(rs.getString("brand"));
				item.setGender(rs.getString("gender"));
				item.setMainHand(rs.getString("main_hand"));
				item.setFlexibility(rs.getString("flexibility"));
				item.setDescription(rs.getString("description"));
				item.setPrice(rs.getFloat("price"));
				item.setDiscount(rs.getInt("discount"));
				item.setStock(rs.getInt("stock"));
				item.setCategoryId(rs.getInt("category_id"));
				item.setIsSellable(rs.getString("is_sellable"));
				return item;
			} else {
				System.out.println("Aucun article trouvé pour ce nom : " + name);
				return null;
			}
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
	}

	@Override
	public List<Item> getItems() throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "SELECT * FROM item";
			PreparedStatement ps = connection.prepareStatement(requete);
			ps.execute();
			rs = ps.getResultSet();
			if (rs != null) {
				List<Item> items = new ArrayList<Item>();
				while (rs.next()) {
					Item item = new Item();
					item.setId(rs.getInt("id"));
					item.setName(rs.getString("name"));
					item.setBrand(rs.getString("brand"));
					item.setGender(rs.getString("gender"));
					item.setMainHand(rs.getString("main_hand"));
					item.setFlexibility(rs.getString("flexibility"));
					item.setDescription(rs.getString("description"));
					item.setPrice(rs.getFloat("price"));
					item.setDiscount(rs.getInt("discount"));
					item.setStock(rs.getInt("stock"));
					item.setCategoryId(rs.getInt("category_id"));
					item.setIsSellable(rs.getString("is_sellable"));;
					items.add(item);
				}
				return items;
			}
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}  
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		return null;
	}
}
