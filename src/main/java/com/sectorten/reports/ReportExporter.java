/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.reports;

import com.lowagie.text.pdf.PdfWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.management.JMRuntimeException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

/**
 *
 * @author
 */
public class ReportExporter {

    public static final Integer PDF_REPORT_FORMAT = 1;
    public static final Integer XLS_REPORT_FORMAT = 2;
    public static final Integer DOC_REPORT_FORMAT = 3;
    public static final Integer CSV_REPORT_FORMAT = 4;

    public static void exportReportFile(String reportRelPath, HashMap<String, Object> reportParams, String exReportName, Integer reportFormat) throws JRException {

        Connection connection = null;
        String jasperJNDI = ResourceBundle.getBundle("Resources/AppSettings").getString("rep_conn_jndi");

        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(jasperJNDI);
            connection = dataSource.getConnection();

            String reportAbsPath = ReportExporter.getRootPath() + reportRelPath;

            JasperPrint jasperPrint = JasperFillManager.fillReport(reportAbsPath, reportParams, connection);

            // ================= Prepare Response Output Stream ================
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

            if (PDF_REPORT_FORMAT.equals(reportFormat)) {

                httpServletResponse.setContentType("application/pdf");
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + exReportName + ".pdf");

                JRPdfExporter pdfExporter = new JRPdfExporter();
                pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(servletOutputStream));
                SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
                configuration.setPermissions(PdfWriter.ALLOW_COPY | PdfWriter.ALLOW_PRINTING);
                pdfExporter.setConfiguration(configuration);

                pdfExporter.exportReport();

            } else if (XLS_REPORT_FORMAT.equals(reportFormat)) {
                httpServletResponse.setContentType("application/xls");
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + exReportName + ".xls");

//                JRXlsExporter jrXlsExporter = new JRXlsExporter();
//                jrXlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//                jrXlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(servletOutputStream));
//                SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
//                configuration.setDetectCellType(Boolean.TRUE);
//                configuration.setCollapseRowSpan(Boolean.TRUE);
//                configuration.setIgnoreGraphics(Boolean.FALSE);
//                configuration.setRemoveEmptySpaceBetweenRows(Boolean.FALSE);
//                configuration.setRemoveEmptySpaceBetweenColumns(Boolean.FALSE);
//                configuration.setIgnorePageMargins(Boolean.FALSE);
//                configuration.setSheetDirection(RunDirectionEnum.RTL);
//                jrXlsExporter.setConfiguration(configuration);
//                jrXlsExporter.exportReport();
                JRXlsxExporter xlsExporter = new JRXlsxExporter();

                xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(servletOutputStream));

                xlsExporter.exportReport();

            } else if (DOC_REPORT_FORMAT.equals(reportFormat)) {
                httpServletResponse.setContentType("application/rtf");
                httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + exReportName + ".rtf");

                JRRtfExporter rRtfExporter = new JRRtfExporter();
                rRtfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                rRtfExporter.setExporterOutput(new SimpleWriterExporterOutput(servletOutputStream));
                rRtfExporter.exportReport();

            }

            FacesContext.getCurrentInstance().responseComplete();

            connection.close();

        } catch (NamingException | SQLException | JMRuntimeException | IOException ex) {
            Logger.getLogger(ReportExporter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReportExporter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    static public String getRootPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
    }
}
