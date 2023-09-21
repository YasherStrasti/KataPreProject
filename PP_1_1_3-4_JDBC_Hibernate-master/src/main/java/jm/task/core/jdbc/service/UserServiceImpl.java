package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

//    вывод на консоль приведи в соответствие с тем, что указано в алгоритме работы приложения, лишних выводов на консоль не должно быть
    private final UserDao userDao = new UserDaoHibernateImpl();

    public UserServiceImpl() {

    }

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.printf("User с именем - %s добавлен в базу данных!", name);
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
        System.out.printf("User with id: %d removed from table!", id);

    }

    public List<User> getAllUsers() {
        List<User> userList = userDao.getAllUsers();
        for (User u : userList) {
            System.out.println(u.toString());
        }
        return userList;
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
