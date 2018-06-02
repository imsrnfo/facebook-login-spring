package com.igmasiri.facebooklogin.controllers;

import com.igmasiri.facebooklogin.models.Comercio;
import com.igmasiri.facebooklogin.services.ComercioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ComercioService comercioService;

    @GetMapping({"", "/"})
    public String login(Model model, HttpServletRequest request) {
        Comercio comercio = obtenerComercio(model,request,"login");
        model.addAttribute("comercio", comercio);
        return "login";
    }


    private Comercio obtenerComercio(Model model, HttpServletRequest request, String url){
        try {
            String host = new URL(request.getRequestURL().toString()).getHost();
            Comercio comercio = comercioService.obtenerComercioPorHost(host);
            return comercio;
        } catch (Exception e) {}
        return null;
    }

}
