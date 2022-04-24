package ru.dilichev.AccountControl.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dilichev.AccountControl.DAO.LegalClientDAO;
import ru.dilichev.AccountControl.DAO.PhysicalClientDAO;
import ru.dilichev.AccountControl.Models.LegalClient;
import ru.dilichev.AccountControl.Models.PhysicalClient;

import java.util.List;

@Controller
public class TablesController {
    @Autowired
    PhysicalClientDAO physicalClientDAO;

    @Autowired
    LegalClientDAO legalClientDAO;

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
            System.out.println(physicalClientDAO.SQLByCondition(null, phone, address, fullName, passport, tin));

            List<PhysicalClient> res = physicalClientDAO.getPhysicalClientByCondition(null, phone, address, fullName, passport, tin);
            mv.addObject("rows", res);
            mv.addObject("tabSize", res.size());
            mv.setViewName("PhysicalClientsTable");
        }
        else if(type.equals("Legal"))
        {
            List<LegalClient> res = legalClientDAO.getLegalClientByCondition(null, phone, address, name, form, tin);
            mv.addObject("rows", res);
            mv.addObject("tabSize", res.size());
            mv.setViewName("LegalClientsTable");
        }
        return mv;
    }
}
