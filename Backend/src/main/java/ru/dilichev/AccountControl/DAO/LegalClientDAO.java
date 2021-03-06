package ru.dilichev.AccountControl.DAO;

import ru.dilichev.AccountControl.Models.LegalClient;

import java.util.List;

public interface LegalClientDAO {
    void addLegalClient(LegalClient lh);

    void deleteLegalClient(LegalClient lh);

    void updateLegalClient(LegalClient lh);

    String SQLByCondition(Long id, String phone, String address, String name, String form, String tin);

    List<LegalClient> getLegalClientByCondition(Long id, String phone, String address, String name, String form, String tin);
}
