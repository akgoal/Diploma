package com.librarybooks.server.bookservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.librarybooks.client.BookService;
import com.librarybooks.client.objects.Author;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.Genre;
import com.librarybooks.server.bookservice.dao.BooksDAO;
import com.librarybooks.server.bookservice.datasets.AuthorsDataSet;
import com.librarybooks.server.bookservice.datasets.BooksDataSet;
import com.librarybooks.server.bookservice.datasets.GenresDataSet;

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
			books.add(convertToBook(bds));
		return books;
	}

	@Override
	public ArrayList<Book> findBooksByGenreBook(long callInput) {
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<BooksDataSet> dataSets = dao.getBooksByGenreId(callInput);
		for (BooksDataSet bds : dataSets) {
			books.add(convertToBook(bds));
		}
		System.out.print("Книги жанра:\n");
		for (Book b:books) {
			System.out.print(b.getTitle() + "	АВТОРЫ: ");
			for (Author a:b.getAuthor())
				System.out.print(a.getAuthor() + "	");
			System.out.print("ЖАНРЫ: ");
			for (Genre g:b.getGenre())
				System.out.print(g.getGenre() + "	");
			System.out.print("\n");
		}
		return books;
	}

	@Override
	public ArrayList<Book> sendServer() {
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<BooksDataSet> dataSets = dao.getAllBooks();
		for (BooksDataSet bds : dataSets) {
			books.add(convertToBook(bds));
		}
		System.out.print("ВСЕ КНИГИ:\n");
		for (Book b:books) {
			System.out.print(b.getTitle() + "	АВТОРЫ: ");
			for (Author a:b.getAuthor())
				System.out.print(a.getAuthor() + "	");
			System.out.print("ЖАНРЫ: ");
			for (Genre g:b.getGenre())
				System.out.print(g.getGenre() + "	");
			System.out.print("\n");
		}
		return books;
	}

	@Override
	public Book selectBook(long callInput) {
		BooksDataSet dataSet = dao.getBookById(callInput);
		Book book = convertToBook(dataSet);
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
			genres.add(new Genre(gds.getName(), gds.getId(), gds.getBooks().size()));
		}
		System.out.print("ЖАНРЫ:\n");
		for (Genre g:genres)
			System.out.print(g.getGenre() + "\n");
		return genres;
	}

	private Book convertToBook(BooksDataSet booksDataSet) {
		ArrayList<Author> authors = new ArrayList<>();
		for (AuthorsDataSet ads : booksDataSet.getAuthors())
			authors.add(new Author(ads.getName(), ads.getId(), ads.getBooks().size()));

		ArrayList<Genre> genres = new ArrayList<>();
		for (GenresDataSet gds : booksDataSet.getGenres())
			genres.add(new Genre(gds.getName(), gds.getId(), gds.getBooks().size()));

		return new Book(booksDataSet.getId(), authors, booksDataSet.getTitle(), genres, booksDataSet.getImageName());
	}

}
