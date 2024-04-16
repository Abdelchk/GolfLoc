package fr.ensitech.golfloc.mains;

import org.hibernate.Session;

import fr.ensitech.golfloc.model.connection.HibernateConnector;

public class MainSessionHibernate {

    public static void main(String[] args) {

        try {
            Session session = HibernateConnector.getSession();
            System.out.println(session);
            System.out.println("Session Hibernate démarrée avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
