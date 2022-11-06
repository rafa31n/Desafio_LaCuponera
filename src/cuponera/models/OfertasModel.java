package cuponera.models;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cuponera.beans.EstadosOfertas;
import cuponera.beans.Ofertas;

public class OfertasModel extends Conexion {

	public List<Ofertas> listarOfertasEspera(String codigo) throws SQLException {
		try {
			List<Ofertas> lista = new ArrayList<>();
			String sql = "SELECT * FROM ofertas o INNER JOIN estado_ofertas eo ON eo.id_estado_oferta = o.id_estado_oferta "
					+ "INNER JOIN empresa e ON e.id_empresa = o.id_empresa WHERE o.id_estado_oferta = 1 AND o.id_empresa = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			rs = cs.executeQuery();
			while (rs.next()) {
				Ofertas ofertas = new Ofertas();
				ofertas.setIdOferta(rs.getInt("id_oferta"));
				ofertas.setEstado_oferta(new EstadosOfertas(rs.getString("estado_oferta")));
				ofertas.setEmpresa(rs.getString("nombre_empresa"));
				ofertas.setTitulo(rs.getString("titulo_oferta"));
				ofertas.setPrecio_regular(rs.getDouble("precio_regular"));
				ofertas.setPrecio_oferta(rs.getDouble("precio_oferta"));
				ofertas.setFecha_inicio(rs.getString("fecha_inicio"));
				ofertas.setFecha_fin(rs.getString("fecha_fin"));
				ofertas.setFecha_limite(rs.getString("fecha_limite"));
				ofertas.setLimite_cupones(rs.getInt("limite_cupones"));
				ofertas.setDescripcion(rs.getString("descripcion_oferta"));
				ofertas.setCupones_vendidos(rs.getInt("cupones_vendidos"));
				ofertas.setCupones_disponibles(rs.getInt("cupones_disponibles"));
				ofertas.setIngresos_totales(rs.getDouble("ingresos_totales"));
				ofertas.setCargo_servicio(rs.getDouble("cargo_servicio"));
				lista.add(ofertas);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public List<Ofertas> listarOfertasAprobadas(String codigo) throws SQLException {
		try {
			List<Ofertas> lista = new ArrayList<>();
			LocalDate fechaActual = LocalDate.now();
			String sql = "SELECT * FROM ofertas o INNER JOIN estado_ofertas eo ON eo.id_estado_oferta = o.id_estado_oferta"
					+ " INNER JOIN empresa e ON e.id_empresa = o.id_empresa "
					+ "WHERE o.id_estado_oferta = 2 AND (o.fecha_inicio > ? AND o.fecha_fin > ?) AND o.id_empresa = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, fechaActual.toString());
			cs.setString(2, fechaActual.toString());
			cs.setString(3, codigo);
			rs = cs.executeQuery();
			while (rs.next()) {
				Ofertas ofertas = new Ofertas();
				ofertas.setIdOferta(rs.getInt("id_oferta"));
				ofertas.setEstado_oferta(new EstadosOfertas(rs.getString("estado_oferta")));
				ofertas.setEmpresa(rs.getString("nombre_empresa"));
				ofertas.setTitulo(rs.getString("titulo_oferta"));
				ofertas.setPrecio_regular(rs.getDouble("precio_regular"));
				ofertas.setPrecio_oferta(rs.getDouble("precio_oferta"));
				ofertas.setFecha_inicio(rs.getString("fecha_inicio"));
				ofertas.setFecha_fin(rs.getString("fecha_fin"));
				ofertas.setFecha_limite(rs.getString("fecha_limite"));
				ofertas.setLimite_cupones(rs.getInt("limite_cupones"));
				ofertas.setDescripcion(rs.getString("descripcion_oferta"));
				ofertas.setCupones_vendidos(rs.getInt("cupones_vendidos"));
				ofertas.setCupones_disponibles(rs.getInt("cupones_disponibles"));
				ofertas.setIngresos_totales(rs.getDouble("ingresos_totales"));
				ofertas.setCargo_servicio(rs.getDouble("cargo_servicio"));
				lista.add(ofertas);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public List<Ofertas> listarOfertasActivas(String codigo) throws SQLException {
		try {
			List<Ofertas> lista = new ArrayList<>();
			LocalDate fechaActual = LocalDate.now();
			String sql = "SELECT * FROM ofertas o INNER JOIN estado_ofertas eo ON eo.id_estado_oferta = o.id_estado_oferta "
					+ "INNER JOIN empresa e ON e.id_empresa = o.id_empresa "
					+ "WHERE o.id_estado_oferta = 2 AND (DATE(?) BETWEEN o.fecha_inicio AND o.fecha_fin) AND o.id_empresa = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, fechaActual.toString());
			cs.setString(2, codigo);
			rs = cs.executeQuery();
			while (rs.next()) {
				Ofertas ofertas = new Ofertas();
				ofertas.setIdOferta(rs.getInt("id_oferta"));
				ofertas.setEstado_oferta(new EstadosOfertas(rs.getString("estado_oferta")));
				ofertas.setEmpresa(rs.getString("nombre_empresa"));
				ofertas.setTitulo(rs.getString("titulo_oferta"));
				ofertas.setPrecio_regular(rs.getDouble("precio_regular"));
				ofertas.setPrecio_oferta(rs.getDouble("precio_oferta"));
				ofertas.setFecha_inicio(rs.getString("fecha_inicio"));
				ofertas.setFecha_fin(rs.getString("fecha_fin"));
				ofertas.setFecha_limite(rs.getString("fecha_limite"));
				ofertas.setLimite_cupones(rs.getInt("limite_cupones"));
				ofertas.setDescripcion(rs.getString("descripcion_oferta"));
				ofertas.setCupones_vendidos(rs.getInt("cupones_vendidos"));
				ofertas.setCupones_disponibles(rs.getInt("cupones_disponibles"));
				ofertas.setIngresos_totales(rs.getDouble("ingresos_totales"));
				ofertas.setCargo_servicio(rs.getDouble("cargo_servicio"));
				lista.add(ofertas);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public List<Ofertas> listarOfertasPasadas(String codigo) throws SQLException {
		try {
			List<Ofertas> lista = new ArrayList<>();
			LocalDate fechaActual = LocalDate.now();
			String sql = "SELECT * FROM ofertas o INNER JOIN estado_ofertas eo ON eo.id_estado_oferta = o.id_estado_oferta "
					+ "INNER JOIN empresa e ON e.id_empresa = o.id_empresa "
					+ "WHERE o.id_estado_oferta = 2 AND (o.fecha_inicio < ? AND o.fecha_fin < ?) AND o.id_empresa = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, fechaActual.toString());
			cs.setString(2, fechaActual.toString());
			cs.setString(3, codigo);
			rs = cs.executeQuery();
			while (rs.next()) {
				Ofertas ofertas = new Ofertas();
				ofertas.setIdOferta(rs.getInt("id_oferta"));
				ofertas.setEstado_oferta(new EstadosOfertas(rs.getString("estado_oferta")));
				ofertas.setEmpresa(rs.getString("nombre_empresa"));
				ofertas.setTitulo(rs.getString("titulo_oferta"));
				ofertas.setPrecio_regular(rs.getDouble("precio_regular"));
				ofertas.setPrecio_oferta(rs.getDouble("precio_oferta"));
				ofertas.setFecha_inicio(rs.getString("fecha_inicio"));
				ofertas.setFecha_fin(rs.getString("fecha_fin"));
				ofertas.setFecha_limite(rs.getString("fecha_limite"));
				ofertas.setLimite_cupones(rs.getInt("limite_cupones"));
				ofertas.setDescripcion(rs.getString("descripcion_oferta"));
				ofertas.setCupones_vendidos(rs.getInt("cupones_vendidos"));
				ofertas.setCupones_disponibles(rs.getInt("cupones_disponibles"));
				ofertas.setIngresos_totales(rs.getDouble("ingresos_totales"));
				ofertas.setCargo_servicio(rs.getDouble("cargo_servicio"));
				lista.add(ofertas);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public List<Ofertas> listarOfertasRechazadas(String codigo) throws SQLException {
		try {
			List<Ofertas> lista = new ArrayList<>();
			String sql = "SELECT * FROM ofertas o INNER JOIN estado_ofertas eo ON eo.id_estado_oferta = o.id_estado_oferta "
					+ "INNER JOIN empresa e ON e.id_empresa = o.id_empresa WHERE o.id_estado_oferta = 3 AND o.id_empresa = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			rs = cs.executeQuery();
			while (rs.next()) {
				Ofertas ofertas = new Ofertas();
				ofertas.setIdOferta(rs.getInt("id_oferta"));
				ofertas.setEstado_oferta(new EstadosOfertas(rs.getString("estado_oferta")));
				ofertas.setEmpresa(rs.getString("nombre_empresa"));
				ofertas.setTitulo(rs.getString("titulo_oferta"));
				ofertas.setPrecio_regular(rs.getDouble("precio_regular"));
				ofertas.setPrecio_oferta(rs.getDouble("precio_oferta"));
				ofertas.setFecha_inicio(rs.getString("fecha_inicio"));
				ofertas.setFecha_fin(rs.getString("fecha_fin"));
				ofertas.setFecha_limite(rs.getString("fecha_limite"));
				ofertas.setLimite_cupones(rs.getInt("limite_cupones"));
				ofertas.setDescripcion(rs.getString("descripcion_oferta"));
				ofertas.setCupones_vendidos(rs.getInt("cupones_vendidos"));
				ofertas.setCupones_disponibles(rs.getInt("cupones_disponibles"));
				ofertas.setIngresos_totales(rs.getDouble("ingresos_totales"));
				ofertas.setCargo_servicio(rs.getDouble("cargo_servicio"));
				ofertas.setJustificacion(rs.getString("justificacion"));
				lista.add(ofertas);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public List<Ofertas> listarOfertasDescartadas(String codigo) throws SQLException {
		try {
			List<Ofertas> lista = new ArrayList<>();
			String sql = "SELECT * FROM ofertas o INNER JOIN estado_ofertas eo ON eo.id_estado_oferta = o.id_estado_oferta "
					+ "INNER JOIN empresa e ON e.id_empresa = o.id_empresa "
					+ "WHERE o.id_estado_oferta = 4 AND o.id_empresa = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			rs = cs.executeQuery();
			while (rs.next()) {
				Ofertas ofertas = new Ofertas();
				ofertas.setIdOferta(rs.getInt("id_oferta"));
				ofertas.setEstado_oferta(new EstadosOfertas(rs.getString("estado_oferta")));
				ofertas.setEmpresa(rs.getString("nombre_empresa"));
				ofertas.setTitulo(rs.getString("titulo_oferta"));
				ofertas.setPrecio_regular(rs.getDouble("precio_regular"));
				ofertas.setPrecio_oferta(rs.getDouble("precio_oferta"));
				ofertas.setFecha_inicio(rs.getString("fecha_inicio"));
				ofertas.setFecha_fin(rs.getString("fecha_fin"));
				ofertas.setFecha_limite(rs.getString("fecha_limite"));
				ofertas.setLimite_cupones(rs.getInt("limite_cupones"));
				ofertas.setDescripcion(rs.getString("descripcion_oferta"));
				ofertas.setCupones_vendidos(rs.getInt("cupones_vendidos"));
				ofertas.setCupones_disponibles(rs.getInt("cupones_disponibles"));
				ofertas.setIngresos_totales(rs.getDouble("ingresos_totales"));
				ofertas.setCargo_servicio(rs.getDouble("cargo_servicio"));
				lista.add(ofertas);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int insertarOferta(Ofertas oferta) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "INSERT INTO ofertas(id_estado_oferta, id_empresa, titulo_oferta, precio_regular, "
					+ "precio_oferta, fecha_inicio, fecha_fin, fecha_limite, limite_cupones, descripcion_oferta, cupones_disponibles, cupones_vendidos, ingresos_totales, cargo_servicio) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			// System.out.print(sql);
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, oferta.getIdEstadoOferta());
			cs.setInt(2, oferta.getIdEmpresa());
			cs.setString(3, oferta.getTitulo());
			cs.setDouble(4, oferta.getPrecio_regular());
			cs.setDouble(5, oferta.getPrecio_oferta());
			cs.setString(6, oferta.getFecha_inicio());
			cs.setString(7, oferta.getFecha_fin());
			cs.setString(8, oferta.getFecha_limite());
			cs.setInt(9, oferta.getLimite_cupones());
			cs.setString(10, oferta.getDescripcion());
			cs.setInt(11, oferta.getCupones_disponibles());
			cs.setInt(12, 0);
			cs.setInt(13, 0);
			cs.setInt(14, 0);
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public Ofertas obtenerOferta(String codigo) throws SQLException {
		try {
			String sql = "SELECT * FROM ofertas WHERE id_oferta = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			rs = cs.executeQuery();
			if (rs.next()) {
				Ofertas ofertas = new Ofertas();
				ofertas.setIdOferta(rs.getInt("id_oferta"));
				ofertas.setTitulo(rs.getString("titulo_oferta"));
				ofertas.setPrecio_regular(rs.getDouble("precio_regular"));
				ofertas.setPrecio_oferta(rs.getDouble("precio_oferta"));
				ofertas.setFecha_inicio(rs.getString("fecha_inicio"));
				ofertas.setFecha_fin(rs.getString("fecha_fin"));
				ofertas.setFecha_limite(rs.getString("fecha_limite"));
				ofertas.setLimite_cupones(rs.getInt("limite_cupones"));
				ofertas.setDescripcion(rs.getString("descripcion_oferta"));
				ofertas.setCupones_vendidos(rs.getInt("cupones_vendidos"));
				ofertas.setCupones_disponibles(rs.getInt("cupones_disponibles"));
				ofertas.setIngresos_totales(rs.getDouble("ingresos_totales"));
				ofertas.setCargo_servicio(rs.getDouble("cargo_servicio"));
				this.desconectar();
				return ofertas;
			}
			this.desconectar();
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int modificarOferta(Ofertas oferta) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "UPDATE ofertas SET id_estado_oferta=?,titulo_oferta=?,precio_regular=?,"
					+ "precio_oferta=?,fecha_inicio=?,fecha_fin=?,fecha_limite=?,limite_cupones=?,descripcion_oferta=?,"
					+ "cupones_disponibles=? WHERE id_oferta =?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, oferta.getIdEstadoOferta());
			cs.setString(2, oferta.getTitulo());
			cs.setDouble(3, oferta.getPrecio_regular());
			cs.setDouble(4, oferta.getPrecio_oferta());
			cs.setString(5, oferta.getFecha_inicio());
			cs.setString(6, oferta.getFecha_fin());
			cs.setString(7, oferta.getFecha_limite());
			cs.setInt(8, oferta.getLimite_cupones());
			cs.setString(9, oferta.getDescripcion());
			cs.setInt(10, oferta.getCupones_disponibles());
			cs.setInt(11, oferta.getIdOferta());

			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public int eliminarOferta(String codigo) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "DELETE FROM ofertas WHERE id_oferta = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}

	}

	public int descartarOferta(String codigo) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "UPDATE ofertas SET id_estado_oferta=? WHERE id_oferta =?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, 4);
			cs.setString(2, codigo);
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}

	}

	/*
	 * 
	 * FUNCIONES OFERTAS ADMIN
	 * 
	 */

	public List<Ofertas> listarOfertasEsperaAdmin() throws SQLException {
		try {
			List<Ofertas> lista = new ArrayList<>();
			String sql = "SELECT * FROM ofertas o INNER JOIN estado_ofertas eo ON eo.id_estado_oferta = o.id_estado_oferta "
					+ "INNER JOIN empresa e ON e.id_empresa = o.id_empresa WHERE o.id_estado_oferta = 1";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				Ofertas ofertas = new Ofertas();
				ofertas.setIdOferta(rs.getInt("id_oferta"));
				ofertas.setEstado_oferta(new EstadosOfertas(rs.getString("estado_oferta")));
				ofertas.setEmpresa(rs.getString("nombre_empresa"));
				ofertas.setTitulo(rs.getString("titulo_oferta"));
				ofertas.setPrecio_regular(rs.getDouble("precio_regular"));
				ofertas.setPrecio_oferta(rs.getDouble("precio_oferta"));
				ofertas.setFecha_inicio(rs.getString("fecha_inicio"));
				ofertas.setFecha_fin(rs.getString("fecha_fin"));
				ofertas.setFecha_limite(rs.getString("fecha_limite"));
				ofertas.setLimite_cupones(rs.getInt("limite_cupones"));
				ofertas.setDescripcion(rs.getString("descripcion_oferta"));
				ofertas.setCupones_vendidos(rs.getInt("cupones_vendidos"));
				ofertas.setCupones_disponibles(rs.getInt("cupones_disponibles"));
				ofertas.setIngresos_totales(rs.getDouble("ingresos_totales"));
				ofertas.setCargo_servicio(rs.getDouble("cargo_servicio"));
				lista.add(ofertas);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public List<Ofertas> listarOfertasAprobadasAdmin() throws SQLException {
		try {
			List<Ofertas> lista = new ArrayList<>();
			LocalDate fechaActual = LocalDate.now();
			String sql = "SELECT * FROM ofertas o INNER JOIN estado_ofertas eo ON eo.id_estado_oferta = o.id_estado_oferta"
					+ " INNER JOIN empresa e ON e.id_empresa = o.id_empresa "
					+ "WHERE o.id_estado_oferta = 2 AND (o.fecha_inicio > ? AND o.fecha_fin > ?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, fechaActual.toString());
			cs.setString(2, fechaActual.toString());
			rs = cs.executeQuery();
			while (rs.next()) {
				Ofertas ofertas = new Ofertas();
				ofertas.setIdOferta(rs.getInt("id_oferta"));
				ofertas.setEstado_oferta(new EstadosOfertas(rs.getString("estado_oferta")));
				ofertas.setEmpresa(rs.getString("nombre_empresa"));
				ofertas.setTitulo(rs.getString("titulo_oferta"));
				ofertas.setPrecio_regular(rs.getDouble("precio_regular"));
				ofertas.setPrecio_oferta(rs.getDouble("precio_oferta"));
				ofertas.setFecha_inicio(rs.getString("fecha_inicio"));
				ofertas.setFecha_fin(rs.getString("fecha_fin"));
				ofertas.setFecha_limite(rs.getString("fecha_limite"));
				ofertas.setLimite_cupones(rs.getInt("limite_cupones"));
				ofertas.setDescripcion(rs.getString("descripcion_oferta"));
				ofertas.setCupones_vendidos(rs.getInt("cupones_vendidos"));
				ofertas.setCupones_disponibles(rs.getInt("cupones_disponibles"));
				ofertas.setIngresos_totales(rs.getDouble("ingresos_totales"));
				ofertas.setCargo_servicio(rs.getDouble("cargo_servicio"));
				lista.add(ofertas);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public List<Ofertas> listarOfertasActivasAdmin() throws SQLException {
		try {
			List<Ofertas> lista = new ArrayList<>();
			LocalDate fechaActual = LocalDate.now();
			String sql = "SELECT * FROM ofertas o INNER JOIN estado_ofertas eo ON eo.id_estado_oferta = o.id_estado_oferta "
					+ "INNER JOIN empresa e ON e.id_empresa = o.id_empresa "
					+ "WHERE o.id_estado_oferta = 2 AND (DATE(?) BETWEEN o.fecha_inicio AND o.fecha_fin)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, fechaActual.toString());
			rs = cs.executeQuery();
			while (rs.next()) {
				Ofertas ofertas = new Ofertas();
				ofertas.setIdOferta(rs.getInt("id_oferta"));
				ofertas.setEstado_oferta(new EstadosOfertas(rs.getString("estado_oferta")));
				ofertas.setEmpresa(rs.getString("nombre_empresa"));
				ofertas.setTitulo(rs.getString("titulo_oferta"));
				ofertas.setPrecio_regular(rs.getDouble("precio_regular"));
				ofertas.setPrecio_oferta(rs.getDouble("precio_oferta"));
				ofertas.setFecha_inicio(rs.getString("fecha_inicio"));
				ofertas.setFecha_fin(rs.getString("fecha_fin"));
				ofertas.setFecha_limite(rs.getString("fecha_limite"));
				ofertas.setLimite_cupones(rs.getInt("limite_cupones"));
				ofertas.setDescripcion(rs.getString("descripcion_oferta"));
				ofertas.setCupones_vendidos(rs.getInt("cupones_vendidos"));
				ofertas.setCupones_disponibles(rs.getInt("cupones_disponibles"));
				ofertas.setIngresos_totales(rs.getDouble("ingresos_totales"));
				ofertas.setCargo_servicio(rs.getDouble("cargo_servicio"));
				lista.add(ofertas);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public List<Ofertas> listarOfertasPasadasAdmin() throws SQLException {
		try {
			List<Ofertas> lista = new ArrayList<>();
			LocalDate fechaActual = LocalDate.now();
			String sql = "SELECT * FROM ofertas o INNER JOIN estado_ofertas eo ON eo.id_estado_oferta = o.id_estado_oferta "
					+ "INNER JOIN empresa e ON e.id_empresa = o.id_empresa "
					+ "WHERE o.id_estado_oferta = 2 AND (o.fecha_inicio < ? AND o.fecha_fin < ?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, fechaActual.toString());
			cs.setString(2, fechaActual.toString());
			rs = cs.executeQuery();
			while (rs.next()) {
				Ofertas ofertas = new Ofertas();
				ofertas.setIdOferta(rs.getInt("id_oferta"));
				ofertas.setEstado_oferta(new EstadosOfertas(rs.getString("estado_oferta")));
				ofertas.setEmpresa(rs.getString("nombre_empresa"));
				ofertas.setTitulo(rs.getString("titulo_oferta"));
				ofertas.setPrecio_regular(rs.getDouble("precio_regular"));
				ofertas.setPrecio_oferta(rs.getDouble("precio_oferta"));
				ofertas.setFecha_inicio(rs.getString("fecha_inicio"));
				ofertas.setFecha_fin(rs.getString("fecha_fin"));
				ofertas.setFecha_limite(rs.getString("fecha_limite"));
				ofertas.setLimite_cupones(rs.getInt("limite_cupones"));
				ofertas.setDescripcion(rs.getString("descripcion_oferta"));
				ofertas.setCupones_vendidos(rs.getInt("cupones_vendidos"));
				ofertas.setCupones_disponibles(rs.getInt("cupones_disponibles"));
				ofertas.setIngresos_totales(rs.getDouble("ingresos_totales"));
				ofertas.setCargo_servicio(rs.getDouble("cargo_servicio"));
				lista.add(ofertas);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public List<Ofertas> listarOfertasRechazadasAdmin() throws SQLException {
		try {
			List<Ofertas> lista = new ArrayList<>();
			String sql = "SELECT * FROM ofertas o INNER JOIN estado_ofertas eo ON eo.id_estado_oferta = o.id_estado_oferta "
					+ "INNER JOIN empresa e ON e.id_empresa = o.id_empresa WHERE o.id_estado_oferta = 3";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				Ofertas ofertas = new Ofertas();
				ofertas.setIdOferta(rs.getInt("id_oferta"));
				ofertas.setEstado_oferta(new EstadosOfertas(rs.getString("estado_oferta")));
				ofertas.setEmpresa(rs.getString("nombre_empresa"));
				ofertas.setTitulo(rs.getString("titulo_oferta"));
				ofertas.setPrecio_regular(rs.getDouble("precio_regular"));
				ofertas.setPrecio_oferta(rs.getDouble("precio_oferta"));
				ofertas.setFecha_inicio(rs.getString("fecha_inicio"));
				ofertas.setFecha_fin(rs.getString("fecha_fin"));
				ofertas.setFecha_limite(rs.getString("fecha_limite"));
				ofertas.setLimite_cupones(rs.getInt("limite_cupones"));
				ofertas.setDescripcion(rs.getString("descripcion_oferta"));
				ofertas.setCupones_vendidos(rs.getInt("cupones_vendidos"));
				ofertas.setCupones_disponibles(rs.getInt("cupones_disponibles"));
				ofertas.setIngresos_totales(rs.getDouble("ingresos_totales"));
				ofertas.setCargo_servicio(rs.getDouble("cargo_servicio"));
				ofertas.setJustificacion(rs.getString("justificacion"));
				lista.add(ofertas);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public List<Ofertas> listarOfertasDescartadasAdmin() throws SQLException {
		try {
			List<Ofertas> lista = new ArrayList<>();
			String sql = "SELECT * FROM ofertas o INNER JOIN estado_ofertas eo ON eo.id_estado_oferta = o.id_estado_oferta "
					+ "INNER JOIN empresa e ON e.id_empresa = o.id_empresa " + "WHERE o.id_estado_oferta = 4";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				Ofertas ofertas = new Ofertas();
				ofertas.setIdOferta(rs.getInt("id_oferta"));
				ofertas.setEstado_oferta(new EstadosOfertas(rs.getString("estado_oferta")));
				ofertas.setEmpresa(rs.getString("nombre_empresa"));
				ofertas.setTitulo(rs.getString("titulo_oferta"));
				ofertas.setPrecio_regular(rs.getDouble("precio_regular"));
				ofertas.setPrecio_oferta(rs.getDouble("precio_oferta"));
				ofertas.setFecha_inicio(rs.getString("fecha_inicio"));
				ofertas.setFecha_fin(rs.getString("fecha_fin"));
				ofertas.setFecha_limite(rs.getString("fecha_limite"));
				ofertas.setLimite_cupones(rs.getInt("limite_cupones"));
				ofertas.setDescripcion(rs.getString("descripcion_oferta"));
				ofertas.setCupones_vendidos(rs.getInt("cupones_vendidos"));
				ofertas.setCupones_disponibles(rs.getInt("cupones_disponibles"));
				ofertas.setIngresos_totales(rs.getDouble("ingresos_totales"));
				ofertas.setCargo_servicio(rs.getDouble("cargo_servicio"));
				lista.add(ofertas);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(OfertasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

}
