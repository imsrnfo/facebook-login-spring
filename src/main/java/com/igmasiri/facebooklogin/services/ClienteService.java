package com.igmasiri.facebooklogin.services;

import com.igmasiri.facebooklogin.models.Cliente;
import com.igmasiri.facebooklogin.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void guardar(Cliente cliente){
        clienteRepository.save(cliente);
    }

}
