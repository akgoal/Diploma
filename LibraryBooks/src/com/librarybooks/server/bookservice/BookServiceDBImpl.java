package com.librarybooks.server.bookservice;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarybooks.client.BookService;
import com.librarybooks.client.objects.Author;
import com.librarybooks.client.objects.Book;
import com.librarybooks.client.objects.BookEdit;
import com.librarybooks.client.objects.Genre;
import com.librarybooks.client.objects.Selection;
import com.librarybooks.server.bookservice.dao.BooksDAO;
import com.librarybooks.server.bookservice.datasets.AuthorsDataSet;
import com.librarybooks.server.bookservice.datasets.BooksDataSet;
import com.librarybooks.server.bookservice.datasets.GenresDataSet;
import com.librarybooks.server.bookservice.datasets.SelectionsDataSet;

@Service("bookService")
public class BookServiceDBImpl implements BookService {

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

	@Override
	public ArrayList<Book> searchBooks(ArrayList<String> param) {
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<BooksDataSet> dataSets = dao.searchBooks(param);
		for (BooksDataSet bds : dataSets)
			books.add(convertToDTO(bds));
		return books;
	}

	@Override
	public String titleByIdSelection(long id) {
		SelectionsDataSet sds = dao.getSelectionById(id);
		if (sds != null)
			return sds.getName();
		else
			return null;
	}

	@Override
	public String titleByIdGenre(long id) {
		GenresDataSet gds = dao.getGenreById(id);
		if (gds != null)
			return gds.getName();
		else
			return null;
	}

	@Override
	public String titleByIdAuthor(long id) {
		AuthorsDataSet ads = dao.getAuthorById(id);
		if (ads != null)
			return ads.getName();
		else
			return null;
	}

	@Override
	public void addBook(BookEdit book) {
		BooksDataSet bds = new BooksDataSet();

		bds.setTitle(book.getTitle());
		bds.setOriginalTitle(book.getTitle_original());
		bds.setImageName(book.getImg());
		bds.setCreationYear(Integer.parseInt(book.getYear_create()));
		bds.setPublicationYear(Integer.parseInt(book.getYear_publish()));
		bds.setIsbn(book.getIsbn());
		bds.setPages(Integer.parseInt(book.getCol_pages()));

		String[] authors = book.getAuthor().split(",");
		for (String authorName : authors) {
			bds.getAuthors().add(dao.getOrCreateAuthorByName(authorName));
		}

		String[] genres = book.getGenre().split(",");
		for (String genreName : genres) {
			bds.getGenres().add(dao.getOrCreateGenreByName(genreName));
		}

		bds.setPublisher(dao.getOrCreatePublisherByName(book.getPublish()));

		bds.setBinding(dao.getOrCreateBindingByName(book.getDescription()));

		bds.setDescription(book.getSpecific());

		/*
		 * SimpleDateFormat parserSDF = new SimpleDateFormat("dd.MM.yyyy"); try
		 * { bds.setAdditionDate(parserSDF.parse(book.getAddition_date())); }
		 * catch (ParseException e) { e.printStackTrace(); }
		 */
		bds.setAdditionDate(new Date());

		System.out.println(bds);
		dao.addBook(bds);
	}

	@Override
	public ArrayList<Book> listNew() {
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<BooksDataSet> dataSets = dao.getRecentBooks(20);
		for (BooksDataSet bds : dataSets)
			books.add(convertToDTO(bds));
		return books;
	}

	@Override
	public ArrayList<Book> listPopular() {
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<BooksDataSet> dataSets = dao.getBestBooks(20);
		for (BooksDataSet bds : dataSets)
			books.add(convertToDTO(bds));
		return books;
	}

	@Override
	public ArrayList<Book> listClassic() {
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<BooksDataSet> dataSets = dao.getClassicBooks(1850);
		for (BooksDataSet bds : dataSets)
			books.add(convertToDTO(bds));
		return books;
	}

