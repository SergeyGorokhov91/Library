package ru.sergey.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String createBook(){
        return "/books/new";
    }
}
