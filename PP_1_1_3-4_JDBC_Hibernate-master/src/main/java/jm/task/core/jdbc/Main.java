package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;


import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException {

        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Oleg", "Olegov", (byte) 20);
        userDao.saveUser("Kirill", "Kirillov", (byte) 25);
        userDao.saveUser("Alex", "Alexov", (byte) 31);
        userDao.saveUser("Sasha", "Sashov", (byte) 38);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();


    }
}
