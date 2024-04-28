package ru.otus.pro.kovaleva.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.pro.kovaleva.dao.AccountDao;
import ru.otus.pro.kovaleva.entity.Account;
import ru.otus.pro.kovaleva.entity.Agreement;
import ru.otus.pro.kovaleva.service.exception.AccountException;
import ru.otus.pro.kovaleva.service.impl.AccountServiceImpl;
import ru.otus.pro.kovaleva.service.impl.PaymentProcessorImpl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentProcessorImplWithSpyTest {
    @Mock
    AccountDao accountDao;

    @Spy
    @InjectMocks
    AccountServiceImpl accountService;

    @InjectMocks
    PaymentProcessorImpl paymentProcessor;

    @BeforeEach
    void init() {
        paymentProcessor = new PaymentProcessorImpl(accountService);
    }

    @Test
    public void testTransfer() {
        Agreement sourceAgreement = new Agreement();
        sourceAgreement.setId(1L);

        Agreement destinationAgreement = new Agreement();
        destinationAgreement.setId(2L);

        Account sourceAccount = new Account();
        sourceAccount.setAmount(BigDecimal.TEN);
        sourceAccount.setType(0);
        sourceAccount.setId(10L);

        Account destinationAccount = new Account();
        destinationAccount.setAmount(BigDecimal.ZERO);
        destinationAccount.setType(0);
        destinationAccount.setId(20L);

        doReturn(List.of(sourceAccount)).when(accountService).getAccounts(argThat(argument -> argument != null && argument.getId() == 1L));

        doReturn(List.of(destinationAccount)).when(accountService).getAccounts(argThat(argument -> argument != null && argument.getId() == 2L));

        when(accountDao.findById(10L)).thenReturn(Optional.of(sourceAccount));
        when(accountDao.findById(20L)).thenReturn(Optional.of(destinationAccount));
//        when(accountDao.findById(30L)).thenReturn(Optional.of(destinationAccount));

        paymentProcessor.makeTransfer(sourceAgreement, destinationAgreement,
                0, 0, BigDecimal.ONE);

        assertEquals(new BigDecimal(9), sourceAccount.getAmount());
        assertEquals(BigDecimal.ONE, destinationAccount.getAmount());
    }

    @Test
    public void testMakeTransferWithComission() {
        Agreement sourceAgreement = new Agreement();
        sourceAgreement.setId(1L);

        Agreement destinationAgreement = new Agreement();
        destinationAgreement.setId(2L);

        Account sourceAccount = new Account();
        sourceAccount.setAmount(BigDecimal.TEN);
        sourceAccount.setType(0);
        sourceAccount.setId(10L);

        Account destinationAccount = new Account();
        destinationAccount.setAmount(BigDecimal.ZERO);
        destinationAccount.setType(0);
        destinationAccount.setId(20L);

        doReturn(List.of(sourceAccount)).when(accountService).getAccounts(argThat(argument -> argument != null && argument.getId() == 1L));

        doReturn(List.of(destinationAccount)).when(accountService).getAccounts(argThat(argument -> argument != null && argument.getId() == 2L));

        when(accountDao.findById(10L)).thenReturn(Optional.of(sourceAccount));
        when(accountDao.findById(20L)).thenReturn(Optional.of(destinationAccount));

        assertEquals(true, paymentProcessor.makeTransferWithComission(sourceAgreement, destinationAgreement,
                0, 0, new BigDecimal(100), BigDecimal.TWO));
        assertEquals(new BigDecimal(110), sourceAccount.getAmount());
        assertEquals(new BigDecimal(100), destinationAccount.getAmount());
        verify(accountService, times(2)).getAccounts(any());
        verify(accountService).charge(any(), any());
    }

    @Test
    public void testMakeTransferWithComissionException() {
        Agreement agreement = new Agreement();
        agreement.setId(20L);
        BigDecimal commission = new BigDecimal(0.1);

        when(accountService.getAccounts(agreement)).thenReturn(Collections.emptyList());
        assertThrows(AccountException.class, () -> paymentProcessor.makeTransferWithComission(agreement, new Agreement(),
                0, 0, BigDecimal.ONE, commission));
    }
}
