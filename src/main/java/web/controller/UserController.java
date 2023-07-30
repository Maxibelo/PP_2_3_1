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
    public String getUsersList(Model model) {
        model.addAttribute("users", userservice.getAllUsers());
        return "users/usersList";
    }


    @GetMapping("/new")
    public String getNewUserForm(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userservice.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String getEditUserForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userservice.getById(id));
        return "users/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("person") User user, @PathVariable("id") int id) {
        userservice.update(id, user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userservice.delete(id);
        return "redirect:/users";
    }
}
