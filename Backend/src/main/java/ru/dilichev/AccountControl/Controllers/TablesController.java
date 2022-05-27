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
import java.util.List;

@Controller
public class TablesController {
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

    @GetMapping("/ClientsSearch")
    public ModelAndView getClients(
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
        tin = tin.equals("null")? null : tin;
        phone = phone.equals("null")? null : phone;
        address = address.equals("null")? null : address;
        fullName = fullName.equals("null")? null : fullName;
        passport = passport.equals("null")? null : passport;
        name = name.equals("null")? null : name;
        form = form.equals("null")? null : form;

        if(type.equals("Physical"))
        {
            List<PhysicalClient> res = physicalClientDAO.getPhysicalClientByCondition(null, phone, address, fullName, passport, tin);
            mv.addObject("rows", res);
            mv.setViewName("PhysicalClientsTable");
        }
        else if(type.equals("Legal"))
        {
            List<LegalClient> res = legalClientDAO.getLegalClientByCondition(null, phone, address, name, form, tin);
            mv.addObject("rows", res);
            mv.setViewName("LegalClientsTable");
        }
        return mv;
    }

    @GetMapping("/AccountsSearch")
    public ModelAndView getAccounts(
            @RequestParam(value = "accId", defaultValue = "null", required = false) String accID,
            @RequestParam(value = " status", defaultValue = "null", required = false) String status,
            @RequestParam(value = "date_low", defaultValue = "null", required = false) String date_low,
            @RequestParam(value = "date_high", defaultValue = "null", required = false) String date_high,
            @RequestParam(value = "type", defaultValue = "null", required = false) String type,
            @RequestParam(value = "response", defaultValue = "null", required = false) String response,
            @RequestParam(value = "loan", defaultValue = "null", required = false) String loan,
            @RequestParam(value = "client", defaultValue = "null", required = false) String client,
            @RequestParam(value = "balance_low", defaultValue = "null", required = false) String balance_low,
            @RequestParam(value = "balance_high", defaultValue = "null", required = false) String balance_high,
            ModelAndView mv)
    {
        mv.setViewName("AccountsTable");

        accID = accID.equals("null")? null : accID;
        status = status.equals("null")? null : status;
        date_low = date_low.equals("null")? null : date_low;
        date_high = date_high.equals("null")? null : date_high;
        type = type.equals("null")? null : type;
        response = response.equals("null")? null : response;
        loan = loan.equals("null")? null : loan;
        client = client.equals("null")? null : client;
        balance_low = balance_low.equals("null")? null : balance_low;
        balance_high = balance_high.equals("null")? null : balance_high;

        List<Account> res = accountDAO.getAccountByCondition(accID, status, type, client == null? null : Long.valueOf(client),
                date_low == null? null : Timestamp.valueOf(date_low),
                date_high == null? null : Timestamp.valueOf(date_high),
                response, loan,
                balance_low == null? null : Double.valueOf(balance_low),
                balance_low == null? null : Double.valueOf(balance_high));

        String sql = accountDAO.SQLByCondition(accID, status, type, client == null? null : Long.valueOf(client),
                date_low == null? null : Timestamp.valueOf(date_low),
                date_high == null? null : Timestamp.valueOf(date_high),
                response, loan,
                balance_low == null? null : Double.valueOf(balance_low),
                balance_low == null? null : Double.valueOf(balance_high));

        mv.addObject("rows", res);

        return mv;
    }

    @GetMapping("/OfficesSearch")
    public ModelAndView getOffices(
            @RequestParam(value = "id", defaultValue = "null") String id,
            @RequestParam(value = "phone", defaultValue = "null") String phone,
            @RequestParam(value = "address", defaultValue = "null") String address,
            ModelAndView mv)
    {
        mv.setViewName("OfficesTable");

        id = id.equals("null")? null : id;
        phone = phone.equals("null")? null : phone;
        address = address.equals("null")? null : address;

        List<Office> res = officeDAO.getOfficeByCondition(id == null? null : Long.valueOf(id), phone, address);

        mv.addObject("rows", res);

        return mv;
    }

