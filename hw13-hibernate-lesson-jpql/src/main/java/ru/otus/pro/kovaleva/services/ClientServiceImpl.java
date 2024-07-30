package ru.otus.pro.kovaleva.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import ru.otus.pro.kovaleva.models.Client;
import ru.otus.pro.kovaleva.util.EntityUtil;
import ru.otus.pro.kovaleva.util.EntityUtilHibernate;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

    private final EntityUtil<Client> clientEntityUtil;

    private final SessionFactory sessionFactory;

    public ClientServiceImpl(SessionFactory sessionFactory) {
        this.clientEntityUtil = new EntityUtilHibernate<>();
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Client saveClient(Client client) {
        if (client.getId() == null) {
            clientEntityUtil.insert(sessionFactory, client);
            logger.info("Client " + client.getName() + " insert.");
            System.out.println("Client " + client.getName() + " insert.");
            return client;
        }
        return null;
    }

    @Override
    public Client getClient(long id) {
        return clientEntityUtil.findOneById(sessionFactory, Client.class, id);
    }

    @Override
    public List<Client> findAll() {
        return clientEntityUtil.findAll(sessionFactory, Client.class);
    }
}
