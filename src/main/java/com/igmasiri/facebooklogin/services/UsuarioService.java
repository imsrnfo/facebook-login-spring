package com.igmasiri.facebooklogin.services;

import com.google.gson.Gson;
import com.igmasiri.facebooklogin.models.Cliente;
import com.igmasiri.facebooklogin.models.Comercio;
import com.igmasiri.facebooklogin.models.Usuario;
import com.igmasiri.facebooklogin.repositories.ComercioRepository;
import com.igmasiri.facebooklogin.repositories.UsuarioRepository;
import com.igmasiri.facebooklogin.utils.HTTPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private ComercioRepository comercioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario obtenerUsuarioPorCorreo(String correo){
        return usuarioRepository.obtenerPorCorreo(correo);
    }

    private void actualizarAccessToken(Comercio comercio) throws Exception {
        String response = HTTPUtils.getJSON("https://graph.facebook.com/oauth/access_token?client_id=" + comercio.getFacebookAplicationId() + "&client_secret=" + comercio.getFacebookSecretKey() + "&type=client_cred");
        Map jsonMap = new Gson().fromJson(response, Map.class);
        comercio.setFacebookAccessTokenApi((String) jsonMap.get("access_token"));
        comercioRepository.save(comercio);
    }
    // Verifica que el token de facebook sea valido
    public Cliente obtenerUsuarioPorTokenFacebook(String token, Comercio comercio) throws Exception {

        String response = HTTPUtils.getJSON("https://graph.facebook.com/debug_token?input_token=" + token + "&access_token=" + comercio.getFacebookAccessTokenApi());
        try {
            //pruebo si funciono obteniendo los datos del usuario
            return obtenerUsuarioPorFacebookResponse(response, token, comercio);
        } catch (Exception e) {
            try {
                //si no pruebo actualizando el token de la aplicacion
                Map jsonMap = new Gson().fromJson(response, Map.class);
                Map data = (Map) jsonMap.get("data");
                Map error = (Map) data.get("error");
                double code = (Double) error.get("code");
                if (code == 190) {
                    actualizarAccessToken(comercio);
                    //se prueba nuevamente si el token es valido (esta vez con el token de la aplicacion actualizado)
                    return obtenerUsuarioPorFacebookToken(token, comercio);
                }
            } catch (Exception ex) {
            }
        }
        return null;
    }
    private Cliente obtenerUsuarioPorFacebookToken(String token, Comercio comercio) throws Exception {
        String response = HTTPUtils.getJSON("https://graph.facebook.com/debug_token?input_token=" + token + "&access_token=" + comercio.getFacebookAccessTokenApi());
        return obtenerUsuarioPorFacebookResponse(response, token, comercio);
    }
    private Cliente obtenerUsuarioPorFacebookResponse(String response, String token, Comercio comercio) throws Exception {
        Map jsonMap = new Gson().fromJson(response, Map.class);
        Map data = (Map) jsonMap.get("data");
        boolean is_valid = (Boolean) data.get("is_valid");
        if (is_valid) {
            String user_id = (String) data.get("user_id");
            Cliente usuario = new Cliente();
            response = HTTPUtils.getJSON("https://graph.facebook.com/" + user_id + "?access_token=" + comercio.getFacebookAccessTokenApi() + "&fields=email,name");
            jsonMap = new Gson().fromJson(response, Map.class);
            String name = (String) jsonMap.get("name");
            String[] splited = name.split(" ");
            usuario.setCorreo((String) jsonMap.get("email"));
            usuario.setNombre((splited.length>0) ? splited[0] : "");
            usuario.setApellido((splited.length>1) ? splited[1] : "");
            usuario.setImagen("https://graph.facebook.com/"+user_id+"/picture?type=large&redirect=true&width=500&height=500&return_ssl_resources=true");
            usuario.setFacebookid(user_id);
            usuario.setFacebooktoken(token);
            return usuario;
        }
        return null;
    }

}
