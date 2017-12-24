package org.coredumpproject.coredump;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.Logger;


/**
 * Created by Gregory on 5/26/2017.
 */
@Singleton
@TransactionManagement(value= TransactionManagementType.BEAN)
public class World {

    private String name;

    public World(){
      name = "New World";
    }

    public void claim(String user){
        name = user+" claimed this world!";
        saveUser(name);
    }

    public String getName(){
        return this.name;
    }

    @PostConstruct
    public void init() {
        System.out.println("Creating The World.");
    }

    public void saveUser(String name){
        		/* Create EntityManagerFactory */
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("Coredump");

		/* Create and populate Entity */


		/* Create EntityManager */
        EntityManager em = emf.createEntityManager();

		/* Persist entity */
        em.getTransaction().begin();
        User user = em.find(User.class,1);
        if (user == null){
            user = new User();
            user.setUserId(1);
        }
        user.setName(name);
        em.persist(user);
        em.getTransaction().commit();
    }
}
