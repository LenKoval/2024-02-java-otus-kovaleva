package ru.otus.pro.kovaleva.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.pro.kovaleva.dao.AccountDao;
import ru.otus.pro.kovaleva.entity.Account;
import ru.otus.pro.kovaleva.entity.Agreement;
import ru.otus.pro.kovaleva.service.exception.AccountException;
import ru.otus.pro.kovaleva.service.impl.AccountServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
    @Mock
    AccountDao accountDao;

    @InjectMocks
    AccountServiceImpl accountServiceImpl;

    @Test
    void testTransfer() {
        Account sourceAccount = new Account();
        sourceAccount.setAmount(new BigDecimal(100));

        Account destinationAccount = new Account();
        destinationAccount.setAmount(new BigDecimal(10));

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(sourceAccount));
        when(accountDao.findById(eq(2L))).thenReturn(Optional.of(destinationAccount));

        accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10));

        assertEquals(new BigDecimal(90), sourceAccount.getAmount());
        assertEquals(new BigDecimal(20), destinationAccount.getAmount());
    }

    @Test
    void testSourceNotFound() {
        when(accountDao.findById(any())).thenReturn(Optional.empty());

        AccountException result =
                assertThrows(AccountException.class, () -> accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10)));
        assertEquals("No source account", result.getLocalizedMessage());
    }


    @Test
    void testTransferWithVerify() {
        Account sourceAccount = new Account();
        sourceAccount.setAmount(new BigDecimal(100));
        sourceAccount.setId(1L);

        Account destinationAccount = new Account();
        destinationAccount.setAmount(new BigDecimal(10));
        destinationAccount.setId(2L);

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(sourceAccount));
        when(accountDao.findById(eq(2L))).thenReturn(Optional.of(destinationAccount));

        ArgumentMatcher<Account> sourceMatcher =
                argument -> argument.getId().equals(1L) && argument.getAmount().equals(new BigDecimal(90));

        ArgumentMatcher<Account> destinationMatcher =
                argument -> argument.getId().equals(2L) && argument.getAmount().equals(new BigDecimal(20));

        accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10));

        verify(accountDao).save(argThat(sourceMatcher));
        verify(accountDao).save(argThat(destinationMatcher));
    }

    @Test
    void testAddAccount() {
        Agreement agreement = new Agreement();
        agreement.setId(10L);
        String accNumber = "number";
        Integer type = 20;
        BigDecimal amount = new BigDecimal(10);

        Account account = new Account();
        account.setAgreementId(agreement.getId());
        account.setNumber(accNumber);
        account.setType(type);
        account.setAmount(amount);

        ArgumentMatcher<Account> matcher = argument ->
                argument != null && accNumber.equals(account.getNumber())
                        && type.equals(account.getType())
                        && amount.equals(account.getAmount());

        when(accountDao.save(argThat(matcher))).thenReturn(account);
        accountServiceImpl.addAccount(agreement, accNumber, type, amount);
        verify(accountDao).save(argThat(matcher));
    }

    @Test
    void testGetAllAccounts() {
        Account account1 = new Account();
        account1.setId(10L);
        account1.setAmount(new BigDecimal(20));

        Account account2 = new Account();
        account2.setId(20L);
        account2.setAmount(new BigDecimal(50));

        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);
        when(accountDao.findAll()).thenReturn(accountList);

        List<Account> allAccounts = accountServiceImpl.getAccounts();
        assertArrayEquals(accountList.toArray(), allAccounts.toArray());
        verify(accountDao).findAll();
    }

    @Test
    void testCharge() {
        Account account = new Account();
        account.setId(10L);
        BigDecimal amount = new BigDecimal(200);
        account.setAmount(amount);
        BigDecimal chargeAmount = new BigDecimal(20);

        when(accountDao.findById(eq(10L))).thenReturn(Optional.of(account));
        accountServiceImpl.charge(10L, chargeAmount);

        assertEquals(amount.subtract(chargeAmount), account.getAmount());
        verify(accountDao).findById(10L);
        verify(accountDao).save(account);

        //assertThrows(AccountException.class, () -> accountServiceImpl.charge(20L, chargeAmount));
    }
}
