package ru.otus.pro.kovaleva.services;

import ru.otus.pro.kovaleva.models.Client;

import java.util.List;

public interface ClientService {

    Client saveClient(Client client);


    Client getClient(long id);


    List<Client> findAll();
}
