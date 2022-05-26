package ru.dilichev.AccountControl.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.dilichev.AccountControl.DAO.AccountTypeDAO;
import ru.dilichev.AccountControl.Models.AccountType;

import java.util.List;

@Controller
public class SearchRequestsController {
    @Autowired
    AccountTypeDAO accountTypeDAO;

    @GetMapping("/Clients")
    public String SearchClients()
    {
        return "ClientSearch";
    }

    @GetMapping("/Accounts")
    public ModelAndView SearchAccounts(ModelAndView mv)
    {
        mv.setViewName("AccountSearch");

        List<AccountType> accTypes = accountTypeDAO.getAccountTypeByCondition(null, null, null, null,
                null, null, null, null, null, true);

        mv.addObject("accTypes", accTypes);
        return mv;
    }

    @GetMapping("/AccountTypes")
    public String SearchAccountTypes()
    {
        return "AccountTypeSearch";
    }

    @GetMapping("/Offices")
    public String SearchOffices()
    {
        return "OfficeSearch";
    }

    @GetMapping("/Transactions")
    public String SearchTransactions()
    {
        return "TransactionSearch";
    }
}
