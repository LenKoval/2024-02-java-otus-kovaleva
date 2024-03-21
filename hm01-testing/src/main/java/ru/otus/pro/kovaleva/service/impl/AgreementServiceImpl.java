package ru.otus.pro.kovaleva.service.impl;

import ru.otus.pro.kovaleva.dao.AgreementDao;
import ru.otus.pro.kovaleva.entity.Agreement;
import ru.otus.pro.kovaleva.service.AgreementService;

import java.util.Optional;

public class AgreementServiceImpl implements AgreementService {
    private AgreementDao agreementDao;

    public AgreementServiceImpl(AgreementDao agreementDao) {
        this.agreementDao = agreementDao;
    }

    @Override
    public Agreement addAgreement(String name) {
        Agreement agreement = new Agreement();
        agreement.setName(name);

        return agreementDao.save(agreement);
    }
    @Override
    public Optional<Agreement> findByName(String name) {
        return agreementDao.findByName(name);
    }
}
