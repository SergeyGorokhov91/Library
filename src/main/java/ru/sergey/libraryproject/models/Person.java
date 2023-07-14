package ru.sergey.libraryproject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name="person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Column(name="fullname")
    private String fullName;

    @Min(value = 1900,message = "Birth year cant be less than 1900")
    @Max(value = 2023,message = "Birth year cant be bigger than 2023")
    @NotNull(message = "birth year cant be empty")
    @Column(name = "birthyear")
    private Integer birthYear;

    @OneToMany(mappedBy = "owner")
    private Set<Book> books;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (fullName != null ? !fullName.equals(person.fullName) : person.fullName != null) return false;
        return birthYear != null ? birthYear.equals(person.birthYear) : person.birthYear == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (birthYear != null ? birthYear.hashCode() : 0);
        return result;
    }
}
