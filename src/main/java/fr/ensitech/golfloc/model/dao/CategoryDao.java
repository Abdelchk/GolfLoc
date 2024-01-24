package fr.ensitech.golfloc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import java.sql.Statement;
import java.util.ArrayList;

import fr.ensitech.golfloc.entity.Category;
import fr.ensitech.golfloc.model.connection.UserDataSource;

public class CategoryDao implements ICategoryDao {
	
	public CategoryDao() {
		
	}

	@Override
	public Integer addCategory(Category category) throws Exception {
		if (category == null) {
			throw new NullPointerException("Le user à créer ne doit pas être NULL !");
		}
		if (category.getName() == null || category.getName().trim().isEmpty()
			|| category.getDiscount() < 0 || Integer.toString(category.getDiscount()).trim().isEmpty()
			|| category.getIsCumulative() == null || category.getIsCumulative().trim().isEmpty()) 
		{
			throw new IllegalArgumentException("Tous les paramètres sont obligatoires !");
		}
		Connection connection = null;
		ResultSet rs = null;
		
		try {
			connection = UserDataSource.getConnection();
			String requete = "INSERT INTO category(name, discount, is_cumulative)" + " VALUES(?,?,?)";
			PreparedStatement ps = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, category.getName());
			ps.setInt(2, category.getDiscount());
			ps.setString(3, category.getIsCumulative());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs != null && rs.next()) {
				return rs.getInt(1); // l'id de la ligne ajoutée dans la table user de la BDD
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
	public void removeCategory(int id) throws Exception {
		if (id < 0) {
			throw new IllegalArgumentException("L'id de la catégorie doit être positif !");
		}
		Connection connection = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "Delete from category Where id = ?";
			PreparedStatement ps = connection.prepareStatement(requete);
			ps.setInt(1, id);
			ps.executeUpdate();
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public void updateCategory(Category category) throws Exception {
		if (category == null) {
			throw new NullPointerException("La catégorie à modifier ne doit pas être NULL !");
		}
		if (category.getName() == null || category.getName().trim().isEmpty()
			|| category.getDiscount() < 0 || Integer.toString(category.getDiscount()).trim().isEmpty()
			|| category.getIsCumulative() == null || category.getIsCumulative().trim().isEmpty()) 
		{
			throw new IllegalArgumentException("Tous les paramètres sont obligatoires !");
		}
		Connection connection = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "Update category Set name = ?, discount = ? , is_cumulative = ? Where id = ?";
			PreparedStatement ps = connection.prepareStatement(requete);
			ps.setString(1, category.getName());
			ps.setInt(2, category.getDiscount());
			ps.setString(3, category.getIsCumulative());
			ps.setInt(4, category.getId());
			ps.executeUpdate();
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public void updateDiscountCategory(int id, int discount) throws Exception {
		if (id < 0 || discount < 0 || Integer.toString(discount).trim().isEmpty()) {
			throw new IllegalArgumentException("Le nom de la catégorie et la remise est obligatoire !");
		}
		Connection connection = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "Update category Set discount = ? Where id = ?";
			PreparedStatement ps = connection.prepareStatement(requete);
			ps.setInt(1, discount);
			ps.setInt(2, id);
			ps.executeUpdate();
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

	@Override
	public Category getCategoryById(int id) throws Exception {
		if (id < 0) {
			throw new IllegalArgumentException("L'id de la catégorie doit être positif !");
		}
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "Select * from category Where name = ?";
			PreparedStatement ps = connection.prepareStatement(requete);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs != null && rs.next()) {
				Category category = new Category();
				category.setName(rs.getString("name"));
				category.setDiscount(rs.getInt("discount"));
				category.setIsCumulative(rs.getString("is_cumulative"));
				return category;
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
	public List<Category> getCategories() throws Exception {
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = UserDataSource.getConnection();
			String requete = "Select * from category";
			PreparedStatement ps = connection.prepareStatement(requete);
			rs = ps.executeQuery();
			if (rs != null) {
				List<Category> categories = new ArrayList<Category>();
				while (rs.next()) {
					Category category = new Category();
					category.setName(rs.getString("name"));
					category.setDiscount(rs.getInt("discount"));
					category.setIsCumulative(rs.getString("is_cumulative"));
					categories.add(category);
				}
				return categories;
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