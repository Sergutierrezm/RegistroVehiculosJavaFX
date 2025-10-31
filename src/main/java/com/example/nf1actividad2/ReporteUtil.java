package com.example.nf1actividad2;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

public class ReporteUtil {

    /**
     * Genera un PDF a partir del jrxml que esté en resources/reportes/
     * @param jrxmlResourcePath recurso relativo, p.ej. "/com/example/nf1actividad2/reportes/informeVehiculo.jrxml"
     * @param outputPdfPath ruta de salida en el filesystem, p.ej. "informeVehiculo.pdf"
     * @param parametros mapa de parámetros para el informe
     * @throws JRException si hay problema con Jasper
     */
    public static void generarPdfDesdeJrxml(String jrxmlResourcePath, String outputPdfPath, Map<String, Object> parametros) throws JRException {
        // Cargamos el jrxml como stream desde resources
        InputStream jrxmlStream = ReporteUtil.class.getResourceAsStream(jrxmlResourcePath);
        if (jrxmlStream == null) {
            throw new JRException("No se encontró el recurso: " + jrxmlResourcePath);
        }

        // Compilar el JRXML
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

        // Llenamos con parámetros y datasource mínimo
        // Collections.singletonList(new Object()) asegura que los parámetros se muestren
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                parametros,
                new JRBeanCollectionDataSource(Collections.singletonList(new Object()))
        );

        // Exportar a PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, outputPdfPath);
    }
}