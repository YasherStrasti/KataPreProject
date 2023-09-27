package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    private void executeSQLCommand(String sql) {
        try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.createSQLQuery(sql).executeUpdate();
                session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            if (sessionFactory.getCurrentSession().getTransaction() == null) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
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
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (sessionFactory.getCurrentSession().getTransaction() == null) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = String.format("DELETE FROM USER WHERE id = %d;", id);
        executeSQLCommand(sql);
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM User";
            Query query = session.createQuery(hql);
            List<User> users = query.getResultList();

            transaction.commit();

            return users;
        } catch (Exception e) {
            e.printStackTrace();
            if (sessionFactory.getCurrentSession().getTransaction() == null) {
                sessionFactory.getCurrentSession().getTransaction().rollback();
            }
        }

        return new ArrayList<>();
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE USER";
        executeSQLCommand(sql);
    }
}
