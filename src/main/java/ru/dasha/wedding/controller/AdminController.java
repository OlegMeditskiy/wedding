package ru.dasha.wedding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dasha.wedding.domain.AboutUs;
import ru.dasha.wedding.repos.AboutUsRepo;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    AboutUsRepo aboutUsRepo;

    @GetMapping
    public String admin() {
        return "admin";
    }

}
