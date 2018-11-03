package ru.rest;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HibernateDao {

    private Session session;

    public HibernateDao() throws Exception {
        this.session = HibernateSessionFactory.getSessionFactory().openSession();

    }

    public List<Advert> getAllAdverts() {
        List<Advert> result= new ArrayList<>();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Advert a ORDER BY a.id").list();
            for (Object employee : employees) {
                result.add((Advert) employee);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return result;
    }

    public Advert getAdvert(int id) {
        return session.get(Advert.class, id);
    }

    public Advert updateAdvert(Advert advert) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(advert);
            tx.commit();
            return advert;
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public Advert createAdvert(Advert adv) {
        save(adv);
        return adv;
    }

    private void save(Object entity){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            throw new IllegalStateException();
        }
    }

    public void delete(int id) {
        delete(Advert.class, id);
    }

    private <T> boolean delete(Class<T> table, int id){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Object entity = session.get(table, id);
            session.delete(entity);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
