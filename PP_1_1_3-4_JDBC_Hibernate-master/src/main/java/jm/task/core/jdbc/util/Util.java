package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private final static String URL = "jdbc:mysql://localhost:3306/PP";
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private static SessionFactory sessionFactory;


    public Util() {}


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .setProperty("hibernate.connection.url", URL)
                        .setProperty("hibernate.connection.driver.class", "com.mysql.jdbc.Driver")
                        .setProperty("hibernate.connection.username", USER)
                        .setProperty("hibernate.connection.password", PASSWORD)
                        .setProperty("hibernate.current_session_context_class", "thread")
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .setProperty("show_sql", "true")
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            } catch (HibernateException e) {
                System.out.println(e.getMessage());
                sessionFactory.close();
            }
        }

        return sessionFactory;
    }
}
