package ru.dilichev.AccountControl.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import ru.dilichev.AccountControl.DAO.PhysicalClientDAO;
import ru.dilichev.AccountControl.Models.PhysicalClient;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    PhysicalClientDAO physicalClientDAO;

    @Autowired
    ViewResolver viewResolver;

    @GetMapping({"/", "/main"})
    public String getMainPage()
    {
        return "main";
    }
}
