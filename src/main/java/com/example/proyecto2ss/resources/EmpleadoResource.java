package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.EmpleadoDAO;
import com.example.proyecto2ss.dtos.EmpleadoDTO;
import com.example.proyecto2ss.dtos.InscripcionDTO;
import com.example.proyecto2ss.modelos.Empleado;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("empleados")
public class EmpleadoResource {

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarEmpleados() {
        List<Empleado> empleados = empleadoDAO.obtenerTodos();
        return Response.ok(empleados).build();
    }

    @GET
    @Path("detalles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDetallesInscripciones() {
        List<EmpleadoDTO> detalles = empleadoDAO.obtenerDetalles();
        return Response.ok(detalles).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearEmpleado(Empleado emp) {
        if (empleadoDAO.crear(emp)) {
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Empleado creado\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al crear empleado\"}").build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarEmpleado(@PathParam("id") int id, Empleado emp) {
        emp.setId(id);
        if (empleadoDAO.actualizar(emp)) {
            return Response.ok("{\"mensaje\": \"Empleado actualizado\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al actualizar\"}").build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response desactivarEmpleado(@PathParam("id") int id) {
        if (empleadoDAO.desactivar(id)) {
            return Response.ok("{\"mensaje\": \"Empleado dado de baja\"}").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"Empleado no encontrado\"}").build();
        }
    }
}