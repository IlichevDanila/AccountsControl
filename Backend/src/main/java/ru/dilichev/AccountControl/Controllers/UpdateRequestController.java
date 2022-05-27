package ru.dilichev.AccountControl.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dilichev.AccountControl.DAO.*;
import ru.dilichev.AccountControl.Models.*;

import java.sql.Timestamp;
import java.util.ArrayList;

@Controller
public class UpdateRequestController {
    @Autowired
    ClientDAO clientDAO;

    @Autowired
    PhysicalClientDAO physicalClientDAO;

    @Autowired
    LegalClientDAO legalClientDAO;

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    OfficeDAO officeDAO;

    @Autowired
    AccountTypeDAO accountTypeDAO;

    @Autowired
    TransactionDAO transactionDAO;

    @GetMapping("/OfficeUpdate")
    public ModelAndView UpdateOffices(@RequestParam(value = "id", defaultValue = "null") String id,
                                    @RequestParam(value = "phone", defaultValue = "null") String phone,
                                    @RequestParam(value = "address", defaultValue = "null") String address,
                                    ModelAndView mv)
    {
        if(id.equals("null") || officeDAO.getOfficeByCondition(Long.valueOf(id), null, null).size() == 0)
        {
            if(!phone.equals("null") && !address.equals("null"))
            {
                Office newObj = new Office(Long.valueOf(id), phone, address);
                officeDAO.addOffice(newObj);
                mv.addObject("rows", officeDAO.getOfficeByCondition(newObj.getId(), newObj.getPhone(), newObj.getAddress()));
            }
            else
            {
                mv.addObject("rows", new ArrayList<Office>());
            }
        }
        else
        {
            if(!phone.equals("null") && !address.equals("null"))
            {
                Office newObj = new Office(Long.valueOf(id), phone, address);
                officeDAO.updateOffice(newObj);
                mv.addObject("rows", officeDAO.getOfficeByCondition(newObj.getId(), newObj.getPhone(), newObj.getAddress()));
            }
            else
            {
                mv.addObject("rows", new ArrayList<Office>());
            }
        }
        mv.setViewName("OfficesTable");

        return mv;
    }

    @GetMapping("/ClientsUpdate")
    public ModelAndView UpdateClients(@RequestParam(value = "id", defaultValue = "null") String id,
                                      @RequestParam(value = "tin", defaultValue = "null") String tin,
                                      @RequestParam(value = "phone", defaultValue = "null") String phone,
                                      @RequestParam(value = "address", defaultValue = "null") String address,
                                      @RequestParam(value = "type") String type,
                                      @RequestParam(value = "fullName", defaultValue = "null") String fullName,
                                      @RequestParam(value = "passport", defaultValue = "null") String passport,
                                      @RequestParam(value = "name", defaultValue = "null") String name,
                                      @RequestParam(value = "form", defaultValue = "null") String form,
                                    ModelAndView mv)
    {
        if(tin.equals("null") ||
                (legalClientDAO.getLegalClientByCondition(null, null, null, null, null, tin).size() == 0 &&
                physicalClientDAO.getPhysicalClientByCondition(null, null, null, null, null, tin).size() == 0))
        {
            if(type.equals("Physical") || type.equals("physical"))
            {
                if(!phone.equals("null") && !address.equals("null") && !tin.equals("null") && !fullName.equals("null") && !passport.equals("null"))
                {
                    PhysicalClient newObj = new PhysicalClient(new Client(null, type, phone, address), fullName, passport, tin);
                    physicalClientDAO.addPhysicalClient(newObj);
                    mv.addObject("rows", physicalClientDAO.getPhysicalClientByCondition(null,
                            null, null, null, null, tin));
                }
                mv.setViewName("PhysicalClientsTable");
            }
            else
            {
                if(!phone.equals("null") && !address.equals("null") && !tin.equals("null") && !name.equals("null") && !form.equals("null"))
                {
                    LegalClient newObj = new LegalClient(new Client(null, type, phone, address), name, form, tin);
                    legalClientDAO.addLegalClient(newObj);
                    mv.addObject("rows", legalClientDAO.getLegalClientByCondition(null,
                            null, null, null, null, tin));
                }
                mv.setViewName("LegalClientsTable");
            }
        }
        else
        {
            if(type.equals("Physical") || type.equals("physical"))
            {
                if(!phone.equals("null") && !address.equals("null") && !tin.equals("null") && !fullName.equals("null") && !passport.equals("null"))
                {
                    PhysicalClient newObj = new PhysicalClient(new Client(null, type, phone, address), fullName, passport, tin);
                    PhysicalClient oldObj = physicalClientDAO.getPhysicalClientByCondition(null, null, null, null, null, tin).get(0);
                    newObj.getClient().setId(oldObj.getClient().getId());
                    physicalClientDAO.updatePhysicalClient(newObj);
                    mv.addObject("rows", physicalClientDAO.getPhysicalClientByCondition(newObj.getClient().getId(),
                            null, null, null, null, null));
                }
                mv.setViewName("PhysicalClientsTable");
            }
            else
            {
                if (!phone.equals("null") && !address.equals("null") && !tin.equals("null") && !name.equals("null") && !form.equals("null")) {
                    LegalClient newObj = new LegalClient(new Client(null, type, phone, address), name, form, tin);
                    LegalClient oldObj = legalClientDAO.getLegalClientByCondition(null, null, null, null, null, tin).get(0);
                    newObj.getClient().setId(oldObj.getClient().getId());
                    legalClientDAO.updateLegalClient(newObj);
                    mv.addObject("rows", legalClientDAO.getLegalClientByCondition(newObj.getClient().getId(),
                            null, null, null, null, null));
                }
                mv.setViewName("LegalClientsTable");
            }
        }

        return mv;
    }