    @GetMapping("/AccountTypesSearch")
    public ModelAndView getAccountTypes(
            @RequestParam(value = "id", defaultValue = "null") String id,
            @RequestParam(value = "name", defaultValue = "null") String name,
            @RequestParam(value = "pprofit_low", defaultValue = "null") String pprofit_low,
            @RequestParam(value = "pprofit_high", defaultValue = "null") String pprofit_high,
            @RequestParam(value = "fprofit_low", defaultValue = "null") String fprofit_low,
            @RequestParam(value = "fprofit_high", defaultValue = "null") String fprofit_high,
            @RequestParam(value = "debiting", defaultValue = "null") String debiting,
            @RequestParam(value = "accrual", defaultValue = "null") String accrual,
            @RequestParam(value = "valid", defaultValue = "null") String valid,
            @RequestParam(value = "period", defaultValue = "period") String period,
            ModelAndView mv)
    {
        mv.setViewName("AccountTypesTable");

        id = id.equals("null")? null : id;
        name = name.equals("null")? null : name;
        pprofit_low = pprofit_low.equals("null")? null : pprofit_low;
        pprofit_high = pprofit_high.equals("null")? null : pprofit_high;
        fprofit_low = fprofit_low.equals("null")? null : fprofit_low;
        fprofit_high = fprofit_high.equals("null")? null : fprofit_high;
        debiting = debiting.equals("null")? null : debiting;
        accrual = accrual.equals("null")? null : accrual;
        valid = valid.equals("null")? null : valid;
        period = period.equals("null")? null : period;

        List<AccountType> res = accountTypeDAO.getAccountTypeByCondition(id == null? null : Long.valueOf(id),
                name,
                pprofit_low == null? null : Double.valueOf(pprofit_low),
                pprofit_high == null? null : Double.valueOf(pprofit_high),
                fprofit_low == null? null : Double.valueOf(fprofit_low),
                fprofit_high == null? null : Double.valueOf(fprofit_high),
                debiting == null? null : Boolean.valueOf(debiting),
                accrual == null? null : Boolean.valueOf(accrual), period,
                valid == null? null : Boolean.valueOf(valid));

        mv.addObject("rows", res);

        return mv;
    }

    @GetMapping("/TransactionsSearch")
    public ModelAndView getTransactions(
            @RequestParam(value = "id", defaultValue = "null") String id,
            @RequestParam(value = "byAcc", defaultValue = "null") String byAcc,
            @RequestParam(value = "acc", defaultValue = "null") String acc,
            @RequestParam(value = "debit", defaultValue = "null") String debit_account_id,
            @RequestParam(value = "credit", defaultValue = "null") String credit_account_id,
            @RequestParam(value = "timeL", defaultValue = "null") String tran_time_low,
            @RequestParam(value = "timeH", defaultValue = "null") String tran_time_high,
            @RequestParam(value = "amountL", defaultValue = "null") String amount_low,
            @RequestParam(value = "amountH", defaultValue = "null") String amount_high,
            ModelAndView mv)
    {
        mv.setViewName("TransactionsTable");

        id = id.equals("null")? null : id;
        byAcc = byAcc.equals("null")? null : byAcc;
        acc = acc.equals("null")? null : acc;
        debit_account_id = debit_account_id.equals("null")? null : debit_account_id;
        credit_account_id = credit_account_id.equals("null")? null : credit_account_id;
        tran_time_low = tran_time_low.equals("null")? null : tran_time_low;
        tran_time_high = tran_time_high.equals("null")? null : tran_time_high;
        amount_low = amount_low.equals("null")? null : amount_low;
        amount_high = amount_high.equals("null")? null : amount_high;

        List<Transaction> res = null;

        if(byAcc != null)
        {
            res = transactionDAO.getTransactionByAccount(acc);
        }
        else
        {
            res = transactionDAO.getTransactionByCondition(id == null? null : Long.valueOf(id),
                    debit_account_id, credit_account_id,
                    tran_time_low == null? null : Timestamp.valueOf(tran_time_low),
                    tran_time_high == null? null : Timestamp.valueOf(tran_time_high),
                    amount_low == null? null : Double.valueOf(amount_low),
                    amount_high == null? null : Double.valueOf(amount_high));
        }

        mv.addObject("rows", res);

        return mv;
    }
}
