package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.CicloEscolarDAO;
import com.example.proyecto2ss.modelos.CicloEscolar;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("ciclos")
public class CicloEscolarResource {
    private CicloEscolarDAO cicloDAO = new CicloEscolarDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCiclos() {
        List<CicloEscolar> ciclos = cicloDAO.obtenerTodos();
        return Response.ok(ciclos).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearCiclo(CicloEscolar ciclo) {
        if (cicloDAO.crear(ciclo)) {
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Ciclo Escolar creado exitosamente\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al crear el Ciclo Escolar\"}").build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCiclo(@PathParam("id") int id, CicloEscolar ciclo) {
        ciclo.setId(id);
        if (cicloDAO.actualizar(ciclo)) {
            return Response.ok("{\"mensaje\": \"Ciclo Escolar actualizado\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al actualizar\"}").build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarCiclo(@PathParam("id") int id) {
        if (cicloDAO.eliminar(id)) {
            return Response.ok("{\"mensaje\": \"Ciclo Escolar eliminado correctamente\"}").build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("{\"error\": \"No se puede eliminar porque esta en uso o no existe\"}").build();
        }
    }
}