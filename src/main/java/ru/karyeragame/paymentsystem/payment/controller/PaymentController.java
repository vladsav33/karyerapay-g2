package ru.karyeragame.paymentsystem.payment.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.karyeragame.paymentsystem.payment.dto.PaymentDto;
import ru.karyeragame.paymentsystem.payment.service.PaymentService;
import ru.karyeragame.paymentsystem.user.dto.UserFullDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String payment(@RequestBody @Valid PaymentDto paymentDto, Authentication authentication) {
        return paymentService.payment(paymentDto, ((UserFullDto) authentication.getPrincipal()).getId());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentDto> getAll() {
        return paymentService.getAll();
    }

    @GetMapping(("/email/{email}"))
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentDto> getAllByEmail(@PathVariable @Email String email) {
        return paymentService.getAllByEmail(email);
    }
}
