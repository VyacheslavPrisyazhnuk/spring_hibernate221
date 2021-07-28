package hiber.dao;

import hiber.config.AppConfig;
import hiber.model.Car;
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
         String HQL="FROM Car car LEFT OUTER JOIN FETCH car.user WHERE  car.series =:car_id";
         List <Car> list = session.createQuery(HQL, Car.class).setParameter("car_id", model).list();
         for (Car item: list) {
             System.out.println("вывод юзера по серии автоваза");
             System.out.println(item.getUser());
         }
      } catch (HibernateException e) {
         e.printStackTrace();
      }

   }
}
