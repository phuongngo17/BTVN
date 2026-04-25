package org.example.bai1.controller;

import jakarta.validation.Valid;
import org.example.bai1.model.Todo;
import org.example.bai1.repository.impl.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("todos", todoRepository.findAll());
        return "home";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "form-add";
    }

    @PostMapping("/add")
    public String addTodo(@Valid @ModelAttribute("todo") Todo todo,
                          BindingResult result) {

        if (result.hasErrors()) {
            return "form-add";
        }

        todoRepository.save(todo);
        return "redirect:/";
    }
}