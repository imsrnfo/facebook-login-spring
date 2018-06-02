package com.igmasiri.facebooklogin.controllers;

import com.igmasiri.facebooklogin.configuration.exceptions.AplicationHandledException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/cliente")
@PreAuthorize("hasAuthority('ROLE_CLIENTE')")
public class ClienteController {

    @GetMapping("/home")
    public String home(Model model) throws AplicationHandledException {
        return "cliente/home";
    }

}
