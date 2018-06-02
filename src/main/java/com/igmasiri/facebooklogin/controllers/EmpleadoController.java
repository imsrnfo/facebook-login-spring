package com.igmasiri.facebooklogin.controllers;

import com.igmasiri.facebooklogin.configuration.exceptions.AplicationHandledException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/empleado")
@PreAuthorize("hasAuthority('ROLE_EMPLEADO')")
public class EmpleadoController {

    @GetMapping("/home")
    public String home(Model model) throws AplicationHandledException {
        return "empleado/home";
    }

}
