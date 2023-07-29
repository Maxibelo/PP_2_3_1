package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAOImp;
import web.models.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userservice;

    @Autowired
    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userservice.index());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userservice.show(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userservice.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userservice.show(id));
        return "users/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("person") User user, @PathVariable("id") int id) {
        userservice.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userservice.delete(id);
        return "redirect:/users";
    }
}
