package fr.ensitech.golfloc.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;

import fr.ensitech.golfloc.entity.Adresse;
import fr.ensitech.golfloc.entity.User;
import fr.ensitech.golfloc.model.dao.IUserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import fr.ensitech.golfloc.utils.Dates;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserDao userDao;

    @InjectMocks
    private UserService userService;

    @Test
    public void testRegisterSuccess() {
    	try {
	        User user = new User();
	        Adresse adresse = new Adresse();
	        user.setEmail("unittest@gmail.com");
	        user.setPassword("passwordunittest123");
	        user.setNom("Abdel");
	        user.setPrenom("Check");
	        user.setDateNaissance(Dates.convertStringToDate("1995-05-20"));
	        user.setPhoneNumber("0606060606");
	        
	        adresse.setRue("rue de test");
	        adresse.setVille("Ville de test");
	        adresse.setCodePostal("75000");
	        adresse.setNumero("45");
	        
	        user.setAdresse(adresse);
	
	        when(userDao.getUserByEmail(user.getEmail())).thenReturn(null);
	
	        boolean result = userService.register(user);
	
	        assertTrue(result);
	        verify(userDao, times(1)).addUser(user);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    @Test
    public void testRegisterFail_EmailExists() {
    	try {
            
	        User user = new User();
	        Adresse adresse = new Adresse();
	        user.setEmail("check.abdel@gamil.com");
	        user.setPassword("testemailexistspassword");
	        user.setNom("Abdel");
	        user.setPrenom("Check");
	        user.setDateNaissance(Date.valueOf("1995-05-20"));
	        user.setPhoneNumber("0606060606");
	        
	        adresse.setRue("rue de test");
	        adresse.setVille("Ville de test");
	        adresse.setCodePostal("75000");
	        adresse.setNumero("45");
	        
	        user.setAdresse(adresse);
	        
	        when(userDao.getUserByEmail(user.getEmail())).thenReturn(new User());
	
	        boolean result = userService.register(user);
	
	        assertFalse(result);
	        verify(userDao, never()).addUser(user);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @Test
    public void testLoginSuccess() {
    	try {
    		String email = "check.abdel@gmail.com";
	        String password = "AbdelC95520#*";
	
	        User user = new User();
	        user.setEmail(email);
	        user.setPassword(password);
	
	        when(userDao.getUserByEmail(email)).thenReturn(user);
	
	        User result = userService.login(email, password);
	
	        assertNotNull(result);
	        assertEquals(email, result.getEmail());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @Test
    public void testLoginFail_WrongPassword() {
    	try {
	        String email = "check.abdel@gmail.com";
	        String password = "AbdelC95520#*";
	
	        User user = new User();
	        user.setEmail(email);
	        user.setPassword("wrongpassword#*");
	
	        when(userDao.getUserByEmail(email)).thenReturn(user);
	
	        User result = userService.login(email, password);
	
	        assertNull(result);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @Test
    public void testLoginFail_UserNotFound() {
    	try {
	        String email = "notfound@example.com";
	        String password = "passwordnotfound";
	
	        when(userDao.getUserByEmail(email)).thenReturn(null);
	
	        User result = userService.login(email, password);
	
	        assertNull(result);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
