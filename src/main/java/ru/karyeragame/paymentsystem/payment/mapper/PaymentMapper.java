package ru.karyeragame.paymentsystem.payment.mapper;

import lombok.experimental.UtilityClass;
import ru.karyeragame.paymentsystem.payment.dto.PaymentDto;
import ru.karyeragame.paymentsystem.payment.model.Payment;

@UtilityClass
public class PaymentMapper {
    public PaymentDto toPaymentDto(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .gameId(payment.getGameId())
                .fromAccount(payment.getFromAccount())
                .toAccount(payment.getToAccount())
                .amount(payment.getAmount())
                .description(payment.getDescription())
                .created(payment.getCreated())
                .build();
    }
}
