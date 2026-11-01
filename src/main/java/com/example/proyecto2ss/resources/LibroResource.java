package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.LibroDAO;
import com.example.proyecto2ss.modelos.Libro;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("libros")
public class LibroResource {

    private LibroDAO libroDAO = new LibroDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarLibros(){
        List<Libro> lista = libroDAO.obtenerTodos();
        return Response.ok(lista).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearLibro(Libro libro){
        if (libroDAO.crearLibro(libro)){
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Libro creado exitosamente\"}").build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al crear el libro\"}").build();

        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarLibro(@PathParam("id") int id, Libro libro ){
        libro.setId(id);
        if (libroDAO.actualizarLibro(libro)){
            return Response.ok("{\"mensaje\": \"Libro actualizado\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al actualizar\"}").build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarLibro(@PathParam("id") int id ){
        if (libroDAO.eliminarLibro(id)){
            return Response.ok("{\"mensaje\": \"Libro eliminado\"}").build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("{\"error\": \"No se puede eliminar porque esta en uso o no existe\"}").build();
        }
    }

}
