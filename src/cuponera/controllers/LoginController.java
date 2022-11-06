package cuponera.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javax.servlet.http.HttpSession;

import cuponera.beans.Empresa;
import cuponera.beans.Usuarios;
import cuponera.models.EmpresasModel;
import cuponera.models.LoginModel;
import cuponera.models.UsuariosModel;

@WebServlet(name = "LoginController", urlPatterns = { "/login.do" })

public class LoginController extends HttpServlet {

	ArrayList<String> listaErrores = new ArrayList<>();
	LoginModel modelo = new LoginModel();
	EmpresasModel modeloE = new EmpresasModel();
	UsuariosModel user = new UsuariosModel();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			String operacion = request.getParameter("op");

			switch (operacion) {
			case "iniciarSesion":
				iniciarSesion(request, response);
				break;
			case "cerrarSesion":
				cerrarSesion(request, response);
				break;
			case "registrarse":
				registrarse(request, response);
				break;
			case "verificar":
				verificar(request, response);
				break;
			case "contraOlvidada":
				contraOlvidada(request, response);
				break;
			}
		}
	}

	private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			if (email.isEmpty() || password.isEmpty()) {
				listaErrores.add("Completa todos los campos");
				request.setAttribute("listaErrores", listaErrores);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

			if (modelo.iniciarSesion(email, password) != null) {
				Usuarios usuario = modelo.iniciarSesion(email, password);

				HttpSession sesion = request.getSession(true);
				sesion.setAttribute("autenticado", true);
				sesion.setAttribute("id_usuario", usuario.getIdUsuario());
				sesion.setAttribute("id_cargo", usuario.getIdCargo());
				sesion.setAttribute("nombre", usuario.getNombre());
				sesion.setAttribute("apellidos", usuario.getApellidos());
				sesion.setAttribute("telefono", usuario.getTelefono());
				sesion.setAttribute("correo", usuario.getCorreo());
				sesion.setAttribute("direccion", usuario.getDireccion());
				sesion.setAttribute("dui", usuario.getDui());
				sesion.setAttribute("password", usuario.getPassword());
				sesion.setAttribute("id_empresa", usuario.getIdEmpresa());

				response.sendRedirect(request.getContextPath() + "/home.jsp");
			} else {
				listaErrores.add("Correo o contraseÃ±a incorrecta");
				request.setAttribute("listaErrores", listaErrores);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (ServletException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession sesion = request.getSession(true);
			sesion.setAttribute("autenticado", null);
			sesion.setAttribute("id_usuario", null);
			sesion.setAttribute("id_cargo", null);
			sesion.setAttribute("nombre", null);
			sesion.setAttribute("apellidos", null);
			sesion.setAttribute("telefono", null);
			sesion.setAttribute("correo", null);
			sesion.setAttribute("direccion", null);
			sesion.setAttribute("dui", null);
			sesion.setAttribute("password", null);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String generateRandomToken(int len) {
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < len; i++) {
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}

		return sb.toString();
	}

	private void registrarse(HttpServletRequest request, HttpServletResponse response) {
		String token = generateRandomToken(8);
		try {
			listaErrores.clear();
			Usuarios miUsuario = new Usuarios();

			if (listaErrores.size() > 0) {
				request.setAttribute("usuario", miUsuario);
				request.setAttribute("listaErrores", listaErrores);
				response.sendRedirect(request.getContextPath() + "/registrarse.jsp");
			} else {
				miUsuario.setIdCargo(3);
				miUsuario.setNombre(request.getParameter("nombres"));
				miUsuario.setApellidos(request.getParameter("apellidos"));
				miUsuario.setTelefono(request.getParameter("telefono"));
				miUsuario.setCorreo(request.getParameter("correo"));
				miUsuario.setPassword(request.getParameter("password"));
				miUsuario.setDireccion(request.getParameter("direccion"));
				miUsuario.setDui(request.getParameter("dui"));
				miUsuario.setToken(token);

				String correoCliente = miUsuario.getCorreo();
				String asunto = "Verificar cuenta cliente - La Cuponera";
				String link = "http://localhost:8080/Desafio_LaCuponera/verificar.jsp";
				String cuerpo = "Ingresa a este link para verificar tu contraseña: " + link + "\nToken: "
						+ miUsuario.getToken();

				if (user.registrarCliente(miUsuario) > 0) {
					enviarConGMail(correoCliente, asunto, cuerpo);
					request.getSession().setAttribute("exito",
							"Cuenta creada exitosamente. Revisa tu correo para verificar tu cuenta.");
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				} else {
					request.getSession().setAttribute("fracaso", "No se puede crear una cuenta con estos datos.");
					response.sendRedirect(request.getContextPath() + "/registrarse.jsp");
				}
			}
		} catch (IOException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
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

	private void verificar(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			String email = request.getParameter("correo");
			String token = request.getParameter("token");

			if (modelo.verificarToken(email, token) > 0) {
				request.getSession().setAttribute("exito", "Cuenta verificada");
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			} else {
				request.getSession().setAttribute("fracaso", "No se pudo verificar tu cuenta, revisa el token");
				response.sendRedirect(request.getContextPath() + "/verificar.jsp");
			}
		} catch (IOException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void contraOlvidada(HttpServletRequest request, HttpServletResponse response) {
		String contraAleatoria = generateRandomToken(8);
		try {
			listaErrores.clear();

			String email = request.getParameter("correo");
			String asunto = "Contraseña olvidada - La Cuponera";
			String cuerpo = "Contraseña aleatoria: " + contraAleatoria;

			if (modelo.cambiarContraOlvidada(email, contraAleatoria) > 0) {
				enviarConGMail(email, asunto, cuerpo);
				request.getSession().setAttribute("exito",
						"Contraseña temporal generada correctamente. Cambia tu contraseña al iniciar sesión.");
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			} else {
				request.getSession().setAttribute("fracaso",
						"No se pudo verificar tu cuenta, revisa el correo que ingresaste.");
				response.sendRedirect(request.getContextPath() + "/verificar.jsp");
			}
		} catch (IOException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
}