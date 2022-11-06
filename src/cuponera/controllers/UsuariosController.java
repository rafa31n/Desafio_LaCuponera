package cuponera.controllers;

import java.io.File;
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

import cuponera.beans.Usuarios;
import cuponera.models.CargoUsuariosModel;
import cuponera.models.UsuariosModel;

@WebServlet(name = "UsuariosController", urlPatterns = { "/usuarios.do" })
public class UsuariosController extends HttpServlet {
	ArrayList<String> listaErrores = new ArrayList<>();
	UsuariosModel modelo = new UsuariosModel();
	CargoUsuariosModel cargo = new CargoUsuariosModel();

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
			case "listarClientes":
				listarClientes(request, response);
				break;
			case "listarDependientes":
				listarDependientes(request, response);
				break;
			case "mostrarCupones":
				mostrarCupones(request, response);
				break;
			case "nuevo":
				nuevo(request, response);
				break;
			case "nuevoDependiente":
				nuevoDependiente(request, response);
				break;
			case "insertar":
				insertar(request, response);
				break;
			case "insertarDependiente":
				insertarDependiente(request, response);
				break;
			case "obtener":
				obtener(request, response);
				break;
			case "obtenerDependiente":
				obtenerDependiente(request, response);
				break;
			case "modificar":
				modificar(request, response);
				break;
			case "modificarDependiente":
				modificarDependiente(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
				break;
			case "eliminarD":
				eliminarD(request, response);
				break;
			case "formContra":
				request.getRequestDispatcher("/usuarios/formContra.jsp").forward(request, response);
				break;
			case "cambiarContra":
				cambiarContra(request, response);
				break;
			case "verMisCupones":
				verMisCupones(request, response);
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
			request.setAttribute("listaUsuarios", modelo.listarUsuarios());
			request.getRequestDispatcher("/usuarios/listaUsuarios.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void listarClientes(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaClientes", modelo.listarClientes());
			request.getRequestDispatcher("/clientes/listaClientes.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void listarDependientes(HttpServletRequest request, HttpServletResponse response) {
		String codigo = request.getParameter("empresaId");
		try {
			request.setAttribute("listaDependientes", modelo.listarDependientes(codigo));
			request.getRequestDispatcher("/usuarios/listaDependientes.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaCargos", cargo.listarCargosUsuarios());
			request.getRequestDispatcher("/usuarios/nuevoUsuario.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void nuevoDependiente(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaCargos", cargo.listarCargosUsuarios());
			request.getRequestDispatcher("/usuarios/nuevoDependiente.jsp").forward(request, response);
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
		try {
			listaErrores.clear();
			Usuarios miUsuario = new Usuarios();

			if (listaErrores.size() > 0) {
				request.setAttribute("usuario", miUsuario);
				request.setAttribute("listaErrores", listaErrores);
				request.getRequestDispatcher("usuarios.do?op=nuevo").forward(request, response);
			} else {
				miUsuario.setIdCargo(Integer.parseInt(request.getParameter("cargo")));
				miUsuario.setNombre(request.getParameter("nombres"));
				miUsuario.setApellidos(request.getParameter("apellidos"));
				miUsuario.setTelefono(request.getParameter("telefono"));
				miUsuario.setCorreo(request.getParameter("correo"));
				miUsuario.setPassword(request.getParameter("password"));
				miUsuario.setDireccion(request.getParameter("direccion"));
				miUsuario.setDui(request.getParameter("dui"));

				if (modelo.insertarUsuario(miUsuario) > 0) {
					request.getSession().setAttribute("exito", "Usuario registrado exitosamente");
					response.sendRedirect(request.getContextPath() + "/usuarios.do?op=listar");
				} else {
					request.getSession().setAttribute("fracaso", "Ya existe un usuario con estos datos.");
					response.sendRedirect(request.getContextPath() + "/usuarios.do?op=listar");
				}
			}
		} catch (ServletException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void insertarDependiente(HttpServletRequest request, HttpServletResponse response) {
		String contraAleatoria = generateRandomPassword(8);
		HttpSession sesion = request.getSession(false);
		int empresa = (Integer) sesion.getAttribute("id_empresa");
		try {
			listaErrores.clear();
			Usuarios miUsuario = new Usuarios();

			if (listaErrores.size() > 0) {
				request.setAttribute("usuario", miUsuario);
				request.setAttribute("listaErrores", listaErrores);
				request.getRequestDispatcher("usuarios.do?op=nuevoDependiente").forward(request, response);
			} else {
				miUsuario.setNombre(request.getParameter("nombres"));
				miUsuario.setApellidos(request.getParameter("apellidos"));
				miUsuario.setTelefono(request.getParameter("telefono"));
				miUsuario.setCorreo(request.getParameter("correo"));
				miUsuario.setDireccion(request.getParameter("direccion"));
				miUsuario.setDui(request.getParameter("dui"));
				miUsuario.setPassword(contraAleatoria);
				miUsuario.setIdEmpresa(Integer.parseInt(request.getParameter("id_empresa")));

				String correoDependiente = miUsuario.getCorreo();
				String asunto = "Contraseña cuenta dependiente de sucursal - La Cuponera";
				String cuerpo = "Contraseña: " + miUsuario.getPassword();

				if (modelo.insertarDependiente(miUsuario) > 0) {
					enviarConGMail(correoDependiente, asunto, cuerpo);
					request.getSession().setAttribute("exito", "Usuario registrado exitosamente");
					response.sendRedirect(
							request.getContextPath() + "/usuarios.do?op=listarDependientes&empresaId=" + empresa);
				} else {
					request.getSession().setAttribute("fracaso", "Ya existe un usuario con estos datos.");
					response.sendRedirect(
							request.getContextPath() + "/usuarios.do?op=listarDependientes&empresaId=" + empresa);
				}
			}
		} catch (ServletException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
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
			Usuarios miUsuario = modelo.obtenerUsuario(codigo);
			if (miUsuario != null) {
				request.setAttribute("usuario", miUsuario);
				request.setAttribute("listaCargos", cargo.listarCargosUsuarios());
				request.getRequestDispatcher("/usuarios/editarUsuario.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (SQLException | IOException | ServletException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void obtenerDependiente(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			Usuarios miUsuario = modelo.obtenerDependiente(codigo);
			if (miUsuario != null) {
				request.setAttribute("usuario", miUsuario);
				request.getRequestDispatcher("/usuarios/editarDependiente.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (SQLException | IOException | ServletException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void mostrarCupones(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			request.setAttribute("listaCuponesDisponibles", modelo.obtenerCuponDisponible(codigo));
			request.setAttribute("listaCuponesCanjeados", modelo.obtenerCuponCanjeado(codigo));
			request.setAttribute("listaCuponesVencidos", modelo.obtenerCuponVencido(codigo));
			request.getRequestDispatcher("/clientes/cuponesClientes.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void verMisCupones(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			request.setAttribute("listaCuponesDisponibles", modelo.obtenerCuponDisponible(codigo));
			request.setAttribute("listaCuponesCanjeados", modelo.obtenerCuponCanjeado(codigo));
			request.setAttribute("listaCuponesVencidos", modelo.obtenerCuponVencido(codigo));
			request.getRequestDispatcher("/clientes/verMisCupones.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			Usuarios miUsuario = new Usuarios();
			miUsuario.setIdUsuario(Integer.parseInt(request.getParameter("id")));
			miUsuario.setIdCargo(Integer.parseInt(request.getParameter("cargo")));
			miUsuario.setNombre(request.getParameter("nombres"));
			miUsuario.setApellidos(request.getParameter("apellidos"));
			miUsuario.setTelefono(request.getParameter("telefono"));
			miUsuario.setCorreo(request.getParameter("correo"));
			miUsuario.setPassword(request.getParameter("password"));
			miUsuario.setDireccion(request.getParameter("direccion"));
			miUsuario.setDui(request.getParameter("dui"));

			if (listaErrores.size() > 0) {
				request.setAttribute("listaErrores", listaErrores);
				request.setAttribute("usuario", miUsuario);
				request.getRequestDispatcher("usuarios.do?op=obtener").forward(request, response);
			} else {
				if (modelo.modificarUsuario(miUsuario) > 0) {
					request.getSession().setAttribute("exito", "Usuario modificado exitosamente");
					response.sendRedirect(request.getContextPath() + "/usuarios.do?op=listar");
				} else {
					request.getSession().setAttribute("fracaso", "El usuario no ha sido modificado.");
					response.sendRedirect(request.getContextPath() + "/usuarios.do?op=listar");
				}
			}
		} catch (IOException | SQLException | ServletException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void modificarDependiente(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			Usuarios miUsuario = new Usuarios();
			miUsuario.setIdUsuario(Integer.parseInt(request.getParameter("id")));
			miUsuario.setNombre(request.getParameter("nombres"));
			miUsuario.setApellidos(request.getParameter("apellidos"));
			miUsuario.setTelefono(request.getParameter("telefono"));
			miUsuario.setCorreo(request.getParameter("correo"));
			miUsuario.setPassword(request.getParameter("password"));
			miUsuario.setDireccion(request.getParameter("direccion"));
			miUsuario.setDui(request.getParameter("dui"));

			if (listaErrores.size() > 0) {
				request.setAttribute("listaErrores", listaErrores);
				request.setAttribute("usuario", miUsuario);
				request.getRequestDispatcher("usuarios.do?op=obtenerDependiente").forward(request, response);
			} else {
				if (modelo.modificarUsuarioDependiente(miUsuario) > 0) {
					request.getSession().setAttribute("exito", "Usuario modificado exitosamente");
					response.sendRedirect(request.getContextPath() + "/usuarios.do?op=listarDependientes");
				} else {
					request.getSession().setAttribute("fracaso", "El usuario no ha sido modificado.");
					response.sendRedirect(request.getContextPath() + "/usuarios.do?op=listarDependientes");
				}
			}
		} catch (IOException | SQLException | ServletException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			if (modelo.eliminarUsuario(codigo) > 0) {
				request.setAttribute("exito", "Usuario eliminado exitosamente");

			} else {
				request.setAttribute("fracaso", "No se puede eliminar este usuario");
			}
			request.getRequestDispatcher("/usuarios.do?op=listar").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void eliminarD(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			if (modelo.eliminarUsuario(codigo) > 0) {
				request.setAttribute("exito", "Usuario eliminado exitosamente");

			} else {
				request.setAttribute("fracaso", "No se puede eliminar este usuario");
			}
			request.getRequestDispatcher("/usuarios.do?op=listarDependientes").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void cambiarContra(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			Usuarios miUsuario = new Usuarios();
			miUsuario.setPassword(request.getParameter("passwordA"));
			miUsuario.setPasswordN(request.getParameter("passwordN"));
			miUsuario.setPasswordN2(request.getParameter("passwordN2"));
			miUsuario.setIdUsuario(Integer.parseInt(request.getParameter("usuario")));

			if (listaErrores.size() > 0) {
				request.setAttribute("listaErrores", listaErrores);
				request.setAttribute("usuario", miUsuario);
				request.getRequestDispatcher("usuarios.do?op=formContra").forward(request, response);
			} else {
				if (modelo.cambiarContra(miUsuario) > 0) {
					request.getSession().setAttribute("exito", "Contraseña modificado exitosamente");
					response.sendRedirect(request.getContextPath() + "/usuarios.do?op=formContra");
				} else {
					request.getSession().setAttribute("fracaso", "La Contraseña no ha sido modificada.");
					response.sendRedirect(request.getContextPath() + "/usuarios.do?op=formContra");
				}
			}
		} catch (IOException | SQLException | ServletException ex) {
			Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}