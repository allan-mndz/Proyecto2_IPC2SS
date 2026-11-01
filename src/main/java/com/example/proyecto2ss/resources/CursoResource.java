package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.CursoDAO;
import com.example.proyecto2ss.modelos.Curso;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("cursos")
public class CursoResource {
    private CursoDAO cursoDAO = new CursoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCursos() {
        List<Curso> cursos = cursoDAO.obtenerTodos();
        return Response.ok(cursos).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearCurso(Curso curso) {
        if (cursoDAO.crear(curso)) {
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Curso creado exitosamente\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al crear el curso\"}").build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCurso(@PathParam("id") int id, Curso curso) {
        curso.setId(id);
        if (cursoDAO.actualizar(curso)) {
            return Response.ok("{\"mensaje\": \"Curso actualizado\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al actualizar\"}").build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarCurso(@PathParam("id") int id) {
        if (cursoDAO.eliminar(id)) {
            return Response.ok("{\"mensaje\": \"Curso eliminado\"}").build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("{\"error\": \"No se puede eliminar porque esta en uso o no existe\"}").build();
        }
    }

}
