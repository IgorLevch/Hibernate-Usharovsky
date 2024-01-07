package ru.geekbrains;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emFactory = new Configuration() // потокобезопасая сущность . может работать со многими потоками
                .configure("hibernate.cfg.xml")
                .buildSessionFactory(); // может работать только с одним потоком

         EntityManager em = emFactory.createEntityManager();
         // паттерн Unit of Work

//
//        em.getTransaction().begin();
//
//        User user = new User("user1", "password", "ss@kk.ru");
//        em.persist(user); // добавили пользователя в БД
//
//        em.getTransaction().commit();
//
//        em.close();

        // SELECT (для селекта транзакцию создавать необязательно)

      //  User user = em.find(User.class, 1L); // - the 1st way
     //   System.out.println(user);

        // Если извлекаем не по айди, то необходимо создать createQuery
        // HQL , JPQL
      /*  List<User>  userList = em.createQuery("from User", User.class)
                .getResultList();
        System.out.println(userList); // выводим всю таблицу*/


        // добавим еще пару пользователей
       /* em.getTransaction().begin();

        User user1 = new User("user2", "paword", "ss33@kk.ru");
        User user2 = new User("user22", "pawordddd", "33@kk.ru");
        em.persist(user1); // добавили пользователя в БД
        em.persist(user2); // добавили пользователя в БД

        em.getTransaction().commit();

        em.close();
*/

       /* System.out.println("USer with name 'user22'");
        Object user22 = em.createQuery("from User u where u.username = :username")
                .setParameter("username", "user22")
                .getSingleResult();// этот метод , если предполагаем извелечение только одной сущности
        System.out.println(user22);


        System.out.println("Native SQL Inquiry");
        List<User> resultList = em.createNativeQuery("select * from users", User.class) // это уже пишем СКьюЭль запрос
                // данный метод используем только, если не срабатывают обычные Хибернетовские
                .getResultList();
        System.out.println(resultList);


        em.createNamedQuery("userByName")
                .setParameter("username", "user22")
                .getSingleResult();// этот метод , если предполагаем извелечение только одной сущности
        System.out.println(user22);


        em.close();
*/
        //  UPDATE:

     /*   User user = em.find(User.class, 1L);// возвращет прокси класс Юзер, который дает сеттерам задание
        // не просто утснаовить значение, но и записать его в БД
        em.getTransaction().begin();

        user.setPassword("jjjkkeee");
        em.getTransaction().commit();


        List<User>  userList = em.createQuery("from User", User.class)
                .getResultList();
        System.out.println(userList);


*/

        // Delete:

        em.getTransaction().begin();

        em.remove(em.find(User.class,2L));

        em.getTransaction().commit();

       /* User user = em.find(User.class, 2L );
        if (user !=null){
            em.remove(user);
        }*/                    //правильнее будет так

    /*    em.createQuery("delete from User where username = :username")
                .setParameter("username", "user2")
                .executeUpdate();  */   // можно еще так - на JPQL


        List<User>  userList = em.createQuery("from User", User.class)
                .getResultList();
        System.out.println(userList);


        em.close();


    }

}