package com.librarybooks.server.security.data;

import com.librarybooks.server.bookservice.datasets.BooksDataSet;

import javax.persistence.*;

/**
 * Created by Bulat on 31.05.16.
 */
@Entity
@Table(name = "order_books")
public class OrderBook {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BooksDataSet booksDataSet;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BooksDataSet getBooksDataSet() {
        return booksDataSet;
    }

    public void setBooksDataSet(BooksDataSet booksDataSet) {
        this.booksDataSet = booksDataSet;
    }
}
