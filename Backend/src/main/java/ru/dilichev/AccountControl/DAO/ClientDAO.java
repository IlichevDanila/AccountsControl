package ru.dilichev.AccountControl.DAO;

import ru.dilichev.AccountControl.Models.Client;

import java.util.List;

public interface ClientDAO {
    void deleteClient(Client sh);

    void updateClient(Client sh);

    String SQLByCondition(Long id, String type, String phone, String address);

    List<Client> getClientByCondition(Long id, String type, String phone, String address);
}
