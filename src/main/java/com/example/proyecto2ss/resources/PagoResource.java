package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.PagoDAO;
import com.example.proyecto2ss.dtos.PagoDTO;
import com.example.proyecto2ss.modelos.Pago;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("pagos")
public class PagoResource {
    private PagoDAO pagoDAO = new PagoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPagos() {
        List<Pago> pagos = pagoDAO.obtenerTodos();
        return Response.ok(pagos).build();
    }

    @GET
    @Path("detalles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDetallesPagos() {
        List<PagoDTO> detalles = pagoDAO.obtenerDetalles();
        return Response.ok(detalles).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearPago(Pago pago) {
        if (pagoDAO.crear(pago)) {
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Pago registrado exitosamente\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al registrar el pago\"}").build();
        }
    }
}