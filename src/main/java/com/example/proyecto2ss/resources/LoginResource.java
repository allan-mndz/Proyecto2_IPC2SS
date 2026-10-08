package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.UsuarioDAO;
import com.example.proyecto2ss.modelos.Usuario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("login")
public class LoginResource {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response autenticas(Usuario credenciales){
        String username = credenciales.getUsername();
        String contrasena = credenciales.getContrasena();

        Usuario usuarioAutenticado = usuarioDAO.autenticar(username, contrasena);

        if (usuarioAutenticado != null && usuarioAutenticado.getActivo() == 1){
            usuarioAutenticado.setContrasena(null);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "login exitoso");
            respuesta.put("usuario", usuarioAutenticado);

            return Response.ok(respuesta).build();
        } else if (usuarioAutenticado != null && usuarioAutenticado.getActivo() == 0) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Su cuenta ha sido desactivada");

            return Response.status(Response.Status.FORBIDDEN).entity(error).build();

        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Usuario o contrasena incorrecta");

            return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
        }
    }
}