    @GetMapping("/TransactionsUpdate")
    public ModelAndView UpdateTransactions(@RequestParam(value = "id", defaultValue = "null") String id,
                                           @RequestParam(value = "debit", defaultValue = "null") String debit,
                                           @RequestParam(value = "credit", defaultValue = "null") String credit,
                                           @RequestParam(value = "date", defaultValue = "null") String date,
                                           @RequestParam(value = "balance", defaultValue = "null") String balance,
                                           ModelAndView mv)
    {
        mv.setViewName("TransactionsTable");
        if(id.equals("null") || transactionDAO.getTransactionByCondition(Long.valueOf(id), null, null,
                null, null, null, null).size() == 0)
        {
            if(!debit.equals("null") && !credit.equals("null") && !date.equals("null") && !balance.equals("null"))
            {
                Transaction newObj = new Transaction(id.equals("null")? Long.MAX_VALUE : Long.valueOf(id),
                        debit, credit, Timestamp.valueOf(date), Double.valueOf(balance));
                transactionDAO.addTransaction(newObj);
                mv.addObject("rows", transactionDAO.getTransactionByCondition(null, debit,
                        credit, Timestamp.valueOf(date), Timestamp.valueOf(date), Double.valueOf(balance), Double.valueOf(balance)));
            }
        }
        else
        {
            mv.addObject("rows", transactionDAO.getTransactionByCondition(Long.valueOf(id), debit,
                    credit, Timestamp.valueOf(date), Timestamp.valueOf(date), Double.valueOf(balance), Double.valueOf(balance)));
        }

        return mv;
    }

