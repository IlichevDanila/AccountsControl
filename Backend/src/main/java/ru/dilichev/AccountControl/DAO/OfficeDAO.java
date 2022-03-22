package ru.dilichev.AccountControl.DAO;

import ru.dilichev.AccountControl.Models.Office;

import java.util.List;

public interface OfficeDAO {
    void addOffice(Office of);

    void deleteOffice(Office of);

    void updateOffice(Office of);

    List<Office> getOfficeByCondition(Long id, String phone, String address);
}
