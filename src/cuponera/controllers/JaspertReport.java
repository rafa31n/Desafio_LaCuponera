package cuponera.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "JaspertReport", value = "/JaspertReport")
public class JaspertReport extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conexion = null;
		try {
			Map parameters = new HashMap();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename=\"Cupon.pdf\";");
			ServletOutputStream out = response.getOutputStream();
			String pathReporte;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/cuponera_dwf", "root", "");
			} catch (ClassNotFoundException e1) {
			}
			String parametro = "";
			parametro = request.getParameter("codigo_cupon");
			parameters.put("codigo_cupon", parametro);
			pathReporte = "C:\\Users\\rafae\\Documents\\eclipse-workspace\\Desafio_LaCuponera\\src\\cuponera\\controllers\\Cupones.jasper";
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(pathReporte);
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, conexion);
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
			exporter.exportReport();
		} catch (SQLException | JRException ex) {
			Logger.getLogger(JaspertReport.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				conexion.close();
			} catch (SQLException ex) {
				Logger.getLogger(JaspertReport.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
