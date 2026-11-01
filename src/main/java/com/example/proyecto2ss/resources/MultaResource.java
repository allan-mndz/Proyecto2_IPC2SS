package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.MultaDAO;
import com.example.proyecto2ss.dtos.MultaDTO;
import com.example.proyecto2ss.modelos.Multa;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("multas")
public class MultaResource {
    private MultaDAO multaDAO = new MultaDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarMultas() {
        List<Multa> multas = multaDAO.obtenerTodas();
        return Response.ok(multas).build();
    }

    @GET
    @Path("detalles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDetallesMultas() {
        List<MultaDTO> detalles = multaDAO.obtenerDetalles();
        return Response.ok(detalles).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearMulta(Multa multa) {
        if (multaDAO.crear(multa)) {
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Multa generada\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al generar multa\"}").build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response pagarMulta(@PathParam("id") int id, Multa multa) {
        multa.setId(id);
        if (multaDAO.pagarMulta(multa)) {
            return Response.ok("{\"mensaje\": \"Multa pagada exitosamente\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al registrar pago\"}").build();
        }
    }
}