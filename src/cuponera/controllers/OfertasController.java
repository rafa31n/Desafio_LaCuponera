package cuponera.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cuponera.beans.Ofertas;
import cuponera.beans.Usuarios;
import cuponera.models.CargoUsuariosModel;
import cuponera.models.OfertasModel;
import cuponera.models.UsuariosModel;

@WebServlet(name = "OfertasController", urlPatterns = { "/ofertas.do" })
public class OfertasController extends HttpServlet {
	ArrayList<String> listaErrores = new ArrayList<>();
	OfertasModel modelo = new OfertasModel();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("op") == null) {
				listarAprobadas(request, response);
				return;
			}
			String operacion = request.getParameter("op");

			switch (operacion) {
			case "listarEspera":
				listarEspera(request, response);
				break;
			case "listarAprobadas":
				listarAprobadas(request, response);
				break;
			case "listarActivas":
				listarActivas(request, response);
				break;
			case "listarPasadas":
				listarPasadas(request, response);
				break;
			case "listarRechazadas":
				listarRechazadas(request, response);
				break;
			case "listarDescartadas":
				listarDescartadas(request, response);
				break;
			case "nuevo":
				request.getRequestDispatcher("/ofertas/nuevaOferta.jsp").forward(request, response);
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
			case "descartar":
				descartar(request, response);
				break;
			case "listarEsperaAdmin":
				listarEsperaAdmin(request, response);
				break;
			case "listarAprobadasAdmin":
				listarAprobadasAdmin(request, response);
				break;
			case "listarActivasAdmin":
				listarActivasAdmin(request, response);
				break;
			case "listarPasadasAdmin":
				listarPasadasAdmin(request, response);
				break;
			case "listarRechazadasAdmin":
				listarRechazadasAdmin(request, response);
				break;
			case "listarDescartadasAdmin":
				listarDescartadasAdmin(request, response);
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

	private void listarEspera(HttpServletRequest request, HttpServletResponse response) {
		String codigo = request.getParameter("empresaId");
		try {
			request.setAttribute("listaOfertasEspera", modelo.listarOfertasEspera(codigo));
			request.getRequestDispatcher("/ofertas/listaOfertasEspera.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void listarAprobadas(HttpServletRequest request, HttpServletResponse response) {
		String codigo = request.getParameter("empresaId");
		try {
			request.setAttribute("listaOfertasAprobadas", modelo.listarOfertasAprobadas(codigo));
			request.getRequestDispatcher("/ofertas/listaOfertasAprobadas.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void listarActivas(HttpServletRequest request, HttpServletResponse response) {
		String codigo = request.getParameter("empresaId");
		try {
			request.setAttribute("listaOfertasActivas", modelo.listarOfertasActivas(codigo));
			request.getRequestDispatcher("/ofertas/listaOfertasActivas.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void listarPasadas(HttpServletRequest request, HttpServletResponse response) {
		String codigo = request.getParameter("empresaId");
		try {
			request.setAttribute("listaOfertasPasadas", modelo.listarOfertasPasadas(codigo));
			request.getRequestDispatcher("/ofertas/listaOfertasPasadas.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void listarRechazadas(HttpServletRequest request, HttpServletResponse response) {
		String codigo = request.getParameter("empresaId");
		try {
			request.setAttribute("listaOfertasRechazadas", modelo.listarOfertasRechazadas(codigo));
			request.getRequestDispatcher("/ofertas/listaOfertasRechazadas.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void listarDescartadas(HttpServletRequest request, HttpServletResponse response) {
		String codigo = request.getParameter("empresaId");
		try {
			request.setAttribute("listaOfertasDescartadas", modelo.listarOfertasDescartadas(codigo));
			request.getRequestDispatcher("/ofertas/listaOfertasDescartadas.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/*
	 * 
	 * LISTAR OFERTAS PARA ADMIN
	 * 
	 */

	private void listarEsperaAdmin(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaOfertasEspera", modelo.listarOfertasEsperaAdmin());
			request.getRequestDispatcher("/ofertas/listaOfertasEspera.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void listarAprobadasAdmin(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaOfertasAprobadas", modelo.listarOfertasAprobadasAdmin());
			request.getRequestDispatcher("/ofertas/listaOfertasAprobadas.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void listarActivasAdmin(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaOfertasActivas", modelo.listarOfertasActivasAdmin());
			request.getRequestDispatcher("/ofertas/listaOfertasActivas.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void listarPasadasAdmin(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaOfertasPasadas", modelo.listarOfertasPasadasAdmin());
			request.getRequestDispatcher("/ofertas/listaOfertasPasadas.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void listarRechazadasAdmin(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.setAttribute("listaOfertasRechazadas", modelo.listarOfertasRechazadasAdmin());
			request.getRequestDispatcher("/ofertas/listaOfertasRechazadas.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void listarDescartadasAdmin(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaOfertasDescartadas", modelo.listarOfertasDescartadasAdmin());
			request.getRequestDispatcher("/ofertas/listaOfertasDescartadas.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			Ofertas miOferta = new Ofertas();

			if (listaErrores.size() > 0) {
				request.setAttribute("oferta", miOferta);
				request.setAttribute("listaErrores", listaErrores);
				request.getRequestDispatcher("ofertas.do?op=nuevo").forward(request, response);
			} else {
				miOferta.setIdEstadoOferta(1);
				miOferta.setIdEmpresa(Integer.parseInt(request.getParameter("idEmpresa")));
				// miOferta.setIdAdministradorEmpresa(1);
				miOferta.setTitulo(request.getParameter("titulo"));
				miOferta.setPrecio_regular(Double.parseDouble(request.getParameter("precio_regular")));
				miOferta.setPrecio_oferta(Double.parseDouble(request.getParameter("precio_oferta")));
				miOferta.setFecha_inicio(request.getParameter("fecha_inicio"));
				miOferta.setFecha_fin(request.getParameter("fecha_fin"));
				miOferta.setFecha_limite(request.getParameter("fecha_limite"));
				miOferta.setLimite_cupones(Integer.parseInt(request.getParameter("limite")));
				miOferta.setDescripcion(request.getParameter("descripcion"));
				miOferta.setCupones_disponibles(Integer.parseInt(request.getParameter("cupones_disponibles")));

				HttpSession sesion = request.getSession(false);
				int empresa = (Integer) sesion.getAttribute("id_empresa");

				if (modelo.insertarOferta(miOferta) > 0) {
					request.getSession().setAttribute("exito", "Oferta registrada exitosamente");
					response.sendRedirect(
							request.getContextPath() + "/ofertas.do?op=listarEspera&empresaId=" + empresa);
				} else {
					request.getSession().setAttribute("fracaso", "Ya existe una oferta con estos datos.");
					response.sendRedirect(
							request.getContextPath() + "/ofertas.do?op=listarEspera&empresaId=" + empresa);
				}
			}
		} catch (ServletException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			Ofertas miOferta = modelo.obtenerOferta(codigo);
			if (miOferta != null) {
				request.setAttribute("oferta", miOferta);
				request.getRequestDispatcher("/ofertas/editarOferta.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (SQLException | IOException | ServletException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			Ofertas miOferta = new Ofertas();
			miOferta.setIdEstadoOferta(1);
			miOferta.setIdEmpresa(1);
			miOferta.setIdAdministradorEmpresa(1);

			miOferta.setIdOferta(Integer.parseInt(request.getParameter("id")));
			miOferta.setTitulo(request.getParameter("titulo"));
			miOferta.setPrecio_regular(Double.parseDouble(request.getParameter("precio_regular")));
			miOferta.setPrecio_oferta(Double.parseDouble(request.getParameter("precio_oferta")));
			miOferta.setFecha_inicio(request.getParameter("fecha_inicio"));
			miOferta.setFecha_fin(request.getParameter("fecha_fin"));
			miOferta.setFecha_limite(request.getParameter("fecha_limite"));
			miOferta.setLimite_cupones(Integer.parseInt(request.getParameter("limite")));
			miOferta.setDescripcion(request.getParameter("descripcion"));
			miOferta.setCupones_disponibles(Integer.parseInt(request.getParameter("cupones_disponibles")));

			if (listaErrores.size() > 0) {
				request.setAttribute("listaErrores", listaErrores);
				request.setAttribute("oferta", miOferta);
				request.getRequestDispatcher("ofertas.do?op=obtener").forward(request, response);
			} else {
				if (modelo.modificarOferta(miOferta) > 0) {
					request.getSession().setAttribute("exito", "Oferta modificada exitosamente");
					response.sendRedirect(request.getContextPath() + "/ofertas.do?op=listarEspera");
				} else {
					request.getSession().setAttribute("fracaso", "La oferta no se ha sido modificado.");
					response.sendRedirect(request.getContextPath() + "/ofertas.do?op=listarEspera");
				}
			}
		} catch (IOException | SQLException | ServletException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			if (modelo.eliminarOferta(codigo) > 0) {
				request.setAttribute("exito", "Oferta eliminado exitosamente");
			} else {
				request.setAttribute("fracaso", "No se puede eliminar esta oferta");
			}
			request.getRequestDispatcher("/ofertas.do?op=listarEspera").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void descartar(HttpServletRequest request, HttpServletResponse response) {
		try {
			String codigo = request.getParameter("id");
			if (modelo.descartarOferta(codigo) > 0) {
				request.setAttribute("exito", "Oferta descartada exitosamente");
			} else {
				request.setAttribute("fracaso", "No se puede descartada esta oferta");
			}
			request.getRequestDispatcher("/ofertas.do?op=listarDescartadas").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(OfertasController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}