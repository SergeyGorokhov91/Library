package ru.sergey.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sergey.libraryproject.dao.BookDAO;
import ru.sergey.libraryproject.dao.PersonDAO;
import ru.sergey.libraryproject.model.Book;
import ru.sergey.libraryproject.model.Person;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String getBooks(Model model) {
        List<Book> list = bookDAO.index();
        model.addAttribute("books",list);
        return "/books/index";
    }

    @GetMapping("/new")
    public String createBookForm(@ModelAttribute("book") Book book){
        return "/books/new";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") Book book) {
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String book(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book",bookDAO.show(id));
        Person personThatTakeBook= bookDAO.show(id).getPerson();
        if(personThatTakeBook == null) model.addAttribute("peopleList",personDAO.index());
        else model.addAttribute("personById",personDAO.readPerson(personThatTakeBook.getId()));
        return "books/book";
    }

    @PostMapping("/{id}/attach")
    public String attachBook(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        bookDAO.attachPersonToBook(person.getId(), id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/unbind")
    public String unbindBook(@PathVariable("id") int id) {
        bookDAO.unbind(id);
        return "redirect:/books/{id}";
    }

    @GetMapping("/{id}/edit")
    public String bookEditFrom(@PathVariable("id") int id, Model model) {
        model.addAttribute("book",bookDAO.show(id));
        return "books/update";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDAO.update(id,book);
        return "redirect:/books";
    }

    @DeleteMapping("{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }
}
