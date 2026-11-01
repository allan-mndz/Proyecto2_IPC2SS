package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.PrestamoDAO;
import com.example.proyecto2ss.dtos.PrestamoDTO;
import com.example.proyecto2ss.modelos.Prestamo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("prestamos")
public class PrestamoResource {
    private PrestamoDAO prestamoDAO = new PrestamoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPrestamos() {
        List<Prestamo> prestamos = prestamoDAO.obtenerTodos();
        return Response.ok(prestamos).build();
    }

    @GET
    @Path("detalles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDetallesPrestamos() {
        List<PrestamoDTO> detalles = prestamoDAO.obtenerDetalles();
        return Response.ok(detalles).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearPrestamo(Prestamo prestamo) {
        if (prestamoDAO.crear(prestamo)) {
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Prestamo registrado\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al registrar\"}").build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarDevolucion(@PathParam("id") int id, Prestamo prestamo) {
        prestamo.setId(id);
        if (prestamoDAO.actualizar(prestamo)) {
            return Response.ok("{\"mensaje\": \"Devolucion registrada\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al actualizar\"}").build();
        }
    }
}