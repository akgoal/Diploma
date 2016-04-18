package bookservice.dao;

import bookservice.datasets.BooksDataSet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;

/**
 * Created by Dmitry on 17.04.2016.
 */
public class BooksDAO implements BooksDAOI {
    private Session session;

    public BooksDAO(Session session) {
        this.session = session;
    }

    @Override
    public ArrayList<BooksDataSet> findBooks(String title) {
        Criteria criteria = session.createCriteria(BooksDataSet.class);
        criteria.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE));
        return (ArrayList<BooksDataSet>) criteria.list();
    }

    @Override
    public ArrayList<BooksDataSet> getAllBooks() {
        return (ArrayList<BooksDataSet>) session.createCriteria(BooksDataSet.class).list();
    }

    @Override
    public void addBook(BooksDataSet book) {
        session.save(book);
    }
}
