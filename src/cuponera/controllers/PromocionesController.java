package cuponera.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import cuponera.beans.CuponesBeans;
import cuponera.beans.PromocionesBeans;
import cuponera.models.PromocionesModel;

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

/**
 * Servlet implementation class PromocionesController
 */
@WebServlet(name = "PromocionesController", urlPatterns = { "/promociones.do" })
public class PromocionesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PromocionesModel promocionModel = new PromocionesModel();

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
				request.setAttribute("id", request.getParameter("id"));
				request.setAttribute("codigoEmpresa", request.getParameter("codigoEmpresa"));
				request.getRequestDispatcher("/promociones/comprarCupon.jsp").forward(request, response);
				break;
			case "comprar":
				update(request, response);
				break;
			case "listarPromociones":
				listarPromociones(request, response);
				break;
			case "aprobar":
				aprobar(request, response, request.getParameter("id"));
				break;
			case "rechazar":
				rechazar(request, response, request.getParameter("id"), request.getParameter("rechazo"));
				// System.out.println(request.getParameter("rechazo"));
				break;
			case "validarCompra":
				validarCompra(request, response);
				// System.out.println(request.getParameter("rechazo"));
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

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaPromociones", promocionModel.listaPromociones());
			request.getRequestDispatcher("/promociones/listaPromociones.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(CuponesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void listarPromociones(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaPromociones", promocionModel.listaEspera());
			request.getRequestDispatcher("/promociones/aprobarPromocion.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(CuponesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			String cupon = request.getParameter("cupon");
			String dui = request.getParameter("dui_usuario");
			int idUser = Integer.parseInt(request.getParameter("idUser"));
			String codigoEmpresa = request.getParameter("codigoEmpresa");
			String correoUsuario = request.getParameter("correoUsuario");
			String asunto = "Confirmar compra - La Cuponera";
			String link = "http://localhost:8080/Desafio_LaCuponera/clientes/confirmarCompra.jsp";
			String cuerpo = "Ingresa a este link para verificar tu compra: " + link;

			if (promocionModel.updateOfertas(id, cupon, dui, idUser, codigoEmpresa) == true) {
				enviarConGMail(correoUsuario, asunto, cuerpo);
				request.getSession().setAttribute("exito", "Cupon comprado exitosamente");
				response.sendRedirect(request.getContextPath() + "/usuarios.do?op=verMisCupones&id=" + idUser);
			}

		} catch (Exception ex) {
			Logger.getLogger(PromocionesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void aprobar(HttpServletRequest request, HttpServletResponse response, String id) {
		try {

			if (promocionModel.AceptarPromocion(id) == true) {
				request.getSession().setAttribute("exito", "Promoción aprobada");
				response.sendRedirect(request.getContextPath() + "/ofertas.do?op=listarActivasAdmin");
			}

		} catch (Exception ex) {
			Logger.getLogger(PromocionesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void rechazar(HttpServletRequest request, HttpServletResponse response, String id, String justificacion) {
		try {
			if (promocionModel.RechazarPromocion(id, justificacion) == true) {
				request.getSession().setAttribute("exito", "Promocion rechazada");
				response.sendRedirect(request.getContextPath() + "/ofertas.do?op=listarRechazadasAdmin");
			}

		} catch (Exception ex) {
			Logger.getLogger(PromocionesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
		String remitente = "cuponera.elsalvador@gmail.com";
		String key = "lzdyslfbtjtinesd";

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", "Jak3TheDog");
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

	private void validarCompra(HttpServletRequest request, HttpServletResponse response) {
		try {
			String cupon = request.getParameter("codigoCupon");

			if (promocionModel.validarCompra(cupon) == true) {
				request.getSession().setAttribute("exito", "Su compra ha sido confirmada con exito");
				response.sendRedirect(request.getContextPath() + "/clientes/confirmarCompra.jsp");
			} else {
				request.getSession().setAttribute("fracaso", "Su compra no se ha podido validar.");
				response.sendRedirect(request.getContextPath() + "/clientes/confirmarCompra.jsp");
			}

		} catch (Exception ex) {
			Logger.getLogger(PromocionesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
