package ru.sergey.libraryproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Column(name = "fullname")
    private String fullName;

    @Min(value = 1900,message = "Birth year cant be less than 1900")
    @Max(value = 2023,message = "Birth year cant be bigger than 2023")
    @NotNull(message = "birth year cant be empty")
    @Column(name="birthyear")
    private Integer birthYear;

    @OneToMany(mappedBy = "person")
    private List<Book> books;

    public Person() {
    }

    public Person(String fullName, Integer birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

}
