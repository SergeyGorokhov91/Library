package ru.sergey.libraryproject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String updatePerson(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            //TODO узнать как решать этот момент не костылями.
            //Если отправить форму с ошибкой, то тебя удачно переправит на /people/*id* и покажет ошибки,
            // это прописано во вьюхе views/people/update.
            //Но если из этого же окна отправиль офрму снова ошибившись, то откроется страница изменения
            // по адресу /people/0, которая ошибки покажет, но потеряет id.
            //Решил проблему тем, что обнавляю каждый раз id объекта в модели, но это как-то не ок
            if(person.getPerson_id() == 0) {
                person.setPerson_id(id);
            }
            return "people/update";
        }
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
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "people/new";
        }
        personDAO.createPerson(person);
        return "redirect:/people";
    }

}
