package ru.sergey.libraryproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sergey.libraryproject.model.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (name, author, year) VALUES (?,?,?)",
                book.getName(),book.getAuthor(),book.getYear());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?",
                new BeanPropertyRowMapper<>(Book.class),id)
                .stream().findAny().orElse(null);
    }

    public void update(int book_id, Book book) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?,year=? WHERE book_id=?",
                book.getName(),book.getAuthor(),book.getYear(),book_id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?",id);
    }

    public List<Book> getBookThatPersonTake(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?",
                new BeanPropertyRowMapper<>(Book.class),id);
    }

    public void attachPersonToBook(int personId, int bookId) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?",
                personId,bookId);
    }

    public void unbind(int id) {
        jdbcTemplate.update("UPDATE book SET person_id=null WHERE book_id=?",
                id);
    }
}
