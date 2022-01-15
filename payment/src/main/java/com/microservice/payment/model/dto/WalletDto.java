package com.microservice.payment.model.dto;

import com.microservice.payment.entity.WalletEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto {
    private Long customerId;
    private Double balance;

    public static WalletDto walletDto(WalletEntity walletEntity){
        WalletDto tmp = new WalletDto();
        tmp.setCustomerId(walletEntity.getCustomerId());
        tmp.setBalance(walletEntity.getBalance());
        return tmp;
    }
}
