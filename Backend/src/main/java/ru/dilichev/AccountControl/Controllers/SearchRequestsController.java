package ru.dilichev.AccountControl.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchRequestsController {
    @GetMapping("/Clients")
    public String SearchClients()
    {
        return "ClientSearch";
    }

    @GetMapping("/Accounts")
    public String SearchAccounts()
    {
        return "AccountSearch";
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
