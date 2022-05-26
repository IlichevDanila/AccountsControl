package ru.dilichev.AccountControl.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.dilichev.AccountControl.DAO.AccountTypeDAO;
import ru.dilichev.AccountControl.Models.AccountType;

import java.util.List;

@Controller
public class CreateController {
    @Autowired
    AccountTypeDAO accountTypeDAO;

    @GetMapping("/CreateClients")
    public String CreateClients()
    {
        return "CreateClients";
    }

    @GetMapping("/CreateAccounts")
    public ModelAndView CreateAccounts(ModelAndView mv)
    {
        mv.setViewName("CreateAccounts");

        List<AccountType> accTypes = accountTypeDAO.getAccountTypeByCondition(null, null, null, null,
                null, null, null, null, null, true);

        mv.addObject("accTypes", accTypes);
        return mv;
    }

    @GetMapping("/CreateOffices")
    public String CreateOffices()
    {
        return "CreateOffices";
    }

    @GetMapping("/CreateAccountTypes")
    public String CreateAccountTypes()
    {
        return "CreateAccountTypes";
    }

    @GetMapping("/CreateTransactions")
    public String CreateTransactions()
    {
        return "CreateTransactions";
    }
}