	@Override
	public ArrayList<Book> listChild() {
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<BooksDataSet> dataSets = dao.getBooksBySelectionName("Лучшие детские книги");
		for (BooksDataSet bds : dataSets)
			books.add(convertToDTO(bds));
		return books;
	}

	@Override
	public ArrayList<Book> listForeign() {
		ArrayList<Book> books = new ArrayList<>();
		ArrayList<BooksDataSet> dataSets = dao.getForeignBooks();
		for (BooksDataSet bds : dataSets)
			books.add(convertToDTO(bds));
		return books;
	}

	/*
	 * Изменение рейтинга книги. В БД рейтинг хранится как числа оценок и самой
	 * оценки.
	 */
	@Override
	public int changeRate(int rate_new) {
		long bookId = 1;
		BooksDataSet bds = dao.getBookById(bookId);
		int currRateInfo = bds.getRate();
		int newRateInfo;
		int newRate;
			int currAmount = currRateInfo / 10;
			int currRate = currRateInfo % 10;
			int newAmount = currAmount + 1;
			newRate = Math.round((currRate * currAmount + rate_new) / newAmount);
			newRateInfo = newAmount * 10 + newRate;
		
		// bds.setRate(newRateInfo);
		return newRate;
	}

	@SuppressWarnings("unused")
	private Book convertToDTODummy(BooksDataSet booksDataSet) {
		ArrayList<Author> authors = new ArrayList<>();

		ArrayList<Genre> genres = new ArrayList<>();

		Book book = new Book();
		book.setIdBook(booksDataSet.getId());
		book.setAuthor(authors);
		book.setTitle(booksDataSet.getTitle());
		book.setGenre(genres);
		book.setImg(booksDataSet.getImageName());

		return book;
	}

	/* Conversions DataSet -> DTO Object */
	private Book convertToDTO(BooksDataSet booksDataSet) {
		ArrayList<Author> authors = new ArrayList<>();
		for (AuthorsDataSet ads : booksDataSet.getAuthors())
			authors.add(convertToDTOWithoutExtraInfo(ads));

		ArrayList<Genre> genres = new ArrayList<>();
		for (GenresDataSet gds : booksDataSet.getGenres())
			genres.add(convertToDTOWithoutExtraInfo(gds));

		Book book = new Book();
		book.setIdBook(booksDataSet.getId());
		book.setAuthor(authors);
		book.setTitle(booksDataSet.getTitle());
		book.setGenre(genres);
		book.setImg(booksDataSet.getImageName());
		book.setCol_pages("" + booksDataSet.getPages());
		book.setCover(booksDataSet.getBinding().getName());
		book.setIsbn(booksDataSet.getIsbn());
		book.setPublish(booksDataSet.getPublisher().getName());
		book.setSpecific(booksDataSet.getDescription());
		book.setYear_create("" + booksDataSet.getCreationYear());
		book.setYear_publish("" + booksDataSet.getPublicationYear());

		return book;
	}

	private Author convertToDTO(AuthorsDataSet authorsDataSet) {
		Author author = new Author();
		author.setAuthor(authorsDataSet.getName());
		author.setIdAuthor(authorsDataSet.getId());
		author.setColBook(authorsDataSet.getBooks().size());
		return author;
	}

	private Author convertToDTOWithoutExtraInfo(AuthorsDataSet authorsDataSet) {
		Author author = new Author();
		author.setAuthor(authorsDataSet.getName());
		author.setIdAuthor(authorsDataSet.getId());
		return author;
	}

	private Genre convertToDTO(GenresDataSet genresDataSet) {
		Genre genre = new Genre();
		genre.setGenre(genresDataSet.getName());
		genre.setIdGenre(genresDataSet.getId());
		genre.setColBook(genresDataSet.getBooks().size());
		return genre;
	}

	private Genre convertToDTOWithoutExtraInfo(GenresDataSet genresDataSet) {
		Genre genre = new Genre();
		genre.setGenre(genresDataSet.getName());
		genre.setIdGenre(genresDataSet.getId());
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
