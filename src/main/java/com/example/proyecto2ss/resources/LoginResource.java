package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.UsuarioDAO;
import com.example.proyecto2ss.modelos.Usuario;
import com.example.proyecto2ss.util.JwtUtil;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("usuarios")
public class LoginResource {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.obtenerTodos();
        return Response.ok(usuarios).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearUsuario(Usuario usuario) {
        if (usuarioDAO.crear(usuario)) {
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Usuario creado exitosamente\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al crear el usuario\"}").build();
        }
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response iniciarSesion(Usuario credenciales) {

        Usuario usuarioDB = usuarioDAO.obtenerPorUsername(credenciales.getUsername());

        if (usuarioDB != null && BCrypt.checkpw(credenciales.getContrasena(), usuarioDB.getContrasena())) {

            // Generamos el token
            String token = JwtUtil.generarToken(usuarioDB.getUsername(), usuarioDB.getRol());

            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("token", token);
            respuesta.put("rol", usuarioDB.getRol());
            respuesta.put("username", usuarioDB.getUsername());

            return Response.ok(respuesta).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\": \"Usuario o contraseña incorrectos\"}").build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarUsuario(@PathParam("id") int id, Usuario usuario) {
        usuario.setId(id);
        if (usuarioDAO.actualizar(usuario)) {
            return Response.ok("{\"mensaje\": \"Usuario actualizado\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al actualizar\"}").build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response desactivarUsuario(@PathParam("id") int id) {
        if (usuarioDAO.desactivar(id)) {
            return Response.ok("{\"mensaje\": \"Usuario desactivado correctamente\"}").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"Usuario no encontrado\"}").build();
        }
    }
}
