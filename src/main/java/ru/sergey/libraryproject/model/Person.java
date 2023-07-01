package ru.sergey.libraryproject.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Person {

    private int person_id;

    @NotEmpty(message = "Name should not be empty")
    private String fullName;

    @Min(value = 1900,message = "Birth year cant be less than 1900")
    @Max(value = 2023,message = "Birth year cant be bigger than 2023")
    @NotNull(message = "birth year cant be empty")
    private Integer birthYear;

    public Person() {
    }

    public Person(String fullName, Integer birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;

    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
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
