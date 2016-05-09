package testproject.server.bookservice.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import testproject.server.bookservice.datasets.AuthorsDataSet;
import testproject.server.bookservice.datasets.BooksDataSet;
import testproject.server.bookservice.datasets.GenresDataSet;

/**
 * Created by Dmitry on 17.04.2016.
 */

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class BooksDAO implements BooksDAOI{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ArrayList<BooksDataSet> findBooksByTitle(String title) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BooksDataSet.class);
		criteria.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE));
		return (ArrayList<BooksDataSet>) criteria.list();
	}

	@Override
	public ArrayList<BooksDataSet> getAllBooks() {
		return (ArrayList<BooksDataSet>) sessionFactory.getCurrentSession().createCriteria(BooksDataSet.class).list();
	}

	@Override
	public void addBook(BooksDataSet book) {
		sessionFactory.getCurrentSession().save(book);
	}

	@Override
	public ArrayList<BooksDataSet> getBooksByAuthorId(long authorId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BooksDataSet.class);
		criteria.createAlias("author", "a");
		criteria.add(Restrictions.eq("a.id", authorId));
		return (ArrayList<BooksDataSet>) criteria.list();
	}

	@Override
	public ArrayList<BooksDataSet> getBooksByGenreId(long genreId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BooksDataSet.class);
		criteria.createAlias("genre", "g");
		criteria.add(Restrictions.eq("g.id", genreId));
		return (ArrayList<BooksDataSet>) criteria.list();
	}

	@Override
	public AuthorsDataSet getAuthorById(long authorId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AuthorsDataSet.class);
		criteria.add(Restrictions.idEq(authorId));
		return (AuthorsDataSet) criteria.uniqueResult();
	}

	@Override
	public GenresDataSet getGenreById(long genreId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GenresDataSet.class);
		criteria.add(Restrictions.idEq(genreId));
		return (GenresDataSet) criteria.uniqueResult();
	}

	@Override
	public void addAuthor(AuthorsDataSet author) {
		sessionFactory.getCurrentSession().save(author);		
	}

	@Override
	public void addGenre(GenresDataSet genre) {
		sessionFactory.getCurrentSession().save(genre);	
	}

	@Override
	public BooksDataSet getBookById(long bookId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BooksDataSet.class);
		criteria.add(Restrictions.idEq(bookId));
		return (BooksDataSet) criteria.uniqueResult();
	}

}
