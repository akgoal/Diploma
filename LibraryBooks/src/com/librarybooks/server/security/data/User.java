package com.librarybooks.server.security.data;

import org.hibernate.search.annotations.Field;

import javax.persistence.*;

@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "uni_user1", columnNames = {"username"}),
        @UniqueConstraint(name = "uni_user2", columnNames = {"email"}),
})
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
    @Column(name = "password", nullable = false)
    private String password;

    @Field
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Field
    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Field
    @Column(name = "middle_name")
    private String middleName;

    @Field
    @Column(name = "email", nullable = false)
    private String email;

    @Field
    @Column(name = "email_verified")
    private Boolean emailVerified;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
}

