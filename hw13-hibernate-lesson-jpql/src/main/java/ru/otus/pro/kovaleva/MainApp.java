package ru.otus.pro.kovaleva;

import org.hibernate.SessionFactory;
import ru.otus.pro.kovaleva.configurations.JavaBasedSessionFactory;
import ru.otus.pro.kovaleva.models.Address;
import ru.otus.pro.kovaleva.models.Client;
import ru.otus.pro.kovaleva.models.Phone;
import ru.otus.pro.kovaleva.services.ClientService;
import ru.otus.pro.kovaleva.services.ClientServiceImpl;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = JavaBasedSessionFactory.getSessionFactory()) {
            Client client1 = createClient("Client1", "+1(3848)3843455", "Street01");
            Client client2 = createClient("Client2", "+1(3848)3534534", "Street02");

            ClientService clientService = new ClientServiceImpl(sessionFactory);

            clientService.saveClient(client1);
            clientService.saveClient(client2);
            System.out.println(clientService.findAll());
            System.out.println(clientService.getClient(1));
        }
    }

    private static Client createClient(String name, String phoneNumber, String street) {
        Phone phone = new Phone();
        phone.setNumber(phoneNumber);

        Address address = new Address();
        address.setStreet(street);

        Client client = new Client();
        client.setName(name);
        client.setPhones(List.of(phone));
        client.setAddress(address);

        return client;
    }
}
