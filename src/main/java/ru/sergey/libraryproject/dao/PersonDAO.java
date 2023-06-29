package ru.sergey.libraryproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sergey.libraryproject.model.Person;

import java.util.List;

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person readPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?",
                        new BeanPropertyRowMapper<>(Person.class),id)
                .stream().findAny().orElse(null);
    }

    public void updatePerson(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET fullname=?, birthage=? WHERE person_id=?",
                person.getFullName(),person.getBirthAge(),id);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?",
                id);
    }

    public void createPerson(Person person) {
        jdbcTemplate.update("INSERT INTO person(fullname,birthage) VALUES (?,?)",
                person.getFullName(),person.getBirthAge());
    }
}
