package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoHibernateImpl();

    public UserServiceImpl() {

    }

    public void createUsersTable() {
        userDao.createUsersTable();
        System.out.println("Table created!");
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
        System.out.println("Table deleted!");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println("User saved!");
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
        System.out.println("User removed from table!");

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
        System.out.println("Table cleared!");
    }
}
