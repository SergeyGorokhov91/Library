package ru.sergey.libraryproject.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sergey.libraryproject.model.Person;

import javax.security.auth.login.Configuration;
import java.util.List;

@Component
@Transactional
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createSelectionQuery("SELECT p FROM Person p",Person.class).getResultList();
    }

    public Person readPerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }

    public void updatePerson(int id, Person person) {
        Session session = sessionFactory.getCurrentSession();
        Person updatedPerson = session.get(Person.class,id);
        updatedPerson.setBirthYear(person.getBirthYear());
        updatedPerson.setFullName(person.getFullName());
    }

    public void deletePerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class,id));
    }

    public void createPerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }
}
