package com.librarybooks.server.bookservice.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.librarybooks.server.bookservice.datasets.AuthorsDataSet;
import com.librarybooks.server.bookservice.datasets.BooksDataSet;
import com.librarybooks.server.bookservice.datasets.GenresDataSet;

/**
 * Created by Dmitry on 17.04.2016.
 */

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class BooksDAOImpl implements BooksDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ArrayList<BooksDataSet> findBooksByTitle(String title) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BooksDataSet.class);
		criteria.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE));
		return initializeLazyMembersCompletely((ArrayList<BooksDataSet>) criteria.list());
	}

	@Override
	public ArrayList<BooksDataSet> getAllBooks() {
		return initializeLazyMembersCompletely(
				(ArrayList<BooksDataSet>) sessionFactory.getCurrentSession().createCriteria(BooksDataSet.class).list());
	}

	@Override
	public void addBook(BooksDataSet book) {
		sessionFactory.getCurrentSession().save(book);
	}

	@Override
	public ArrayList<BooksDataSet> getBooksByAuthorId(long authorId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BooksDataSet.class)
				.createCriteria("authors").add(Restrictions.eq("id", authorId));
		return initializeLazyMembersCompletely((ArrayList<BooksDataSet>) criteria.list());
	}

	@Override
	public ArrayList<BooksDataSet> getBooksByGenreId(long genreId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BooksDataSet.class)
				.createCriteria("genres").add(Restrictions.eq("id", genreId));
		return initializeLazyMembersCompletely((ArrayList<BooksDataSet>) criteria.list());
	}

	@Override
	public AuthorsDataSet getAuthorById(long authorId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AuthorsDataSet.class);
		criteria.add(Restrictions.idEq(authorId));
		return initializeLazyMembers((AuthorsDataSet) criteria.uniqueResult());
	}

	@Override
	public GenresDataSet getGenreById(long genreId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GenresDataSet.class);
		criteria.add(Restrictions.idEq(genreId));
		return initializeLazyMembers((GenresDataSet) criteria.uniqueResult());
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
		return initializeLazyMembersCompletely((BooksDataSet) criteria.uniqueResult());
	}

	@Override
	public ArrayList<GenresDataSet> getAllGenres() {
		ArrayList<GenresDataSet> gdsList = (ArrayList<GenresDataSet>) sessionFactory.getCurrentSession()
				.createCriteria(GenresDataSet.class).list();
		for (GenresDataSet gds : gdsList)
			initializeLazyMembers(gds);
		return gdsList;
	}

	/* Lazy loading of all members */
	@SuppressWarnings("unused")
	private BooksDataSet initializeLazyMembers(BooksDataSet bds) {
		Hibernate.initialize(bds.getAuthors());
		Hibernate.initialize(bds.getGenres());
		Hibernate.initialize(bds.getPublisher());
		Hibernate.initialize(bds.getBinding());
		return bds;
	}

	private ArrayList<BooksDataSet> initializeLazyMembersCompletely(ArrayList<BooksDataSet> bdsList) {
		for (BooksDataSet bds : bdsList)
			initializeLazyMembersCompletely(bds);
		return bdsList;
	}

	private GenresDataSet initializeLazyMembers(GenresDataSet gds) {
		Hibernate.initialize(gds.getBooks());
		return gds;
	}

	private AuthorsDataSet initializeLazyMembers(AuthorsDataSet ads) {
		Hibernate.initialize(ads.getBooks());
		return ads;
	}
	
	/* Lazy loading of all members with all their members */
	private BooksDataSet initializeLazyMembersCompletely(BooksDataSet bds) {
		Hibernate.initialize(bds.getAuthors());
		for (AuthorsDataSet ads : bds.getAuthors())
			initializeLazyMembers(ads);
		Hibernate.initialize(bds.getGenres());
		for (GenresDataSet gds : bds.getGenres())
			initializeLazyMembers(gds);
		Hibernate.initialize(bds.getPublisher());
		Hibernate.initialize(bds.getBinding());
		return bds;
	}

}