package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    private void executeSQLCommand(String sql) {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();

        }
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS USER " +
                " (id BIGINT NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " age TINYINT, " +
                " PRIMARY KEY (id));";
        executeSQLCommand(sql);
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS USER";
        executeSQLCommand(sql);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = String.format("INSERT INTO USER (name, lastName, age) VALUES ('%s', '%s', '%d')", name, lastName, age);
        executeSQLCommand(sql);
    }

    @Override
    public void removeUserById(long id) {
        String sql = String.format("DELETE FROM USER WHERE id = %d;", id);
        executeSQLCommand(sql);
    }

    @Override
    public List<User> getAllUsers() {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            String sql = "SELECT * FROM USER";

            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            List<User> userList = session.createNativeQuery(sql, User.class).getResultList();
            session.getTransaction().commit();

            return userList;
        }
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE USER";
        executeSQLCommand(sql);
    }
}
