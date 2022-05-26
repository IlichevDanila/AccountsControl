package ru.dilichev.AccountControl.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dilichev.AccountControl.DAO.*;
import ru.dilichev.AccountControl.Models.*;

import java.util.List;

@Controller
public class EditRequestsController {
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

    @GetMapping("/EditClients")
    public ModelAndView EditClients(@RequestParam(value = "id") String id,
                              ModelAndView mv)
    {
        List<Client> res = clientDAO.getClientByCondition(Long.valueOf(id), null, null, null);
        if(res.get(0).getType().equals("physical"))
        {
            List<PhysicalClient> res2 = physicalClientDAO.getPhysicalClientByCondition(Long.valueOf(id), null,
                    null, null, null, null);
            mv.addObject("obj", res2.get(0));
        }
        else
        {
            List<LegalClient> res2 = legalClientDAO.getLegalClientByCondition(Long.valueOf(id), null,
                    null, null, null, null);
            mv.addObject("obj", res2.get(0));
        }
        mv.setViewName("ClientEdit");
        return mv;
    }

    @GetMapping("/EditAccounts")
    public ModelAndView EditAccounts(@RequestParam(value = "id") String id,
                                     ModelAndView mv)
    {
        mv.setViewName("AccountEdit");

        List<AccountType> accTypes = accountTypeDAO.getAccountTypeByCondition(null, null,
                null, null,
                null, null, null, null, null, true);

        mv.addObject("accTypes", accTypes);

        List<Account> res = accountDAO.getAccountByCondition(id, null, null,
                null, null, null, null, null,
                null, null);

        mv.addObject("obj", res.get(0));

        return mv;
    }

    @GetMapping("/EditAccountTypes")
    public ModelAndView EditAccountTypes(@RequestParam(value = "id") String id,
                                         ModelAndView mv)
    {
        mv.setViewName("AccountTypeEdit");

        List<AccountType> res = accountTypeDAO.getAccountTypeByCondition(Long.valueOf(id), null,
                null, null, null, null,
                null, null, null, null);

        mv.addObject("obj", res.get(0));
        return mv;
    }

    @GetMapping("/EditOffices")
    public ModelAndView EditOffices(@RequestParam(value = "id") String id,
                                    ModelAndView mv)
    {
        mv.setViewName("OfficeEdit");

        List<Office> res = officeDAO.getOfficeByCondition(Long.valueOf(id), null, null);

        mv.addObject("obj", res.get(0));

        return mv;
    }

    @GetMapping("/EditTransactions")
    public ModelAndView EditTransactions(@RequestParam(value = "id") String id,
                                   ModelAndView mv)
    {
        mv.setViewName("TransactionEdit");
        List<Transaction> res = transactionDAO.getTransactionByCondition(Long.valueOf(id), null,
                null, null, null,
                null, null);
        mv.addObject("obj", res.get(0));
        return mv;
    }
}
