package com.librarybooks.server.bookservice.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.librarybooks.server.bookservice.datasets.AuthorsDataSet;
import com.librarybooks.server.bookservice.datasets.BooksDataSet;
import com.librarybooks.server.bookservice.datasets.GenresDataSet;
import com.librarybooks.server.bookservice.datasets.SelectionsDataSet;

/**
 * Created by Dmitry on 17.04.2016.
 */

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class BooksDAOImpl implements BooksDAO {

	private static boolean booksAreIndexed = false;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void indexBooks() {
		FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
		try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<BooksDataSet> findBooksByTitle(String title) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BooksDataSet.class);
		criteria.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE));
		return initializeLazyMembers((ArrayList<BooksDataSet>) criteria.list());
	}

	@Override
	public ArrayList<BooksDataSet> getAllBooks() {
		return initializeLazyMembers(
				(ArrayList<BooksDataSet>) sessionFactory.getCurrentSession().createCriteria(BooksDataSet.class).list());
	}

	@Override
	public void addBook(BooksDataSet book) {
		sessionFactory.getCurrentSession().save(book);
	}

	@Override
	public ArrayList<BooksDataSet> getBooksByAuthorId(long authorId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AuthorsDataSet.class);
		criteria.add(Restrictions.idEq(authorId));
		ArrayList<BooksDataSet> bdsList = castToArrayList(
				initializeLazyMembers((AuthorsDataSet) criteria.uniqueResult()).getBooks());
		return initializeLazyMembers(bdsList);
	}

	@Override
	public ArrayList<BooksDataSet> getBooksByGenreId(long genreId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(GenresDataSet.class);
		criteria.add(Restrictions.idEq(genreId));
		ArrayList<BooksDataSet> bdsList = castToArrayList(
				initializeLazyMembers((GenresDataSet) criteria.uniqueResult()).getBooks());
		return initializeLazyMembers(bdsList);
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
		return initializeLazyMembers((BooksDataSet) criteria.uniqueResult());
	}

	@Override
	public ArrayList<GenresDataSet> getAllGenres() {
		ArrayList<GenresDataSet> gdsList = (ArrayList<GenresDataSet>) sessionFactory.getCurrentSession()
				.createCriteria(GenresDataSet.class).list();
		for (GenresDataSet gds : gdsList)
			initializeLazyMembers(gds);
		return gdsList;
	}

	@Override
	public ArrayList<BooksDataSet> getBooksBySelectionId(long selectionId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SelectionsDataSet.class);
		criteria.add(Restrictions.idEq(selectionId));
		ArrayList<BooksDataSet> bdsList = castToArrayList(
				initializeLazyMembers((SelectionsDataSet) criteria.uniqueResult()).getBooks());
		return initializeLazyMembers(bdsList);
	}

	@Override
	public ArrayList<AuthorsDataSet> getAllAuthors() {
		ArrayList<AuthorsDataSet> adsList = (ArrayList<AuthorsDataSet>) sessionFactory.getCurrentSession()
				.createCriteria(AuthorsDataSet.class).list();
		for (AuthorsDataSet ads : adsList)
			initializeLazyMembers(ads);
		return adsList;
	}

	@Override
	public ArrayList<SelectionsDataSet> getAllSelections() {
		ArrayList<SelectionsDataSet> sdsList = (ArrayList<SelectionsDataSet>) sessionFactory.getCurrentSession()
				.createCriteria(SelectionsDataSet.class).list();
		for (SelectionsDataSet sds : sdsList)
			initializeLazyMembers(sds);
		return sdsList;
	}

	/*
	 * Полнотекстовый поиск по названию, оригинальному названию, авторам и
	 * жанрам
	 */
	@Override
	public ArrayList<BooksDataSet> searchBooks(List<String> words) {

		/*
		 * Criteria criteria =
		 * sessionFactory.getCurrentSession().createCriteria(BooksDataSet.class)
		 * ; for (String word : words) criteria.add(Restrictions.ilike("title",
		 * word, MatchMode.ANYWHERE)); ArrayList<BooksDataSet> res =
		 * (ArrayList<BooksDataSet>) criteria.list();
		 * 
		 * return initializeLazyMembersCompletely(res);
		 */

		if (!booksAreIndexed) {
			indexBooks();
			booksAreIndexed = true;
		}

		FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());

		org.hibernate.search.query.dsl.QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder()
				.forEntity(BooksDataSet.class).get();

		org.apache.lucene.search.BooleanQuery.Builder bqBuilder = new BooleanQuery.Builder();

		for (String keyword : words) {
			bqBuilder.add(qb.keyword().onFields("title", "originalTitle", "authors.name", "genres.name")
					.matching(keyword).createQuery(), BooleanClause.Occur.MUST);
		}
		org.apache.lucene.search.BooleanQuery bquery = bqBuilder.build();

		FullTextQuery ftq = fullTextSession.createFullTextQuery(bquery, BooksDataSet.class);

		ArrayList<BooksDataSet> res;
		List<BooksDataSet> queryRes = ftq.list();
		if (queryRes.isEmpty())
			res = new ArrayList<BooksDataSet>();
		else
			res = initializeLazyMembers((ArrayList<BooksDataSet>) queryRes);
		return res;
	}

	/* Lazy loading of all members */
	private BooksDataSet initializeLazyMembers(BooksDataSet bds) {
		Hibernate.initialize(bds.getAuthors());
		Hibernate.initialize(bds.getGenres());
		Hibernate.initialize(bds.getPublisher());
		Hibernate.initialize(bds.getBinding());
		return bds;
	}

	private GenresDataSet initializeLazyMembers(GenresDataSet gds) {
		Hibernate.initialize(gds.getBooks());
		return gds;
	}

	private AuthorsDataSet initializeLazyMembers(AuthorsDataSet ads) {
		Hibernate.initialize(ads.getBooks());
		return ads;
	}

	private SelectionsDataSet initializeLazyMembers(SelectionsDataSet sds) {
		Hibernate.initialize(sds.getBooks());
		return sds;
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

	@SuppressWarnings("unused")
	private ArrayList<BooksDataSet> initializeLazyMembersCompletely(ArrayList<BooksDataSet> bdsList) {
		for (BooksDataSet bds : bdsList)
			initializeLazyMembersCompletely(bds);
		return bdsList;
	}

	private ArrayList<BooksDataSet> initializeLazyMembers(ArrayList<BooksDataSet> bdsList) {
		for (BooksDataSet bds : bdsList)
			initializeLazyMembers(bds);
		return bdsList;
	}

	/* Cast from Set to ArrayList */
	private <T> ArrayList<T> castToArrayList(Set<T> set) {
		ArrayList<T> list = new ArrayList<T>();
		for (T t : set)
			list.add(t);
		return list;
	}

}
