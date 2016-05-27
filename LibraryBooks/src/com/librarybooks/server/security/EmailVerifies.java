package com.librarybooks.server.security;

import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.Date;

@Table(name = "email_virifies")
@Entity
public class EmailVerifies {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field
    @Column(name = "user_id")
    private Long userId;

    @Field
    @Column(name="created_at")
    private Date creatredAt;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getCreatredAt() {
        return creatredAt;
    }
}
