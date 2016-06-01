package com.librarybooks.server.security.data;

import com.librarybooks.server.bookservice.datasets.BooksDataSet;

import javax.persistence.*;
import java.util.List;

@Table(name = "orders")
@Entity
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false, insertable = false)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "order_books", joinColumns = {
            @JoinColumn(name = "order_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "book_id",
                    nullable = false, updatable = false)})
    private List<BooksDataSet> orderBooks;

    @Column(name = "price", nullable = false)
    private String price;
    @Column(name = "date", nullable = false)
    private String date;
    @Column(name = "date_back", nullable = false)
    private String dateBack;
    @Column(name = "order_num", nullable = false)
    private int orderNum;
    @Column(name = "state", nullable = false)
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BooksDataSet> getOrderBooks() {
        return orderBooks;
    }

    public void setOrderBooks(List<BooksDataSet> orderBooks) {
        this.orderBooks = orderBooks;
    }


    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDateBack(String dateBack) {
        this.dateBack = dateBack;
    }

    public String getDateBack() {
        return dateBack;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
