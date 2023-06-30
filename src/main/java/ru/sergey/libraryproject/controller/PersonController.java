package ru.sergey.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sergey.libraryproject.dao.BookDAO;
import ru.sergey.libraryproject.dao.PersonDAO;
import ru.sergey.libraryproject.model.Person;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PersonController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("peopleList", personDAO.index());
        return "people/index";
    }

    @GetMapping("{id}")
    public String personPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.readPerson(id));
        model.addAttribute("books", bookDAO.getBookThatPersonTake(id));
        return "/people/person";
    }

    @GetMapping("/{id}/edit")
    public String updatePersonPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person",personDAO.readPerson(id));
        return "people/update";
    }

    @PatchMapping("{id}")
    public String updatePerson(@PathVariable("id") int id, @ModelAttribute("person")Person person) {
        personDAO.updatePerson(id,person);
        return "redirect:/people";
    }

    @DeleteMapping({"{id}"})
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }

    @GetMapping("/new")
    public String createPersonPage(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping
    public String createPerson(@ModelAttribute("person") Person person) {
        personDAO.createPerson(person);
        return "redirect:/people";
    }

}
