package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.CurriculoDAO;
import com.example.proyecto2ss.dtos.CurriculoDTO;
import com.example.proyecto2ss.modelos.Curriculo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("curriculos")
public class CurriculoResource {
    private CurriculoDAO curriculoDAO = new CurriculoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCurriculo(){
        List<Curriculo> curriculos = curriculoDAO.obtenerTodos();
        return Response.ok(curriculos).build();
    }

    @GET
    @Path("detalles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarDetallesCurriculos() {
        List<CurriculoDTO> detalles = curriculoDAO.obtenerDetalles();
        return Response.ok(detalles).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearCurriculo(Curriculo curriculo){
        if (curriculoDAO.crear(curriculo)){
            return Response.status(Response.Status.CREATED).entity("{\"mensaje\": \"Curriculo creada exitosamente\"}").build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al crear el Curriculo\"}").build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCurriculo(@PathParam("id") int id, Curriculo curriculo) {
        curriculo.setId(id);
        if (curriculoDAO.actualizar(curriculo)) {
            return Response.ok("{\"mensaje\": \"Curriculo actualizado\"}").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Error al actualizar\"}").build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarCurriculo(@PathParam("id") int id) {
        if (curriculoDAO.eliminar(id)){
            return Response.ok("{\"mensaje\": \"Curriculo eliminado\"}").build();
        }else {
            return Response.status(Response.Status.CONFLICT).entity("{\"error\": \"No se puede eliminar porque esta en uso o no existe\"}").build();
        }
    }

}
