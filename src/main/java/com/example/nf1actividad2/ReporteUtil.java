package com.example.nf1actividad2;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

public class ReporteUtil {


    public static void generarPdfDesdeJrxml(String jrxmlResourcePath, String outputPdfPath, Map<String, Object> parametros) throws JRException {
        // Carga el archivo .jrxml desde la carpeta resource
        InputStream jrxmlStream = ReporteUtil.class.getResourceAsStream(jrxmlResourcePath);
        if (jrxmlStream == null) {
            throw new JRException("No se encontró el recurso: " + jrxmlResourcePath);
        }

        // Compila el archivo .jrxml en un objeto JasperReport
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

        //Crea un objeto JasperPrint rellenando el informe con los parametros recibidos
        //Permite generar el informe aunque no haya base de datos asociada
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                parametros,
                new JRBeanCollectionDataSource(Collections.singletonList(new Object()))
        );

        // Exporta el informe ya completado a un archivo pdf en la ruta indicada
        JasperExportManager.exportReportToPdfFile(jasperPrint, outputPdfPath);
    }
}