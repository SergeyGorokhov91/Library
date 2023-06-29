package ru.sergey.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sergey.libraryproject.dao.BookDAO;
import ru.sergey.libraryproject.model.Book;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String getBooks(Model model) {
        List<Book> list = bookDAO.index();
        for (Book book: list) {
            System.out.println(book.getBook_id()+","+book.getPerson_id()+", "+book.getName()+", "+book.getAuthor()+", "+book.getYear());
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
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String book(@PathVariable("id") int id, Model model) {
        model.addAttribute("book",bookDAO.show(id));
        return "books/book";
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
}
