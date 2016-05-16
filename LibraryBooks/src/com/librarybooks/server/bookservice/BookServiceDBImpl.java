package com.librarybooks.server.bookservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.librarybooks.client.BookService;
import com.librarybooks.client.objects.Author;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Genre;
import com.librarybooks.client.objects.Selection;
import com.librarybooks.server.bookservice.dao.BooksDAO;
import com.librarybooks.server.bookservice.datasets.AuthorsDataSet;
import com.librarybooks.server.bookservice.datasets.BooksDataSet;
import com.librarybooks.server.bookservice.datasets.GenresDataSet;
import com.librarybooks.server.bookservice.datasets.SelectionsDataSet;

@SuppressWarnings("serial")
@Service("bookService")
public class BookServiceDBImpl extends RemoteServiceServlet implements BookService {

	@Autowired
	private BooksDAO dao;

	@Override
	public ArrayList<Book> findBooksByAuthorBook(long callInput) {
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<BooksDataSet> dataSets = dao.getBooksByAuthorId(callInput);
		for (BooksDataSet bds : dataSets)
			books.add(convertToDTO(bds));
		return books;
	}

	@Override
	public ArrayList<Book> findBooksByGenreBook(long callInput) {
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<BooksDataSet> dataSets = dao.getBooksByGenreId(callInput);
		for (BooksDataSet bds : dataSets) {
			books.add(convertToDTO(bds));
		}
		return books;
	}

	@Override
	public ArrayList<Book> sendServer() {
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<BooksDataSet> dataSets = dao.getAllBooks();
		for (BooksDataSet bds : dataSets) {
			books.add(convertToDTO(bds));
		}
		return books;
	}

	@Override
	public Book selectBook(long callInput) {
		BooksDataSet dataSet = dao.getBookById(callInput);
		Book book = convertToDTO(dataSet);
		return book;
	}

	@Override
	public Book bookToServer(Book callInput) {
		Book back = new Book();
		// back.setAuthor(" -> " + input.getAuthor());
		back.setTitle(" -> " + callInput.getTitle());
		return back;
	}

	@Override
	public ArrayList<Genre> listOfGenres() {
		ArrayList<Genre> genres = new ArrayList<>();
		ArrayList<GenresDataSet> dataSets = dao.getAllGenres();
		for (GenresDataSet gds : dataSets) {
			genres.add(convertToDTO(gds));
		}
		return genres;
	}

	@Override
	public ArrayList<Book> findBooksBySelectionBook(long id) {
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<BooksDataSet> dataSets = dao.getBooksBySelectionId(id);
		for (BooksDataSet bds : dataSets)
			books.add(convertToDTO(bds));
		return books;
	}

	@Override
	public ArrayList<Author> listOfAuthors() {
		ArrayList<Author> authors = new ArrayList<>();
		ArrayList<AuthorsDataSet> dataSets = dao.getAllAuthors();
		for (AuthorsDataSet ads : dataSets) {
			authors.add(convertToDTO(ads));
		}
		return authors;
	}

	@Override
	public ArrayList<Selection> listOfSelections() {
		ArrayList<Selection> selections = new ArrayList<>();
		ArrayList<SelectionsDataSet> dataSets = dao.getAllSelections();
		for (SelectionsDataSet sds : dataSets) {
			selections.add(convertToDTO(sds));
		}
		return selections;
	}
	
	/* Поиск книг. 
	 * В первую очередь идут книги с совпадениями в названии, затем в авторах, 
	 * затем в жанрах. Поиск идет по всем словам из param */
	@Override
	public ArrayList<Book> searchBooks(ArrayList<String> param) {
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<BooksDataSet> dataSets = dao.searchBooks(param);
		for (BooksDataSet bds : dataSets)
			books.add(convertToDTO(bds));
		return books;
	}

	/* Conversions DataSet -> DTO Object */
	private Book convertToDTO(BooksDataSet booksDataSet) {
		ArrayList<Author> authors = new ArrayList<>();
		for (AuthorsDataSet ads : booksDataSet.getAuthors())
			authors.add(convertToDTO(ads));

		ArrayList<Genre> genres = new ArrayList<>();
		for (GenresDataSet gds : booksDataSet.getGenres())
			genres.add(convertToDTO(gds));

		Book book = new Book();
		book.setIdBook(booksDataSet.getId());
		book.setAuthor(authors);
		book.setTitle(booksDataSet.getTitle());
		book.setGenre(genres);
		book.setImg(booksDataSet.getImageName());

		return book;
	}

	private Author convertToDTO(AuthorsDataSet authorsDataSet) {
		Author author = new Author();
		author.setAuthor(authorsDataSet.getName());
		author.setIdAuthor(authorsDataSet.getId());
		author.setColBook(authorsDataSet.getBooks().size());
		return author;
	}

	private Genre convertToDTO(GenresDataSet genresDataSet) {
		Genre genre = new Genre();
		genre.setGenre(genresDataSet.getName());
		genre.setIdGenre(genresDataSet.getId());
		genre.setColBook(genresDataSet.getBooks().size());
		return genre;
	}

	private Selection convertToDTO(SelectionsDataSet selectionsDataSet) {
		Selection selection = new Selection();
		selection.setSelection(selectionsDataSet.getName());
		selection.setIdSelection(selectionsDataSet.getId());
		selection.setColBook(selectionsDataSet.getBooks().size());
		return selection;
	}


}
