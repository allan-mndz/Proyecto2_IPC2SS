package com.example.proyecto2ss.resources;


import com.example.proyecto2ss.daos.SeccionDAO;
import com.example.proyecto2ss.modelos.Seccion;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("secciones")
public class SeccionResource {
    private SeccionDAO seccionDAO = new SeccionDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarSecciones(){
        List<Seccion> secciones = seccionDAO.obtenerTodos();
        return Response.ok(secciones).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearSeccion(Seccion seccion){
        if (seccionDAO.crear(seccion)){
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Seccion creado exitosamente\"}").build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al crear la seccion\"}").build();
        }

    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarSeccion(@PathParam("id") int id, Seccion seccion){
        seccion.setId(id);
        if (seccionDAO.actualizar(seccion)){
            return Response.ok("{\"mensaje\": \"Seccion actualizado\"}").build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al actualizar\"}").build();
        }

    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarSeccion(@PathParam("id") int id){
        if (seccionDAO.eliminar(id)){
            return Response.ok("{\"mensaje\": \"Seccion eliminada\"}").build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("{\"error\": \"No se puede eliminar porque esta en uso o no existe\"}").build();
        }

    }

}
