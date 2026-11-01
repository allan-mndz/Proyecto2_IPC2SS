package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.AsistenciaDAO;
import com.example.proyecto2ss.dtos.AsistenciaDTO;
import com.example.proyecto2ss.modelos.Asistencia;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("asistencias")
public class AsistenciaResource {
    private AsistenciaDAO asistenciaDAO = new AsistenciaDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAsistencias() {
        List<Asistencia> lista = asistenciaDAO.obtenerTodas();
        return Response.ok(lista).build();
    }

    @GET
    @Path("detalles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDetallesAsistencias() {
        List<AsistenciaDTO> detalles = asistenciaDAO.obtenerDetalles();
        return Response.ok(detalles).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearAsistencia(Asistencia asistencia) {
        if (asistenciaDAO.crearAsistencia(asistencia)) {
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Asistencia registrada exitosamente\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al registrar la asistencia\"}").build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarAsistencia(@PathParam("id") int id, Asistencia asistencia) {
        asistencia.setId(id);
        if (asistenciaDAO.actualizar(asistencia)) {
            return Response.ok("{\"mensaje\": \"Asistencia actualizada\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al actualizar la asistencia\"}").build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarAsistencia(@PathParam("id") int id) {
        if (asistenciaDAO.eliminar(id)) {
            return Response.ok("{\"mensaje\": \"Asistencia eliminada\"}").build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("{\"error\": \"No se encontro el registro de asistencia\"}").build();
        }
    }
}