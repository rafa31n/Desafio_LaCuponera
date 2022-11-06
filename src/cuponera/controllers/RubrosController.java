package cuponera.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cuponera.beans.Rubros;
import cuponera.beans.Usuarios;
import cuponera.models.RubrosModel;

@WebServlet(name = "RubrosController", urlPatterns = { "/rubros.do" })
public class RubrosController extends HttpServlet {
	ArrayList<String> listaErrores = new ArrayList<>();
	RubrosModel modelo = new RubrosModel();

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
			request.setAttribute("listaRubros", modelo.listarRubros());
			request.getRequestDispatcher("/rubros/listaRubro.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private void nuevo(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("listaRubros", modelo.listarRubros());
			request.getRequestDispatcher("/rubros/nuevoRubro.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			Rubros rubros = new Rubros();

			if (listaErrores.size() > 0) {
				request.setAttribute("rubros", rubros);
				request.setAttribute("listaErrores", listaErrores);
				request.getRequestDispatcher("rubros.do?op=nuevo").forward(request, response);
			} else {
				rubros.setNombreRubro(request.getParameter("nombre"));

				if (modelo.insertarRubros(rubros) > 0) {
					request.getSession().setAttribute("exito", "Rubro registrado exitosamente");
					response.sendRedirect(request.getContextPath() + "/rubros.do?op=listar");
				} else {
					request.getSession().setAttribute("fracaso", "Ya existe un rubro con ese nombre.");
					response.sendRedirect(request.getContextPath() + "/rubros.do?op=listar");
				}
			}
		} catch (ServletException ex) {
			Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			Rubros rubros = modelo.obtenerRubros(id);
			if (rubros != null) {
				request.setAttribute("rubros", rubros);
				request.setAttribute("listaRubros", modelo.listarRubros());
				request.getRequestDispatcher("/rubros/editarRubro.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (SQLException | IOException | ServletException ex) {
			Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {
			listaErrores.clear();
			Rubros rubros = new Rubros();
			rubros.setIdRubro(Integer.parseInt(request.getParameter("id")));
			rubros.setNombreRubro(request.getParameter("nombre"));

			if (listaErrores.size() > 0) {
				request.setAttribute("listaErrores", listaErrores);
				request.setAttribute("rubros", rubros);
				request.getRequestDispatcher("rubros.do?op=obtener").forward(request, response);
			} else {
				if (modelo.modificarRubros(rubros) > 0) {
					request.getSession().setAttribute("exito", "Rubro modificado exitosamente");
					response.sendRedirect(request.getContextPath() + "/rubros.do?op=listar");
				} else {
					request.getSession().setAttribute("fracaso", "El rubro no ha sido modificado.");
					response.sendRedirect(request.getContextPath() + "/rubros.do?op=listar");
				}
			}
		} catch (IOException | SQLException | ServletException ex) {
			Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			if (modelo.eliminarRubros(id) > 0) {
				request.setAttribute("exito", "Rubro eliminado exitosamente");

			} else {
				request.setAttribute("fracaso", "No se puede eliminar este rubro");
			}
			request.getRequestDispatcher("/rubros.do?op=listar").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			Logger.getLogger(RubrosController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}