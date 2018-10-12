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
            List employees = session.createQuery("FROM Advert").list();
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
        session.update(advert);
        return getAdvert(advert.getId());
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

/*
    @Override
    public List<Competition> getAllCompetitions() {
        List<Competition> result= new ArrayList<>();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Competitions").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();){
                Competitions comp = (Competitions) iterator.next();
                result.add(new Competition(comp.getId(), comp.getName(), comp.getDateOfBegin().toLocalDate()));
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int saveCompetition(Competition competitions) throws Exception {
        Competitions competitionEntity = new Competitions();
        competitionEntity.update(competitions);
        save(competitionEntity);
        return competitionEntity.getId();
    }

    @Override
    public Competition getCompetition(int id) {
        return null;
    }

    @Override
    public boolean updateCompetition(Competition competitions) {
        Competitions entity = session.get(Competitions.class, competitions.getId());
        if(entity != null) {
            entity.setName(competitions.getName());
            entity.setDate(Date.valueOf(competitions.getDateOfBegin()));
            update(entity);
        }
        return false;
    }

    @Override
    public boolean deleteCompetition(int id) {
        return delete(Competitions.class, id);
    }

    @Override
    public boolean saveSportsmen(Sportsmen sportsmen) {
        return false;
    }

    @Override
    public boolean deleteSportsmen(int id) {
        return false;
    }

    @Override
    public boolean editSportsmen() {
        return false;
    }

    @Override
    public Sportsmen getSportsmen(int id) {
        return null;
    }

    @Override
    public List<Category> getCompetitionCategories(Competition competition) {
        List<Category> result = new ArrayList<>();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query= session.createQuery("FROM Categories Where c_competition_id = :idParam");
            query.setParameter("idParam", competition.getId());
            List categories = query.list();
            for (Iterator iterator = categories.iterator(); iterator.hasNext();){
                Categories entity = (Categories) iterator.next();
                result.add(new Category(
                        entity.getId(),
                        entity.getSex(),
                        entity.getEight(),
                        entity.getWeight(),
                        entity.getWeightType(),
                        entity.getEightType(),
                        competition,
                        entity.getName()
                    )
                );
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public int saveCategory(Category category) {
        Categories entity = new Categories();
        entity.update(category);
        save(entity);
        return entity.getId();
    }

    @Override
    public boolean updateCategory(Category category) {
        Categories entity = session.get(Categories.class, category.getId());
        if(entity != null) {
            entity.update(category);
            return update(entity);
        }
        return false;
    }

    @Override
    public boolean deleteCategory(int id) {
        return delete(Categories.class, id);
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

    private boolean update(Object entity){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            return false;
        }
        return true;
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

    @Override
    public boolean saveAllCategories(List<Category> categories) {
        try{
            categories.forEach(this::saveCategory);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Sportsmen> getCategorySportsmen(Category category) {
        List<Category> result = new ArrayList<>();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query= session.createQuery("FROM Categories Where c_competition_id = :idParam");
            query.setParameter("idParam", category.getId());
            List categories = query.list();
            for (Iterator iterator = categories.iterator(); iterator.hasNext();){
                Categories entity = (Categories) iterator.next();
                result.add(new Sportsmen(
                                entity.getId(),
                                entity.getSex(),
                                entity.getEight(),
                                entity.getWeight(),
                                entity.getWeightType(),
                                entity.getEightType(),
                        category,
                                entity.getName()
                        )
                );
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return result;
    }
    */
}
