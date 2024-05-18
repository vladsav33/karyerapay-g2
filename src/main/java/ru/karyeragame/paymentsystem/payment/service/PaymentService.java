package ru.karyeragame.paymentsystem.payment.service;

import ru.karyeragame.paymentsystem.payment.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    String payment(PaymentDto paymentDto, Long userFromId);

    List<PaymentDto> getAll();

    List<PaymentDto> getAllByEmail(String email);
}
