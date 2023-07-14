package ru.sergey.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sergey.libraryproject.models.Book;
import ru.sergey.libraryproject.models.Person;
import ru.sergey.libraryproject.services.BookService;
import ru.sergey.libraryproject.services.PersonService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/books")
public class BookController {
    private PersonService personService;
    private BookService bookService;

    @Autowired
    public BookController(PersonService personService, BookService bookService) {
        this.personService = personService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getBooks(Model model) {
        Set<Book> list = bookService.findAll();
        for (Book book: list) {
            System.out.println(book.getId()+", "+book.getName()+", "+book.getAuthor()+", "+book.getYear());
        }
        model.addAttribute("books",list);
        return "/books/index";
    }

    @GetMapping("/new")
    public String createBookForm(@ModelAttribute("book") Book book){
        return "/books/new";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String book(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book",bookService.findById(id));
        Person personWithBook = bookService.findById(id).getOwner();
        if(personWithBook == null) model.addAttribute("peopleList",personService.findAll());
        else model.addAttribute("personById",personService.findById(personWithBook.getId()));
        return "books/book";
    }

    @PostMapping("/{id}/attach")
    public String attachBook(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        bookService.attachPersonToBook(person.getId(), id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/unbind")
    public String unbindBook(@PathVariable("id") int id) {
        bookService.unbind(id);
        return "redirect:/books/{id}";
    }

    @GetMapping("/{id}/edit")
    public String bookEditFrom(@PathVariable("id") int id, Model model) {
        model.addAttribute("book",bookService.findById(id));
        return "books/update";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookService.update(id,book);
        return "redirect:/books";
    }

    @DeleteMapping("{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
