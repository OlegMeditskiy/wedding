package ru.dasha.wedding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dasha.wedding.domain.WeddingDate;
import ru.dasha.wedding.domain.user.User;
import ru.dasha.wedding.repos.WeddingDateRepo;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    WeddingDateRepo weddingDateRepo;

    @GetMapping
    public String main(Model model) {
        Date date = weddingDateRepo.findById((long) 2).get().getWeddingDate();

        model.addAttribute("date",date);
        return "main";
    }

}


