package ru.sergey.libraryproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sergey.libraryproject.dao.PersonDAO;
import ru.sergey.libraryproject.model.Person;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("peopleList", personDAO.index());
        return "people/index";
    }

    @GetMapping("{id}")
    public String personPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.readPerson(id));
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

}
