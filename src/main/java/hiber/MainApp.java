package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User user1 = new User("петр","Олегович", "petya@mail.ru");
      User user2 = new User("Василий","Олегович", "Vasya@mail.ru");
      User user3 = new User("Маня","Олегович", "manya@mail.ru");

      Car car1 = new Car("ВАЗ", 2101);
      Car car2 = new Car("ВАЗ", 2102);
      Car car3 = new Car("ВАЗ", 2103);
      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Пользователь" + user.getFirstName() + "добавлен");
          System.out.println(user);
      }
      userService.getUserByCarId(2101);
      context.close();
   }
}
