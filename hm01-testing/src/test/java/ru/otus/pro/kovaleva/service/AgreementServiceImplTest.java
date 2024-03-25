package ru.otus.pro.kovaleva.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import ru.otus.pro.kovaleva.dao.AgreementDao;
import ru.otus.pro.kovaleva.entity.Agreement;
import ru.otus.pro.kovaleva.service.impl.AgreementServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

public class AgreementServiceImplTest {
    private AgreementDao dao = Mockito.mock(AgreementDao.class);

    AgreementServiceImpl agreementServiceImpl;

    @BeforeEach
    void init() {
        agreementServiceImpl = new AgreementServiceImpl(dao);
    }

    @Test
    void testFindByName() {
        String name = "test";
        Agreement agreement = new Agreement();
        agreement.setId(10L);
        agreement.setName(name);

        when(dao.findByName(name)).thenReturn(
                Optional.of(agreement));

        Optional<Agreement> result = agreementServiceImpl.findByName(name);

        Assertions.assertTrue(result.isPresent());
        assertEquals(10, agreement.getId());
    }

    @Test
    void testFindByNameWithCaptor() {
        String name = "test";
        Agreement agreement = new Agreement();
        agreement.setId(10L);
        agreement.setName(name);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        when(dao.findByName(captor.capture())).thenReturn(
                Optional.of(agreement));

        Optional<Agreement> result = agreementServiceImpl.findByName(name);

        assertEquals("test", captor.getValue());
        Assertions.assertTrue(result.isPresent());
        assertEquals(10, agreement.getId());
    }

    @Test
    void addAgreementTest() {
        String name = "test";
        Agreement agreement = new Agreement();
        agreement.setId(10L);

        ArgumentMatcher<Agreement> matcher = argument -> argument != null && name.equals(argument.getName());

        when(dao.save(argThat(matcher))).thenReturn(agreement);

        Agreement result = agreementServiceImpl.addAgreement("test");
        assertEquals(agreement, result);
        //verify?
    }
}
