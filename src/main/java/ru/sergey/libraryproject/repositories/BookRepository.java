package ru.sergey.libraryproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sergey.libraryproject.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}