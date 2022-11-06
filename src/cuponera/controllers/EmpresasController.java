package cuponera.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;

import cuponera.beans.Empresa;
import cuponera.models.EmpresasModel;
import cuponera.models.RubrosModel;

@WebServlet(name = "EmpresasController", urlPatterns = { "/empresas.do" })
public class EmpresasController extends HttpServlet {
	ArrayList<String> listaErrores = new ArrayList<>();
	EmpresasModel modelo = new EmpresasModel();
	RubrosModel rubro = new RubrosModel();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("op") == null) {
				listar(request, response);
				return;
			}
			String operacion = request.getParameter("op");

			switch (operacion) {
			case "listar":
				listar(request, response);
				break;
			case "nuevo":
				nuevo(request, response);
				break;
			case "insertar":
				insertar(request, response);
				break;
			case "obtener":
				obtener(request, response);
				break;
			case "modificar":
				modificar(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
				break;
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	public String getServletInfo() {
		return "Short description";
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaEmpresas", modelo.listarEmpresas());
			request.getRequestDispatcher("/empresas/listaEmpresa.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaRubros", rubro.listarRubros());
			request.getRequestDispatcher("/empresas/nuevaEmpresa.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static String generateRandomPassword(int len) {
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < len; i++) {
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}

		return sb.toString();
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		String contraAleatoria = generateRandomPassword(8);
		try {
			listaErrores.clear();
			Empresa miEmpresa = new Empresa();

			if (listaErrores.size() > 0) {
				request.setAttribute("empresa", miEmpresa);
				request.setAttribute("listaErrores", listaErrores);
				request.getRequestDispatcher("empresas.do?op=nuevo").forward(request, response);
			} else {
				miEmpresa.setIdRubro(Integer.parseInt(request.getParameter("rubro")));
				miEmpresa.setNombreEmpresa(request.getParameter("nombreE"));
				miEmpresa.setCodigoEmpresa(request.getParameter("codigoE"));
				miEmpresa.setTelefono(request.getParameter("telefono"));
				miEmpresa.setCorreo(request.getParameter("correo"));
				miEmpresa.setPassword(contraAleatoria);
				miEmpresa.setDireccion(request.getParameter("direccion"));
				miEmpresa.setPorcentajeComision(Double.parseDouble(request.getParameter("porcentaje")));
				miEmpresa.setNombreContacto(request.getParameter("nombreContacto"));

				String correoEmpresa = miEmpresa.getCorreo();
				String asunto = "Contraseña cuenta empresa - La Cuponera";
				String cuerpo = "Contraseña: " + miEmpresa.getPassword();

				if (modelo.insertarEmpresa(miEmpresa) > 0) {
					enviarConGMail(correoEmpresa, asunto, cuerpo);
					request.getSession().setAttribute("exito", "Empresa registrada exitosamente");
					response.sendRedirect(request.getContextPath() + "/empresas.do?op=listar");
				} else {
					request.getSession().setAttribute("fracaso", "Ya existe una oferta con estos datos.");
					response.sendRedirect(request.getContextPath() + "/empresas.do?op=listar");
				}
			}
		} catch (ServletException ex) {
			Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
		String remitente = "cuponera.elsalvador@gmail.com";
		String key = "lzdyslfbtjtinesd";

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", "raf4e|2oo3");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			message.setSubject(asunto);
			message.setText(cuerpo);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, key);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			Empresa miEmpresa = modelo.obtenerEmpresa(codigo);
			if (miEmpresa != null) {
				request.setAttribute("empresa", miEmpresa);
				request.setAttribute("listaRubros", rubro.listarRubros());
				request.getRequestDispatcher("/empresas/editarEmpresa.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (SQLException | IOException | ServletException ex) {
			Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			Empresa miEmpresa = new Empresa();
			miEmpresa.setIdEmpresa(Integer.parseInt(request.getParameter("id")));
			miEmpresa.setNombreEmpresa(request.getParameter("nombreE"));
			miEmpresa.setCodigoEmpresa(request.getParameter("codigoE"));
			miEmpresa.setTelefono(request.getParameter("telefono"));
			miEmpresa.setDireccion(request.getParameter("direccion"));
			miEmpresa.setCorreo(request.getParameter("correo"));
			miEmpresa.setNombreContacto(request.getParameter("nombreContacto"));
			miEmpresa.setIdRubro(Integer.parseInt(request.getParameter("rubro")));
			miEmpresa.setPorcentajeComision(Double.parseDouble(request.getParameter("porcentaje")));
			miEmpresa.setPassword(request.getParameter("password"));

			if (listaErrores.size() > 0) {
				request.setAttribute("listaErrores", listaErrores);
				request.setAttribute("empresa", miEmpresa);
				request.getRequestDispatcher("empresas.do?op=obtener").forward(request, response);
			} else {
				if (modelo.modificarEmpresa(miEmpresa) > 0) {
					request.getSession().setAttribute("exito", "Empresa modificada exitosamente");
					response.sendRedirect(request.getContextPath() + "/empresas.do?op=listar");
				} else {
					request.getSession().setAttribute("fracaso", "La Empresa no se ha sido modificado.");
					response.sendRedirect(request.getContextPath() + "/empresas.do?op=listar");
				}
			}
		} catch (IOException | SQLException | ServletException ex) {
			Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			if (modelo.eliminarEmpresa(codigo) > 0) {
				request.setAttribute("exito", "Empresa eliminada exitosamente");
			} else {
				request.setAttribute("fracaso", "No se puede eliminar esta Empresa");
			}
			request.getRequestDispatcher("/empresas.do?op=listar").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
