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
   public void getUserByCarId(String model, int series) {
     try (Session session = sessionFactory.openSession()) {
         String HQL="FROM User WHERE car.model =:mod  and car.series =: ser";
         User user = session.createQuery(HQL, User.class).setParameter("mod", model).setParameter("ser", series).getSingleResult();
             System.out.println("вывод юзера по серии автоваза");
             System.out.println(user);
      } catch (HibernateException e) {
         e.printStackTrace();
      }

   }
}
