package com.microservice.payment.service;

import com.microservice.payment.entity.WalletEntity;
import com.microservice.payment.model.ResponseData;
import com.microservice.payment.model.WalletModel;

public interface WalletService {
    ResponseData findById(Long id);
    ResponseData findAll();
    ResponseData save(WalletModel walletModel);
    ResponseData update(WalletModel walletModel, Long id);
    ResponseData delete(Long id);
}
