package com.example.proyecto2ss.resources;

import com.example.proyecto2ss.daos.ReporteDAO;
import com.example.proyecto2ss.dtos.*;
import com.example.proyecto2ss.util.JasperUtil;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("reportes")
public class ReporteResource {

    private ReporteDAO reporteDAO = new ReporteDAO();

    @GET
    @Path("ingresos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerReporteIngresos(
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin) {

        List<ReporteIngresoDTO> detalles = reporteDAO.obtenerIngresos(fechaInicio, fechaFin);
        return Response.ok(detalles).build();
    }

    @GET
    @Path("morosos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerReporteMorosos(
            @QueryParam("gradoId") int gradoId,
            @QueryParam("seccionId") int seccionId) {

        // Validamos que Angular sí nos haya enviado los filtros
        if (gradoId <= 0 || seccionId <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Debe seleccionar un grado y una sección.").build();
        }

        List<ReporteMorosoDTO> detalles = reporteDAO.obtenerMorosos(gradoId, seccionId);
        return Response.ok(detalles).build();
    }

    @GET
    @Path("rendimiento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerReporteRendimiento(
            @QueryParam("gradoId") int gradoId,
            @QueryParam("seccionId") int seccionId) {

        if (gradoId <= 0 || seccionId <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Debe seleccionar un grado y una sección.")
                    .build();
        }

        List<ReporteRendimientoDTO> detalles = reporteDAO.obtenerRendimiento(gradoId, seccionId);
        return Response.ok(detalles).build();
    }

    @GET
    @Path("historial-pagos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerReporteHistorialPagos(
            @QueryParam("estudianteId") int estudianteId,
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin) {

        if (estudianteId <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Debe especificar el ID del estudiante.")
                    .build();
        }

        List<ReporteHistorialPagoDTO> detalles = reporteDAO.obtenerHistorialPagos(estudianteId, fechaInicio, fechaFin);
        return Response.ok(detalles).build();
    }

    @GET
    @Path("prestamos-mora")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerReportePrestamosMora() {
        List<ReportePrestamoMoraDTO> detalles = reporteDAO.obtenerPrestamosMora();
        return Response.ok(detalles).build();
    }

    @GET
    @Path("ingresos/pdf")
    @Produces("application/pdf")
    public Response descargarPdfIngresos(
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin) {

        // Obtenemos los datos de la base de datos
        List<ReporteIngresoDTO> datos = reporteDAO.obtenerIngresos(fechaInicio, fechaFin);

        Map<String, Object> parametros = new java.util.HashMap<>();
        parametros.put("TITULO_REPORTE", "Reporte de Ingresos del Colegio");

        // Generamos el pdf indicando dónde estará el diseño
        byte[] pdfBytes = JasperUtil.generarPdf("/reportes/ReporteIngresos.jrxml", parametros, datos);

        // Lo enviamos de regreso forzando la descarga en el navegador
        return Response.ok(pdfBytes).header("Content-Disposition", "attachment; filename=Reporte_Ingresos.pdf").build();
    }

}