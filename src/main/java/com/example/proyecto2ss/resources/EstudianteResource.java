package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.EstudianteDAO;
import com.example.proyecto2ss.dtos.EstudianteDTO;
import com.example.proyecto2ss.dtos.InscripcionDTO;
import com.example.proyecto2ss.modelos.Estudiante;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("estudiantes")
public class EstudianteResource {

    private EstudianteDAO estudianteDAO = new EstudianteDAO();

    // GET: Obtener lista de todos los estudiantes
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarEstudiantes(){
        List<Estudiante> estudiantes = estudianteDAO.obtenerTodos();
        return Response.ok(estudiantes).build();
    }

    @GET
    @Path("detalles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDetallesInscripciones() {
        List<EstudianteDTO> detalles = estudianteDAO.obtenerDetales();
        return Response.ok(detalles).build();
    }


    // POST: Crear un nuevo estudiante
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearEstudiante(Estudiante estudiante) {
        boolean exito = estudianteDAO.crear(estudiante);
        if (exito) {
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Estudiante creado exitosamente\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al crear el estudiante. Verifica que el usuario_id exista en la tabla Usuario\"}").build();
        }
    }


    // PUT: Actualizar un estudiante por su ID.
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarEstudiante(@PathParam("id") int id, Estudiante estudiante) {
        estudiante.setId(id); // Aseguramos que el ID del JSON sea el mismo de la url
        boolean exito = estudianteDAO.actualizar(estudiante);
        if (exito) {
            return Response.ok("{\"mensaje\": \"Estudiante actualizado exitosamente\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al actualizar\"}").build();
        }
    }


    // DELETE: Cambiar estado a Inactivo.
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response desactivarEstudiante(@PathParam("id") int id) {
        boolean exito = estudianteDAO.cambiarEstado(id);
        if (exito) {
            return Response.ok("{\"mensaje\": \"Estudiante dado de baja (Inactivo)\"}").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"Estudiante no encontrado\"}").build();
        }
    }

}
