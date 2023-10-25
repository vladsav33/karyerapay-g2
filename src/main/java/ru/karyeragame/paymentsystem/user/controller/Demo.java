package ru.karyeragame.paymentsystem.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.service.CustomUserService;

import javax.validation.constraints.NotBlank;

@Controller
@RequestMapping("/karyerapay")
@AllArgsConstructor
public class Demo {
    private final CustomUserService userDetailsService;

    @GetMapping
    @RequestMapping("/paymentsystem/demo")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> game() {
        return ResponseEntity.ok("Защищён, доступен авторизованным");
    }

    @GetMapping
    @RequestMapping("/admin/findUser/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getUser(@NotBlank @PathVariable String email) {
        return ResponseEntity.ok((User) userDetailsService.loadUserByUsername(email));
    }

}
