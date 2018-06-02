package com.igmasiri.facebooklogin.services;

import com.igmasiri.facebooklogin.configuration.exceptions.AplicationHandledException;
import com.igmasiri.facebooklogin.models.*;
import com.igmasiri.facebooklogin.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComercioService {

	@Autowired
	private ComercioRepository comercioRepository;

	public Comercio obtenerComercioPorHost(String host) throws AplicationHandledException {
		Comercio comercio = comercioRepository.obtenerPorHost(host);
		if (comercio == null) throw new AplicationHandledException("No existe comercio para el host");
		return comercio;
	}

}
