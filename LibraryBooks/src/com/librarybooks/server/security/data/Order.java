package com.librarybooks.server.security.data;

import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.Date;

@Table(name = "order")
@Entity
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field
    @Column(name = "oreder_date", nullable = false)
    private Date orderDate;

    @Field
    @Column(name = "return_date", nullable = false)
    private Date returnDate;

    @Field
    @Column(name = "order_price", nullable = false)
    private Integer orderPrise;

    @Field
    @Column(name = "order_state", nullable = false)
    private String orderState;

    @OneToMany
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getOrderPrise() {
        return orderPrise;
    }

    public void setOrderPrise(Integer orderPrise) {
        this.orderPrise = orderPrise;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
