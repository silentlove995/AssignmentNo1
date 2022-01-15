package com.microservice.payment.service.impl;

import com.microservice.payment.entity.WalletEntity;
import com.microservice.payment.model.ResponseData;
import com.microservice.payment.model.WalletModel;
import com.microservice.payment.model.dto.WalletDto;
import com.microservice.payment.repository.WalletRepository;
import com.microservice.payment.service.WalletService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public ResponseData findById(Long id) {
        Optional<WalletEntity> walletEntity = walletRepository.findById(id);
        if (!walletEntity.isPresent()){
            return new ResponseData(404, "Wallet ID: " + id + " not found", "");
        }
        return new ResponseData(200, "Success", WalletDto.walletDto(walletEntity.get()));
    }

    @Override
    public ResponseData findAll() {
        List<WalletEntity> walletEntities = walletRepository.findAll();
        List<WalletDto> dtos = new ArrayList<>();
        for(WalletEntity item : walletEntities) {
            dtos.add(WalletDto.walletDto(item));
        }
        return new ResponseData(200, "Success", dtos);
    }

    @Override
    public ResponseData save(WalletModel walletModel) {
        ModelMapper modelMapper = new ModelMapper();
        WalletEntity wallet = modelMapper.map(walletModel, WalletEntity.class);
        wallet = walletRepository.save(wallet);
        WalletDto dto = WalletDto.walletDto(walletRepository.save(wallet));
        return new ResponseData(200, "Add Wallet successfully", dto);
    }

    @Override
    public ResponseData update(WalletModel walletModel, Long id) {
        if (!walletRepository.findById(id).isPresent()){
            return new ResponseData(404, "Wallet ID: " + id + " not found", "");
        }
        Optional<WalletEntity> walletEntity = walletRepository.findById(id)
                .map(item -> {
                    ModelMapper modelMapper = new ModelMapper();
                    Long walletId = item.getId();
                    item = modelMapper.map(walletModel, WalletEntity.class);
                    item.setId(walletId);
                    return item;
                });
        walletEntity.ifPresent(value -> walletRepository.save(value));
        return new ResponseData(200, "Update Wallet successfully", walletEntity);
    }

    @Override
    public ResponseData delete(Long id) {
        if (!walletRepository.findById(id).isPresent()){
            return new ResponseData(404, "Wallet ID: " + id + " not found", "");
        }
        walletRepository.deleteById(id);
        return new ResponseData(200, "Delete Wallet successfully", "");
    }
}
