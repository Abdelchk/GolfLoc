package fr.ensitech.golfloc.model.dao;

import java.util.List;

import fr.ensitech.golfloc.entity.User;

public interface IUserDao {

	Integer addUser(User user) throws Exception;
	User getUserById(int id) throws Exception;
	User verifyUser(String email, String password) throws Exception;
	void updateUser(User user) throws Exception;
	void removeUser(int id) throws Exception;
	List<User> getUsers() throws Exception;
	User getUserByEmail(String email) throws Exception;
	void updatePassword(String password, int id) throws Exception;
	String getPasswordById(int userId) throws Exception;
}
