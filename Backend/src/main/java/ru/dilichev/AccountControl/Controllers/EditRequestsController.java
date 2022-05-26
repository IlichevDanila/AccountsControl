package ru.dilichev.AccountControl.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.dilichev.AccountControl.DAO.AccountTypeDAO;
import ru.dilichev.AccountControl.Models.AccountType;

import java.util.List;

@Controller
public class EditRequestsController {
    @Autowired
    AccountTypeDAO accountTypeDAO;

    @GetMapping("/EditClients")
    public String EditClients()
    {
        return "ClientEdit";
    }

    @GetMapping("/EditAccounts")
    public ModelAndView EditAccounts(ModelAndView mv)
    {
        mv.setViewName("AccountEdit");

        List<AccountType> accTypes = accountTypeDAO.getAccountTypeByCondition(null, null, null, null,
                null, null, null, null, null, true);

        mv.addObject("accTypes", accTypes);
        return mv;
    }

    @GetMapping("/EditAccountTypes")
    public String EditAccountTypes()
    {
        return "AccountTypeEdit";
    }

    @GetMapping("/EditOffices")
    public String EditOffices()
    {
        return "OfficeEdit";
    }

    @GetMapping("/EditTransactions")
    public String EditTransactions()
    {
        return "TransactionEdit";
    }
}
