package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.AsignacionMaestroDAO;
import com.example.proyecto2ss.dtos.AsignacionMaestroDTO;
import com.example.proyecto2ss.modelos.AsignacionMaestro;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("asignacionMaestros")
public class AsignacionMaestroResource {
    private AsignacionMaestroDAO asignacionMaestroDAO = new AsignacionMaestroDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAsignacionMaestro(){
        List<AsignacionMaestro> listaAsignacionMaestros = asignacionMaestroDAO.listarTodos();
        return Response.ok(listaAsignacionMaestros).build();

    }

    @GET
    @Path("detalles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDetallesAsignaciones() {
        List<AsignacionMaestroDTO> detalles = asignacionMaestroDAO.obtenerDetalles();
        return Response.ok(detalles).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearAsignacionMaestro(AsignacionMaestro asignacionMaestro){
        if (asignacionMaestroDAO.crearAsignacionMaestro(asignacionMaestro)) {
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Asignacion del maestro creado exitosamente\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al crear la asignacion del maestro\"}").build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarAsignacionMaestro(@PathParam("id") int id, AsignacionMaestro asignacionMaestro){
        asignacionMaestro.setId(id);
        if (asignacionMaestroDAO.actualizarAsignacionMaestro(asignacionMaestro)){
            return Response.ok("{\"mensaje\": \"asignacion del maestro actualizado\"}").build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al actualizar\"}").build();

        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarAsignacionMaestro(@PathParam("id") int id){
        if (asignacionMaestroDAO.eliminarAsignacionMaestro(id)){
            return Response.ok("{\"mensaje\": \"Asignacion de maestro eliminado\"}").build();
        }else {
            return Response.status(Response.Status.CONFLICT).entity("{\"error\": \"No se puede eliminar porque esta en uso o no existe\"}").build();
        }
    }
}
