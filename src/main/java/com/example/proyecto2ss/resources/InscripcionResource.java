package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.InscripcionDAO;
import com.example.proyecto2ss.dtos.InscripcionDTO;
import com.example.proyecto2ss.modelos.Inscripcion;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("inscripciones")
public class InscripcionResource {
    private InscripcionDAO inscripcionDAO = new InscripcionDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarInscripciones(){
        List<Inscripcion> inscripciones = inscripcionDAO.obtenerTodos();
        return Response.ok(inscripciones).build();
    }

    @GET
    @Path("detalles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDetallesInscripciones() {
        List<InscripcionDTO> detalles = inscripcionDAO.obtenerDetalles();
        return Response.ok(detalles).build();
    }



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearInscripcion(Inscripcion inscripcion){
        if (inscripcionDAO.crearInscripcion(inscripcion)){
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"inscripcion creado exitosamente\"}").build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al crear la inscripcion\"}").build();
        }

    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarInscripcion(@PathParam("id") int id, Inscripcion inscripcion){
        inscripcion.setId(id);
        if (inscripcionDAO.actualizar(inscripcion)){
            return Response.ok("{\"mensaje\": \"inscripcion actualizado\"}").build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al actualizar\"}").build();
        }

    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarInscripcion(@PathParam("id") int id){
        if (inscripcionDAO.eliminar(id)){
            return Response.ok("{\"mensaje\": \"inscripcion eliminada\"}").build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("{\"error\": \"No se puede eliminar porque esta en uso o no existe\"}").build();
        }
    }
}
