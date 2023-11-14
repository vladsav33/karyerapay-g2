package ru.karyeragame.paymentsystem.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.service.UserService;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Controller
@RequestMapping("/karyerapay")
@AllArgsConstructor
public class Demo {
    private final UserService userService;

    @GetMapping
    @RequestMapping("/paymentsystem/demo")
    public ResponseEntity<Map<String, String>> demo(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("Защищёный эндпоинт", "Привет, %s"
                        .formatted(userDetails.getUsername())));
    }

    @GetMapping
    @RequestMapping("/admin/findUser/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> getUser(@NotBlank @PathVariable String email) {
        return ResponseEntity.ok((User) userService.loadUserByUsername(email));
    }

}
