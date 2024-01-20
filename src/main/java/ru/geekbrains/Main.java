package ru.geekbrains;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.Contact;
import ru.geekbrains.persist.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emFactory = new Configuration() // потокобезопасая сущность . может работать со многими потоками
                .configure("hibernate.cfg.xml")
                .buildSessionFactory(); // может работать только с одним потоком

         EntityManager em = emFactory.createEntityManager();

// INSERT for OneToMany:

//        em.getTransaction().begin();
//
//        User user = new User("user2","password2","user2@gmail.com");
//        em.persist(user);
//
//        List<Contact> contacts = new ArrayList<>();
//        contacts.add(new Contact("hup","57746",user));
//        contacts.add(new Contact("p","56",user));
//        contacts.add(new Contact("htpp","5777",user));
//        contacts.add(new Contact("ureew","59997",user));
//
//        contacts.forEach(em::persist);
//
//        em.getTransaction().commit();

        // SELECT for OneToMany:
 // транзакция не нужна при селекте

        User user = em.find(User.class, 1L);

        user.getContacts().forEach(System.out::println);

        System.out.println("***********");

//        List<Contact> contacts = em.createQuery(  // этот запрос не срабатывает
//                "SELECT c FROM User u " +
//                        "INNER JOIN Contact c ON u.id=c.user.id "+
//                        "WHERE c.type='hp'", Contact.class)
//                .getResultList();
//
//            contacts.forEach(System.out::println);

       User user1 = em.createQuery(
                "SELECT u FROM User u " +
                        "INNER JOIN Contact c ON u.id=c.user.id "+
                        "WHERE c.type='hp'", User.class)
                .getSingleResult();

        user1.getContacts().forEach(System.out::println);


        System.out.println("&&&&&&&&&");

        List<String> usernames = em.createQuery(  // этот механизм в терминологии Хибернейта называется проекцией
                "SELECT new java.lang.String(u.username) FROM User u " +
                        "INNER JOIN Contact c ON u.id=c.user.id "+
                        "WHERE c.type='hp'", String.class)
                .getResultList();

            usernames.forEach(System.out::println);




        em.close();


    }

}