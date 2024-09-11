package br.com.herz.desafio_luizalabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.herz.desafio_luizalabs.entity.User;
import br.com.herz.desafio_luizalabs.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@RequestParam String username, @RequestParam String password,
            RedirectAttributes redirectAttributes) {
        try {
            var userFromDb = userService.findByUsername(username);
            if (userFromDb.isPresent()) {
                redirectAttributes.addFlashAttribute("error", "O usuário informado já existe.");
                return "redirect:/register";
            }

            var passwordEncoder = new BCryptPasswordEncoder();
            var user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));

            userService.save(user);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao cadastrar usuário: " + e.getMessage());
            return "redirect:/register";
        }

        return "redirect:/login";
    }

}
