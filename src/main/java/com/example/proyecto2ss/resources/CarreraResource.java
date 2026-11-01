package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.CarreraDAO;
import com.example.proyecto2ss.modelos.Carrera;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("carreras")
public class CarreraResource {
    private CarreraDAO carreraDAO = new CarreraDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCarreras() {
        List<Carrera> carreras = carreraDAO.obtenerTodas();
        return Response.ok(carreras).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearCarrera(Carrera carrera) {
        if (carreraDAO.crear(carrera)) {
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Carrera creada exitosamente\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al crear la Carrera\"}").build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCarrera(@PathParam("id") int id, Carrera carrera) {
        carrera.setId(id);
        if (carreraDAO.actualizar(carrera)) {
            return Response.ok("{\"mensaje\": \"Carrera actualizada\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al actualizar\"}").build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarCarrera(@PathParam("id") int id) {
        if (carreraDAO.desactivar(id)) {
            return Response.ok("{\"mensaje\": \"Carrera desactivada correctamente\"}").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"No se encontro la carrera\"}").build();
        }
    }
}