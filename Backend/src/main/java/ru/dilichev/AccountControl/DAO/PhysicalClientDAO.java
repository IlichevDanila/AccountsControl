package ru.dilichev.AccountControl.DAO;

import ru.dilichev.AccountControl.Models.Client;
import ru.dilichev.AccountControl.Models.PhysicalClient;

import java.util.List;

public interface PhysicalClientDAO {
    void addPhysicalClient(PhysicalClient ph);

    void deletePhysicalClient(PhysicalClient ph);

    void updatePhysicalClient(PhysicalClient ph);

    List<PhysicalClient> getPhysicalClientByCondition(Long id, String phone, String address, String fullname, String passport, String tin);
}
