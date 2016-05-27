package com.librarybooks.server.security.data;

import javax.persistence.*;
import java.security.acl.LastOwnerException;

@Table(name = "user_books")
@Entity
public class UserBooks {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "book_id")
    private Long bookId;

    public Long getId(){return id;}

    public Long getUserId(){return userId;}

    public Long getBookId(){return bookId;}

}

