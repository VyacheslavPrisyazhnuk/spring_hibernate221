package hiber.dao;

import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   public void getUserByCarId(int model) {
     try (Session session = sessionFactory.openSession()) {
         String HQL="FROM User user LEFT OUTER JOIN FETCH user.car WHERE  user.car.series =:car_id";
         List <User> list = session.createQuery(HQL, User.class).setParameter("car_id", model).list();
         for (User item: list) {
             System.out.println("вывод юзера по серии автоваза");
             System.out.println(item);
         }
      } catch (HibernateException e) {
         e.printStackTrace();
      }

   }
}
