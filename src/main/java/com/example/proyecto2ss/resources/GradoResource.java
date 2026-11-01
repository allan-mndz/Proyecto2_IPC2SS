package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.GradoDAO;
import com.example.proyecto2ss.dtos.GradoDTO;
import com.example.proyecto2ss.modelos.Grado;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("grados")
public class GradoResource {
    private GradoDAO gradoDAO = new GradoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarGrados(){
        List<Grado> grados = gradoDAO.obtenerTodos();
        return Response.ok(grados).build();
    }

    @GET
    @Path("detalles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDetallesGrados(){
        List<GradoDTO> gradosDTOS = gradoDAO.obtenerDetalles();
        return Response.ok(gradosDTOS).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearGrado(Grado grado){
        if (gradoDAO.crear(grado)){
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Grado creado exitosamente\"}").build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al crear el Grado\"}").build();
        }

    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarGrado(@PathParam("id") int id, Grado grado){
        grado.setId(id);
        if (gradoDAO.actualizar(grado)){
            return Response.ok("{\"mensaje\": \"Grado actualizado\"}").build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al actualizar\"}").build();
        }

    }


    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarGrado(@PathParam("id") int id){
        if (gradoDAO.eliminar(id)){
            return Response.ok("{\"mensaje\": \"Grado eliminado\"}").build();
        }else {
            return Response.status(Response.Status.CONFLICT).entity("{\"error\": \"No se puede eliminar porque este en uso o no existe\"}").build();

        }
    }
}
