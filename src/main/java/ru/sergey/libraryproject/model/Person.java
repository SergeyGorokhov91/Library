package ru.sergey.libraryproject.model;

public class Person {

    private int person_id;
    private String fullName;
    private int birthAge;

    public Person() {
    }

    public Person(String fullName, int birthAge) {
        this.fullName = fullName;
        this.birthAge = birthAge;

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

    public int getBirthAge() {
        return birthAge;
    }

    public void setBirthAge(int birthAge) {
        this.birthAge = birthAge;
    }

}