    @GetMapping("/AccountTypesUpdate")
    public ModelAndView UpdateAccountTypes(@RequestParam(value = "id", defaultValue = "null") String id,
                                      @RequestParam(value = "name", defaultValue = "null") String name,
                                      @RequestParam(value = "profit", defaultValue = "null") String profit,
                                      @RequestParam(value = "percent", defaultValue = "null") String percent,
                                      @RequestParam(value = "debiting", defaultValue = "null") String debiting,
                                      @RequestParam(value = "accrual", defaultValue = "null") String accrual,
                                      @RequestParam(value = "valid", defaultValue = "null") String valid,
                                      @RequestParam(value = "period", defaultValue = "null") String period,
                                      ModelAndView mv)
    {
        if(id.equals("null") || accountTypeDAO.getAccountTypeByCondition(Long.valueOf(id), null,
                null, null, null, null,
                null, null, null, null).size() == 0)
        {
            if(!name.equals("null") && !profit.equals("null") && !period.equals("null"))
            {
                AccountType newObj = new AccountType(Long.valueOf(id), name, percent.equals("null")? null : Double.valueOf(profit),
                        percent.equals("null")? Double.valueOf(profit) : null, !debiting.equals("null"), !accrual.equals("null"),
                        period, !valid.equals("null"));
                accountTypeDAO.addAccountType(newObj);
                mv.addObject("rows", accountTypeDAO.getAccountTypeByCondition(newObj.getId(), null,
                        null, null, null, null,
                        null, null, null, null));
            }
            else
            {
                mv.addObject("rows", new ArrayList<Office>());
            }
        }
        else
        {
            if(!name.equals("null") && !profit.equals("null") && !period.equals("null"))
            {
                AccountType newObj = new AccountType(Long.valueOf(id), name, percent.equals("null")? null : Double.valueOf(profit),
                        percent.equals("null")? Double.valueOf(profit) : null, !debiting.equals("null"), !accrual.equals("null"),
                        period, !valid.equals("null"));
                accountTypeDAO.updateAccountType(newObj);
                mv.addObject("rows", accountTypeDAO.getAccountTypeByCondition(newObj.getId(), null,
                        null, null, null, null,
                        null, null, null, null));
            }
            else
            {
                mv.addObject("rows", new ArrayList<Office>());
            }
        }

        mv.setViewName("AccountTypesTable");

        return mv;
    }

    @GetMapping("/AccountsUpdate")
    public ModelAndView UpdateAccounts(@RequestParam(value = "id", defaultValue = "null") String id,
                                       @RequestParam(value = " status", defaultValue = "null") String status,
                                       @RequestParam(value = "date", defaultValue = "null") String date,
                                       @RequestParam(value = "type", defaultValue = "null") String type,
                                       @RequestParam(value = "response", defaultValue = "null") String response,
                                       @RequestParam(value = "loan", defaultValue = "null") String loan,
                                       @RequestParam(value = "client", defaultValue = "null") String client,
                                       @RequestParam(value = "balance", defaultValue = "null") String balance,
                                       ModelAndView mv)
    {
        if(id.equals("null") || accountDAO.getAccountByCondition(id, null, null, null,
                null, null, null, null,
                null, null).size() == 0)
        {
            if(!status.equals("null") && !date.equals("null") && !type.equals("null") && !client.equals("null"))
            {
                Account newObj = new Account(id,
                        clientDAO.getClientByCondition(Long.valueOf(client), null, null, null).get(0),
                        accountTypeDAO.getAccountTypeByCondition(Long.valueOf(type), null, null,
                                null, null, null, null,
                                null, null, null).get(0), status, Double.valueOf(balance),
                        Timestamp.valueOf(date), response.equals("null")? null : response,
                        loan.equals("null")? null : loan);
                accountDAO.addAccount(newObj);
                mv.addObject("rows", accountDAO.getAccountByCondition(newObj.getId(), null,
                        null, null, null, null,
                        null, null, null, null));
            }
            else
            {
                mv.addObject("rows", new ArrayList<Office>());
            }
        }
        else
        {
            if(!status.equals("null") && !date.equals("null") && !client.equals("null"))
            {
                Account oldObj = accountDAO.getAccountByCondition(id, null, null,
                        null, null, null, null,
                        null, null,null).get(0);
                type = Long.toString(oldObj.getType().getId());
                Account newObj = new Account(id,
                        clientDAO.getClientByCondition(Long.valueOf(client), null, null, null).get(0),
                        oldObj.getType(), status, Double.valueOf(balance),
                        Timestamp.valueOf(date), response.equals("null")? oldObj.getResponse_account() : response,
                        loan.equals("null")? oldObj.getLoan_account() : loan);
                accountDAO.updateAccount(newObj);
                mv.addObject("rows", accountDAO.getAccountByCondition(newObj.getId(), null,
                        null, null, null, null,
                        null, null, null, null));
            }
            else
            {
                mv.addObject("rows", new ArrayList<Office>());
            }
        }

        mv.setViewName("AccountsTable");

        return mv;
    }
}
