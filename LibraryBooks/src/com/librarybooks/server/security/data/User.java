package com.librarybooks.server.security.data;

import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.Date;

@Table(name = "'user'")
@Entity
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field
    @Column(name = "username", nullable = false)
    private String username;

    @Field
    @Column(name = "password")
    private String password;

    @Field
    @Column(name = "first_name")
    private String firstName;

    @Field
    @Column(name = "second_name")
    private String secondName;

    @Field
    @Column(name = "email")
    private String email;

    @Field
    @Column(name = "birthdate")
    private Date birtthdate;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirtthdate() {
        return birtthdate;
    }
}

