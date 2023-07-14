package ru.sergey.libraryproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sergey.libraryproject.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
