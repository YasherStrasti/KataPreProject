package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Oleg", "Olegov", (byte) 20);
        userService.saveUser("Kirill", "Kirillov", (byte) 25);
        userService.saveUser("Alex", "Alexov", (byte) 31);
        userService.saveUser("Sasha", "Sashov", (byte) 38);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
