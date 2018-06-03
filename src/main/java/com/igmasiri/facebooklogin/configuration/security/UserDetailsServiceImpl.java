package com.igmasiri.facebooklogin.configuration.security;

import com.igmasiri.facebooklogin.models.Cliente;
import com.igmasiri.facebooklogin.models.Comercio;
import com.igmasiri.facebooklogin.models.Empleado;
import com.igmasiri.facebooklogin.models.Usuario;
import com.igmasiri.facebooklogin.services.ClienteService;
import com.igmasiri.facebooklogin.services.ComercioService;
import com.igmasiri.facebooklogin.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextListener;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ComercioService comercioService;
    @Autowired
    private ClienteService clienteService;
    //Intellij tiene un bug que no encuentra como injectar HttpServletRequest, con la siguiente linea hacemos que no lo muestre ya que funciona lo mas bien
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private HttpServletRequest request;
    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

    @Override
    public UserDetails loadUserByUsername(String usernameToken) throws UsernameNotFoundException {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        boolean esEmpleado = false;
        UsuarioSession user;
        try{
            String[] usernameTokenArray = StringUtils.split(usernameToken, String.valueOf(Character.LINE_SEPARATOR));
            String username = usernameTokenArray[0];
            String facebooktoken = usernameTokenArray[1];

            String host = new URL(request.getRequestURL().toString()).getHost();
            Comercio comercio = comercioService.obtenerComercioPorHost(host);
            Cliente cliente = usuarioService.obtenerUsuarioPorTokenFacebook(facebooktoken,comercio);
            Usuario usuario = usuarioService.obtenerUsuarioPorCorreo(cliente.getCorreo());
            if (usuario!=null){
                if (usuario instanceof Cliente){
                    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
                }else if (usuario instanceof Empleado){
                    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_EMPLEADO"));
                    esEmpleado=true;
                }
                user = new UsuarioSession(usuario.getUsername(), "{noop}" + usuario.getPassword(), grantedAuthorities);
            }else{
                cliente.setUsername(cliente.getFacebookid());
                cliente.setPassword(cliente.getFacebookid());
                clienteService.guardar(cliente);
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
                user = new UsuarioSession(cliente.getUsername(), "{noop}" + cliente.getPassword(), grantedAuthorities);
            }
            user.setEsEmpleado(esEmpleado);
            return user;
        }catch (Exception e){
            throw new UsernameNotFoundException("Ocurrio un erro al loguearse",e);
        }
    }
}