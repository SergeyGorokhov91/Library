package ru.sergey.libraryproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sergey.libraryproject.models.Book;
import ru.sergey.libraryproject.models.Person;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksByOwnerId(int id);

    @Modifying
    @Query("UPDATE Book b SET b.owner=?1 WHERE b.id=?2")
    void attachPersonToBook(Person person,int bookId);

    @Modifying
    @Query("UPDATE Book b SET b.owner=null WHERE b.id=?1")
    void unbindPersonFromBook(int bookId);
}
