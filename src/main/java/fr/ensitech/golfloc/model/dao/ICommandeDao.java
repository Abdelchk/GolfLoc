package fr.ensitech.golfloc.model.dao;

import java.util.List;

import fr.ensitech.golfloc.entity.Commande;
import fr.ensitech.golfloc.entity.User;

public interface ICommandeDao {
	void save(Commande commande) throws Exception;
    void update(Commande commande) throws Exception;
    void delete(Commande commande) throws Exception;
    Commande findById(int id) throws Exception;
    List<Commande> findByUser(User user) throws Exception;
}
