package com.microservice.payment.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.microservice.payment.entity.WalletEntity;
import com.microservice.payment.model.ResponseData;
import com.microservice.payment.model.WalletModel;
import com.microservice.payment.model.dto.WalletDto;
import com.microservice.payment.repository.WalletRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {WalletServiceImpl.class})
@ExtendWith(SpringExtension.class)
class WalletServiceImplTest {
    @MockBean
    private WalletRepository walletRepository;

    @Autowired
    private WalletServiceImpl walletServiceImpl;

    @Test
    void testFindById() {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(10.0);
        walletEntity.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        walletEntity.setCustomerId(123L);
        walletEntity.setDeleted(true);
        walletEntity.setId(123L);
        walletEntity.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        Optional<WalletEntity> ofResult = Optional.of(walletEntity);
        when(this.walletRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseData actualFindByIdResult = this.walletServiceImpl.findById(123L);
        assertEquals(200, actualFindByIdResult.getCode().intValue());
        assertEquals("ResponseData(code=200, message=Success, data=WalletDto(customerId=123, balance=10.0))",
                actualFindByIdResult.toString());
        assertEquals("Success", actualFindByIdResult.getMessage());
        assertEquals(10.0, ((WalletDto) actualFindByIdResult.getData()).getBalance().doubleValue());
        assertEquals(123L, ((WalletDto) actualFindByIdResult.getData()).getCustomerId().longValue());
        verify(this.walletRepository).findById((Long) any());
    }

    @Test
    void testFindById2() {
        when(this.walletRepository.findById((Long) any())).thenReturn(Optional.empty());
        ResponseData actualFindByIdResult = this.walletServiceImpl.findById(123L);
        assertEquals(404, actualFindByIdResult.getCode().intValue());
        assertEquals("Wallet ID: 123 not found", actualFindByIdResult.getMessage());
        assertEquals("", actualFindByIdResult.getData());
        verify(this.walletRepository).findById((Long) any());
    }

    @Test
    void testFindAll() {
        when(this.walletRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseData actualFindAllResult = this.walletServiceImpl.findAll();
        assertEquals(200, actualFindAllResult.getCode().intValue());
        assertEquals("Success", actualFindAllResult.getMessage());
        assertTrue(((Collection<Object>) actualFindAllResult.getData()).isEmpty());
        verify(this.walletRepository).findAll();
    }

    @Test
    void testFindAll2() {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(10.0);
        walletEntity.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        walletEntity.setCustomerId(123L);
        walletEntity.setDeleted(true);
        walletEntity.setId(123L);
        walletEntity.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");

        ArrayList<WalletEntity> walletEntityList = new ArrayList<>();
        walletEntityList.add(walletEntity);
        walletEntityList.addAll(new ArrayList<>());
        when(this.walletRepository.findAll()).thenReturn(walletEntityList);
        ResponseData actualFindAllResult = this.walletServiceImpl.findAll();
        assertEquals(200, actualFindAllResult.getCode().intValue());
        assertEquals("Success", actualFindAllResult.getMessage());
        Object data = actualFindAllResult.getData();
        assertEquals(1, ((Collection<WalletDto>) data).size());
        WalletDto getResult = ((List<WalletDto>) data).get(0);
        assertEquals(10.0, getResult.getBalance().doubleValue());
        assertEquals(123L, getResult.getCustomerId().longValue());
        verify(this.walletRepository).findAll();
    }

    @Test
    void testSave() {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(10.0);
        walletEntity.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        walletEntity.setCustomerId(123L);
        walletEntity.setDeleted(true);
        walletEntity.setId(123L);
        walletEntity.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        when(this.walletRepository.save((WalletEntity) any())).thenReturn(walletEntity);
        ResponseData actualSaveResult = this.walletServiceImpl.save(new WalletModel());
        assertEquals(200, actualSaveResult.getCode().intValue());
        assertEquals(
                "ResponseData(code=200, message=Add Wallet successfully, data=WalletDto(customerId=123," + " balance=10.0))",
                actualSaveResult.toString());
        assertEquals("Add Wallet successfully", actualSaveResult.getMessage());
        assertEquals(10.0, ((WalletDto) actualSaveResult.getData()).getBalance().doubleValue());
        assertEquals(123L, ((WalletDto) actualSaveResult.getData()).getCustomerId().longValue());
        verify(this.walletRepository, atLeast(1)).save((WalletEntity) any());
    }

    @Test
    void testSave2() {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(10.0);
        walletEntity.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setCreatedBy("Add Wallet successfully");
        walletEntity.setCustomerId(123L);
        walletEntity.setDeleted(true);
        walletEntity.setId(123L);
        walletEntity.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        when(this.walletRepository.save((WalletEntity) any())).thenReturn(walletEntity);
        ResponseData actualSaveResult = this.walletServiceImpl.save(new WalletModel());
        assertEquals(200, actualSaveResult.getCode().intValue());
        assertEquals(
                "ResponseData(code=200, message=Add Wallet successfully, data=WalletDto(customerId=123," + " balance=10.0))",
                actualSaveResult.toString());
        assertEquals("Add Wallet successfully", actualSaveResult.getMessage());
        assertEquals(10.0, ((WalletDto) actualSaveResult.getData()).getBalance().doubleValue());
        assertEquals(123L, ((WalletDto) actualSaveResult.getData()).getCustomerId().longValue());
        verify(this.walletRepository, atLeast(1)).save((WalletEntity) any());
    }

    @Test
    void testSave3() {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(10.0);
        walletEntity.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        walletEntity.setCustomerId(123L);
        walletEntity.setDeleted(true);
        walletEntity.setId(123L);
        walletEntity.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        when(this.walletRepository.save((WalletEntity) any())).thenReturn(walletEntity);
        ResponseData actualSaveResult = this.walletServiceImpl.save(new WalletModel(123L, 10.0));
        assertEquals(200, actualSaveResult.getCode().intValue());
        assertEquals(
                "ResponseData(code=200, message=Add Wallet successfully, data=WalletDto(customerId=123," + " balance=10.0))",
                actualSaveResult.toString());
        assertEquals("Add Wallet successfully", actualSaveResult.getMessage());
        assertEquals(10.0, ((WalletDto) actualSaveResult.getData()).getBalance().doubleValue());
        assertEquals(123L, ((WalletDto) actualSaveResult.getData()).getCustomerId().longValue());
        verify(this.walletRepository, atLeast(1)).save((WalletEntity) any());
    }

    @Test
    void testSave4() {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(10.0);
        walletEntity.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        walletEntity.setCustomerId(123L);
        walletEntity.setDeleted(true);
        walletEntity.setId(123L);
        walletEntity.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        when(this.walletRepository.save((WalletEntity) any())).thenReturn(walletEntity);
        WalletModel walletModel = mock(WalletModel.class);
        when(walletModel.getCustomerId()).thenReturn(123L);
        when(walletModel.getBalance()).thenReturn(10.0);
        ResponseData actualSaveResult = this.walletServiceImpl.save(walletModel);
        assertEquals(200, actualSaveResult.getCode().intValue());
        assertEquals(
                "ResponseData(code=200, message=Add Wallet successfully, data=WalletDto(customerId=123," + " balance=10.0))",
                actualSaveResult.toString());
        assertEquals("Add Wallet successfully", actualSaveResult.getMessage());
        assertEquals(10.0, ((WalletDto) actualSaveResult.getData()).getBalance().doubleValue());
        assertEquals(123L, ((WalletDto) actualSaveResult.getData()).getCustomerId().longValue());
        verify(this.walletRepository, atLeast(1)).save((WalletEntity) any());
        verify(walletModel).getBalance();
        verify(walletModel, atLeast(1)).getCustomerId();
    }

    @Test
    void testUpdate() {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(10.0);
        walletEntity.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        walletEntity.setCustomerId(123L);
        walletEntity.setDeleted(true);
        walletEntity.setId(123L);
        walletEntity.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        when(this.walletRepository.save((WalletEntity) any())).thenReturn(walletEntity);
        when(this.walletRepository.findById((Long) any())).thenReturn(Optional.empty());
        ResponseData actualUpdateResult = this.walletServiceImpl.update(new WalletModel(), 123L);
        assertEquals(404, actualUpdateResult.getCode().intValue());
        assertEquals("Wallet ID: 123 not found", actualUpdateResult.getMessage());
        assertEquals("", actualUpdateResult.getData());
        verify(this.walletRepository).findById((Long) any());
    }

    @Test
    void testDelete() {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(10.0);
        walletEntity.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        walletEntity.setCustomerId(123L);
        walletEntity.setDeleted(true);
        walletEntity.setId(123L);
        walletEntity.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        walletEntity.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        Optional<WalletEntity> ofResult = Optional.of(walletEntity);
        doNothing().when(this.walletRepository).deleteById((Long) any());
        when(this.walletRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseData actualDeleteResult = this.walletServiceImpl.delete(123L);
        assertEquals(200, actualDeleteResult.getCode().intValue());
        assertEquals("Delete Wallet successfully", actualDeleteResult.getMessage());
        assertEquals("", actualDeleteResult.getData());
        verify(this.walletRepository).deleteById((Long) any());
        verify(this.walletRepository).findById((Long) any());
    }

    @Test
    void testDelete2() {
        doNothing().when(this.walletRepository).deleteById((Long) any());
        when(this.walletRepository.findById((Long) any())).thenReturn(Optional.empty());
        ResponseData actualDeleteResult = this.walletServiceImpl.delete(123L);
        assertEquals(404, actualDeleteResult.getCode().intValue());
        assertEquals("Wallet ID: 123 not found", actualDeleteResult.getMessage());
        assertEquals("", actualDeleteResult.getData());
        verify(this.walletRepository).findById((Long) any());
    }
}

