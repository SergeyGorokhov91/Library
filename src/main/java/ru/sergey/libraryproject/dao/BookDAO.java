package ru.sergey.libraryproject.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sergey.libraryproject.model.Book;
import ru.sergey.libraryproject.model.Person;

import java.util.List;

@Component
@Transactional
public class BookDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Book> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createSelectionQuery("SELECT b FROM Book b",Book.class).getResultList();
    }

    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }

    public Book show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class,id);
    }

    public void update(int id, Book book) {
        Session session = sessionFactory.getCurrentSession();
        Book updatedBook = session.get(Book.class,id);
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setName(book.getName());
        updatedBook.setYear(book.getYear());
    }

    public void deleteBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Book.class,id));
    }

    public List<Book> getBookThatPersonTake(int id) {
//        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?",
//                new BeanPropertyRowMapper<>(Book.class),id);
        Session session = sessionFactory.getCurrentSession();
        return session.createSelectionQuery("SELECT b FROM Book b WHERE b.person=:person",Book.class)
                .setParameter("person",session.get(Person.class,id))
                .getResultList();
    }

    public void attachPersonToBook(int personId, int bookId) {
//        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?",
//                personId,bookId);
        Session session = sessionFactory.getCurrentSession();
        session.createMutationQuery("UPDATE Book SET person=:person WHERE id=:bookId")
                .setParameter("person",session.get(Person.class,personId))
                .setParameter("bookId",bookId)
                .executeUpdate();
    }

    public void unbind(int id) {
//        jdbcTemplate.update("UPDATE book SET person_id=null WHERE book_id=?",
//                id);

        Session session = sessionFactory.getCurrentSession();
        session.createMutationQuery("UPDATE Book b SET b.person=NULL WHERE b.id=:bookId")
                .setParameter("bookId",id)
                .executeUpdate();
    }
}
