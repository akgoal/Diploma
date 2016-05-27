package com.librarybooks.server.bookservice;

import java.util.ArrayList;

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

		setDTOToDataset(book, bds);

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
	public int changeRate(long id, int rate_new) {
		BooksDataSet bds = dao.getBookById(id);
		float currRateInfo = bds.getRate();
		int currAmount = (int) currRateInfo / 10;
		float currRate = currRateInfo - currAmount * 10;
		int newAmount = currAmount + 1;
		float newRate = (currRate * currAmount + rate_new) / newAmount;
		float newRateInfo = newAmount * 10 + newRate;

		bds.setRate(newRateInfo);
		dao.updateBook(bds);
		return Math.round(newRate);
	}

	@Override
	public BookEdit selectBookEdit(long id) {
		BooksDataSet dataSet = dao.getBookById(id);
		BookEdit book = convertToDTOBookEdit(dataSet);
		return book;
	}

	@Override
	public void EditBook(long id, BookEdit book) {
		BooksDataSet bds = dao.getBookById(id);
		setDTOToDataset(book, bds);
		dao.updateBook(bds);
	}

	@Override
	public void DelBook(long id) {
		dao.deleteBookById(id);
	}

	/* Conversions DataSet -> DTO */
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
		if (booksDataSet.getPages() != 0)
			book.setCol_pages("" + booksDataSet.getPages());
		else
			book.setCol_pages(null);

		if (booksDataSet.getBinding() != null)
			book.setCover(booksDataSet.getBinding().getName());
		else
			book.setCover(null);
		book.setIsbn(booksDataSet.getIsbn());
		if (booksDataSet.getPublisher() != null)
			book.setPublish(booksDataSet.getPublisher().getName());
		else
			book.setPublish(null);
		book.setSpecific(booksDataSet.getDescription());
		if (booksDataSet.getCreationYear() != 0)
			book.setYear_create("" + booksDataSet.getCreationYear());
		else
			book.setYear_create(null);
		if (booksDataSet.getPublicationYear() != 0)
			book.setYear_publish("" + booksDataSet.getPublicationYear());
		else
			book.setYear_publish(null);
		if (booksDataSet.getPrice() != 0)
			book.setPrice("" + booksDataSet.getPrice());
		else
			book.setPrice(null);

		float rateInfo = booksDataSet.getRate();
		int amount = (int) rateInfo / 10;
		float rate = rateInfo - amount * 10;
		book.setRate(Math.round(rate));

		return book;
	}

	private BookEdit convertToDTOBookEdit(BooksDataSet booksDataSet) {
		BookEdit book = new BookEdit();
		book.setTitle(booksDataSet.getTitle());
		book.setTitle_original(booksDataSet.getOriginalTitle());

		if (booksDataSet.getAuthors() != null) {
			StringBuilder sb = new StringBuilder();
			int count = 0;
			int size = booksDataSet.getAuthors().size();
			for (AuthorsDataSet ads : booksDataSet.getAuthors()) {
				sb.append(ads.getName());
				count++;
				if (count != size)
					sb.append(",");
			}
			book.setAuthor(sb.toString());
		} else
			book.setAuthor(null);

		if (booksDataSet.getGenres() != null) {
			StringBuilder sb = new StringBuilder();
			int count = 0;
			int size = booksDataSet.getGenres().size();
			for (GenresDataSet gds : booksDataSet.getGenres()) {
				sb.append(gds.getName());
				count++;
				if (count != size)
					sb.append(",");
			}
			book.setGenre(sb.toString());
		} else
			book.setGenre(null);

		book.setImg(booksDataSet.getImageName());
		if (booksDataSet.getCreationYear() != 0)
			book.setYear_create("" + booksDataSet.getCreationYear());
		else
			book.setYear_create(null);
		if (booksDataSet.getPublisher() != null)
			book.setPublish(booksDataSet.getPublisher().getName());
		else
			book.setPublish(null);
		if (booksDataSet.getPublicationYear() != 0)
			book.setYear_publish("" + booksDataSet.getPublicationYear());
		else
			book.setYear_publish(null);
		// ********************
		if (booksDataSet.getIsbn() != null)
			book.setIsbn(booksDataSet.getIsbn());
		else book.setIsbn("");
		// book.setIsbn(booksDataSet.getIsbn());
		// ********************
		if (booksDataSet.getPages() != 0)
			book.setCol_pages("" + booksDataSet.getPages());
		else
			book.setCol_pages(null);
		if (booksDataSet.getBinding() != null)
			book.setDescription(booksDataSet.getBinding().getName());
		else
			book.setDescription(null);
		book.setSpecific(booksDataSet.getDescription());
		book.setAddition_date(booksDataSet.getAdditionDate());
		if (booksDataSet.getPrice() != 0)
			book.setPrice("" + booksDataSet.getPrice());
		else
			book.setPrice(null);

		return book;
	}

	private Author convertToDTO(AuthorsDataSet authorsDataSet) {
		Author author = new Author();
		author.setAuthor(authorsDataSet.getName());
		author.setIdAuthor(authorsDataSet.getId());
		if (authorsDataSet.getBooks() != null)
			author.setColBook(authorsDataSet.getBooks().size());
		else
			author.setColBook(0);
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
		if (genresDataSet.getBooks() != null)
			genre.setColBook(genresDataSet.getBooks().size());
		else
			genre.setColBook(0);
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
		if (selectionsDataSet.getBooks() != null)
			selection.setColBook(selectionsDataSet.getBooks().size());
		else
			selection.setColBook(0);
		return selection;
	}

	/* set DTO values to Dataset object */
	private void setDTOToDataset(BookEdit bookEdit, BooksDataSet booksDataSet) {
		booksDataSet.setTitle(bookEdit.getTitle());
		booksDataSet.setOriginalTitle(bookEdit.getTitle_original());
		booksDataSet.setImageName(bookEdit.getImg());
		if (bookEdit.getYear_create() != null)
			booksDataSet.setCreationYear(Integer.parseInt(bookEdit.getYear_create()));
		else
			booksDataSet.setCreationYear(0);
		if (bookEdit.getYear_publish() != null)
			booksDataSet.setPublicationYear(Integer.parseInt(bookEdit.getYear_publish()));
		else
			booksDataSet.setPublicationYear(0);
		booksDataSet.setIsbn(bookEdit.getIsbn());
		if (bookEdit.getCol_pages() != null)
			booksDataSet.setPages(Integer.parseInt(bookEdit.getCol_pages()));
		else
			booksDataSet.setPages(0);

		if (bookEdit.getAuthor() != null) {
			String[] authors = bookEdit.getAuthor().split(",");
			for (String authorName : authors) {
				booksDataSet.getAuthors().add(dao.getOrCreateAuthorByName(authorName));
			}
		} else
			booksDataSet.setAuthors(null);

		if (bookEdit.getGenre() != null) {
			String[] genres = bookEdit.getGenre().split(",");
			for (String genreName : genres) {
				booksDataSet.getGenres().add(dao.getOrCreateGenreByName(genreName));
			}
		} else
			booksDataSet.setGenres(null);

		if (bookEdit.getPublish() != null)
			booksDataSet.setPublisher(dao.getOrCreatePublisherByName(bookEdit.getPublish()));
		else
			booksDataSet.setPublisher(null);
		if (bookEdit.getDescription() != null)
			booksDataSet.setBinding(dao.getOrCreateBindingByName(bookEdit.getDescription()));
		else
			booksDataSet.setBinding(null);
		booksDataSet.setDescription(bookEdit.getSpecific());
		booksDataSet.setAdditionDate(bookEdit.getAddition_date());

		if (bookEdit.getPrice() != null)
			booksDataSet.setPrice(Integer.parseInt(bookEdit.getPrice()));
		else
			booksDataSet.setPrice(0);
	}

}
