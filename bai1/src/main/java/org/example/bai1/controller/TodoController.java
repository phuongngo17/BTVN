package org.example.bai1.controller;

import jakarta.validation.Valid;
import org.example.bai1.model.Todo;
import org.example.bai1.repository.impl.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String saveTodo(@Valid @ModelAttribute("todo") Todo todo,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "form-add";
        }

        todoRepository.save(todo);

        if (todo.getId() != null) {
            redirectAttributes.addFlashAttribute("message", "Cập nhật thành công");
        } else {
            redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
        }

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id không hợp lệ: " + id));

        model.addAttribute("todo", todo);
        return "form-add";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id,
                             RedirectAttributes redirectAttributes) {

        if (!todoRepository.existsById(id)) {
            redirectAttributes.addFlashAttribute("message", "Task không tồn tại");
            return "redirect:/";
        }

        todoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Xóa thành công");
        return "redirect:/";
    }
}