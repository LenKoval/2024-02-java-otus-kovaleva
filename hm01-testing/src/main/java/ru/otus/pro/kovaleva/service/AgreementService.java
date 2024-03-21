package ru.otus.pro.kovaleva.service;

import ru.otus.pro.kovaleva.entity.Agreement;

import java.util.Optional;

public interface AgreementService {
    Agreement addAgreement(String name);

    Optional<Agreement> findByName(String name);
}
