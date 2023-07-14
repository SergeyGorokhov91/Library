package ru.sergey.libraryproject.services;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergey.libraryproject.models.Book;
import ru.sergey.libraryproject.models.Person;
import ru.sergey.libraryproject.repositories.BookRepository;
import ru.sergey.libraryproject.repositories.PersonRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class BookService {

    final private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Set<Book> findAll() {
        return new HashSet<>(bookRepository.findAll());
    }

    public Book findById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);//тут можно кинуть exception, но для простоты так
    }

    @Transactional//переопределяем, ибо над классом стоит readOnly = true
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id,Book book) {
        book.setId(id);
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional

    public List<Book> findBooksByOwner(int id) {
        return bookRepository.findBooksByOwnerId(id);
    }

    @Transactional
    public void attachPersonToBook(int personId, int bookId) {
        Person person = new Person();
        person.setId(personId);
        bookRepository.attachPersonToBook(person,bookId);
    }

    @Transactional
    public void unbind(int id) {
        bookRepository.unbindPersonFromBook(id);
    }
}
