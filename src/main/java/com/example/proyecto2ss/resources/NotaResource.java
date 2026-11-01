package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.NotaDAO;
import com.example.proyecto2ss.dtos.NotaDTO;
import com.example.proyecto2ss.modelos.Nota;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("notas")
public class NotaResource {
    private NotaDAO notaDAO = new NotaDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarNotas() {
        List<Nota> lista = notaDAO.obtenerTodas();
        return Response.ok(lista).build();
    }

    @GET
    @Path("detalles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDetallesNotas() {
        List<NotaDTO> detalles = notaDAO.obtenerDetalles();
        return Response.ok(detalles).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearNota(Nota nota) {
        if (notaDAO.crearNota(nota)) {
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Nota registrada exitosamente\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al registrar la nota\"}").build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarNota(@PathParam("id") int id, Nota nota) {
        nota.setId(id);
        if (notaDAO.actualizar(nota)) {
            return Response.ok("{\"mensaje\": \"Nota actualizada\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al actualizar la nota\"}").build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarNota(@PathParam("id") int id) {
        if (notaDAO.eliminar(id)) {
            return Response.ok("{\"mensaje\": \"Nota eliminada\"}").build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("{\"error\": \"No se encontro el registro de la nota\"}").build();
        }
    }
}