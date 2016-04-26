package testproject.server.bookservice.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import testproject.server.bookservice.datasets.AuthorsDataSet;
import testproject.server.bookservice.datasets.BooksDataSet;
import testproject.server.bookservice.datasets.GenresDataSet;

/**
 * Created by Dmitry on 17.04.2016.
 */
@SuppressWarnings("unchecked")
public class BooksDAO implements BooksDAOI {
	private Session session;

	public BooksDAO(Session session) {
		this.session = session;
	}

	@Override
	public ArrayList<BooksDataSet> findBooksByTitle(String title) {
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

	@Override
	public ArrayList<BooksDataSet> getBooksByAuthorId(long authorId) {
		Criteria criteria = session.createCriteria(BooksDataSet.class);
		criteria.createAlias("author", "a");
		criteria.add(Restrictions.eq("a.id", authorId));
		return (ArrayList<BooksDataSet>) criteria.list();
	}

	@Override
	public ArrayList<BooksDataSet> getBooksByGenreId(long genreId) {
		Criteria criteria = session.createCriteria(BooksDataSet.class);
		criteria.createAlias("genre", "g");
		criteria.add(Restrictions.eq("g.id", genreId));
		return (ArrayList<BooksDataSet>) criteria.list();
	}

	@Override
	public AuthorsDataSet getAuthorById(long authorId) {
		Criteria criteria = session.createCriteria(AuthorsDataSet.class);
		criteria.add(Restrictions.idEq(authorId));
		return (AuthorsDataSet) criteria.uniqueResult();
	}

	@Override
	public GenresDataSet getGenreById(long genreId) {
		Criteria criteria = session.createCriteria(GenresDataSet.class);
		criteria.add(Restrictions.idEq(genreId));
		return (GenresDataSet) criteria.uniqueResult();
	}

	@Override
	public void addAuthor(AuthorsDataSet author) {
		session.save(author);		
	}

	@Override
	public void addGenre(GenresDataSet genre) {
		session.save(genre);	
	}

	@Override
	public BooksDataSet getBookById(long bookId) {
		Criteria criteria = session.createCriteria(BooksDataSet.class);
		criteria.add(Restrictions.idEq(bookId));
		return (BooksDataSet) criteria.uniqueResult();
	}

}
