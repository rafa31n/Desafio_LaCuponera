package cuponera.models;

import cuponera.beans.CuponesBeans;
import cuponera.beans.PromocionesBeans;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PromocionesModel extends Conexion {

	public List<PromocionesBeans> listaPromociones() throws SQLException {
		LocalDate fechaActual = LocalDate.now();
		try {
			List<PromocionesBeans> lista = new ArrayList<>();
			String sql = "SELECT * FROM ofertas o INNER JOIN empresa e ON e.id_empresa = o.id_empresa "
					+ "WHERE o.id_estado_oferta = 2 AND (DATE(?) BETWEEN o.fecha_inicio AND o.fecha_fin) AND o.cupones_disponibles > 0";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, fechaActual.toString());
			rs = cs.executeQuery();
			while (rs.next()) {
				PromocionesBeans promocion = new PromocionesBeans();
				promocion.setId_oferta(Integer.parseInt(rs.getString("id_oferta")));
				promocion.setId_estado_oferta(Integer.parseInt(rs.getString("id_estado_oferta")));
				promocion.setId_empresa(Integer.parseInt(rs.getString("id_empresa")));
				// promocion.setId_administrador_empresa(Integer.parseInt(rs.getString("id_administrador_empresa")));
				promocion.setTitulo_oferta(rs.getString("titulo_oferta"));
				promocion.setPrecio_regular(Float.parseFloat(rs.getString("precio_regular")));
				promocion.setPrecio_oferta(Float.parseFloat(rs.getString("precio_oferta")));
				promocion.setFecha_inicio(Date.valueOf(rs.getString("fecha_inicio")));
				promocion.setFecha_fin(Date.valueOf(rs.getString("fecha_fin")));
				promocion.setFecha_limite(Date.valueOf(rs.getString("fecha_limite")));
				promocion.setLimite_cupones(Integer.parseInt(rs.getString("limite_cupones")));
				promocion.setDescripcion_oferta(rs.getString("descripcion_oferta"));
				promocion.setCupones_vendidos(Integer.parseInt(rs.getString("cupones_vendidos")));
				promocion.setCupones_disponibles(Integer.parseInt(rs.getString("cupones_disponibles")));
				promocion.setIngresos_totales(Float.parseFloat(rs.getString("ingresos_totales")));
				promocion.setCargo_servicio(Float.parseFloat(rs.getString("cargo_servicio")));
				promocion.setCodigoEmpresa(rs.getString("codigo_empresa"));
				lista.add(promocion);
				// System.out.println(rs.getRow());
			}
			this.desconectar();
			return lista;
		} catch (Exception e) {
			Logger.getLogger(Cupones.class.getName()).log(Level.SEVERE, null, e);
			this.desconectar();
			return null;
		}

	}

	public List<PromocionesBeans> listaEspera() throws SQLException {

		try {
			List<PromocionesBeans> lista = new ArrayList<>();
			String sql = "SELECT * FROM ofertas WHERE id_estado_oferta = 1";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				PromocionesBeans promocion = new PromocionesBeans();
				promocion.setId_oferta(Integer.parseInt(rs.getString("id_oferta")));
				promocion.setId_estado_oferta(Integer.parseInt(rs.getString("id_estado_oferta")));
				promocion.setId_empresa(Integer.parseInt(rs.getString("id_empresa")));
				// promocion.setId_administrador_empresa(Integer.parseInt(rs.getString("id_administrador_empresa")));
				promocion.setTitulo_oferta(rs.getString("titulo_oferta"));
				promocion.setPrecio_regular(Float.parseFloat(rs.getString("precio_regular")));
				promocion.setPrecio_oferta(Float.parseFloat(rs.getString("precio_oferta")));
				promocion.setFecha_inicio(Date.valueOf(rs.getString("fecha_inicio")));
				promocion.setFecha_fin(Date.valueOf(rs.getString("fecha_fin")));
				promocion.setFecha_limite(Date.valueOf(rs.getString("fecha_limite")));
				promocion.setLimite_cupones(Integer.parseInt(rs.getString("limite_cupones")));
				promocion.setDescripcion_oferta(rs.getString("descripcion_oferta"));
				promocion.setCupones_vendidos(Integer.parseInt(rs.getString("cupones_vendidos")));
				promocion.setCupones_disponibles(Integer.parseInt(rs.getString("cupones_disponibles")));
				promocion.setIngresos_totales(Float.parseFloat(rs.getString("ingresos_totales")));
				promocion.setCargo_servicio(Float.parseFloat(rs.getString("cargo_servicio")));
				lista.add(promocion);
			}
			this.desconectar();
			return lista;
		} catch (Exception e) {
			Logger.getLogger(Cupones.class.getName()).log(Level.SEVERE, null, e);
			this.desconectar();
			return null;
		}

	}

	public void insertarCupones(CuponesBeans beans) throws SQLException {
		try {
			String sql = "INSERT INTO cupones (id_oferta,id_estado_cupon,codigo_cupon,dui, id_usuario) VALUES (?,?,?,?,?)";
			cs = conexion.prepareCall(sql);
			cs.setInt(1, beans.getId_oferta());
			cs.setInt(2, beans.getEstado_cupon());
			cs.setString(3, beans.getCodigo_cupon());
			cs.setString(4, beans.getDui());
			cs.setInt(5, beans.getIdUser());
			cs.executeUpdate();
		} catch (Exception ex) {
			Logger.getLogger(PromocionesModel.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void update(String id, String cupon, Double precioOferta, Double comision) throws SQLException {
		int cupones_vendidos = Integer.parseInt(rs.getString("cupones_vendidos"));
		int cupones_disponblies = Integer.parseInt(rs.getString("cupones_disponibles"));
		int resultadoVendidos = cupones_vendidos + Integer.parseInt(cupon);
		int resultadoDisponibles = cupones_disponblies - Integer.parseInt(cupon);

		double ingresosTotales = resultadoVendidos * precioOferta;
		double cargoServicio = ingresosTotales * comision;

		String sqlUpdate = "UPDATE ofertas SET cupones_vendidos = " + resultadoVendidos + ", cupones_disponibles = "
				+ resultadoDisponibles + ", ingresos_totales = " + ingresosTotales + ", cargo_servicio = "
				+ cargoServicio + " WHERE id_oferta = " + id + "";
		// System.out.println(sqlUpdate);
		cs = conexion.prepareCall(sqlUpdate);
		cs.executeUpdate();
	}

	public List<String> ListaCodigos(String cupon, String codigoEmpresa) {
		List<String> Listacodigo = new ArrayList<String>();
		int max = 5000000;
		int min = 1000000;
		int range = max - min + 1;
		for (int i = 0; i < Integer.parseInt(cupon); i++) {
			String cod = codigoEmpresa;
			int random = (int) (Math.random() * range) + min;
			String codigos = cod + random;
			Listacodigo.add(codigos);
		}
		return Listacodigo;
	}

	public boolean updateOfertas(String id, String cupon, String dui, int idUser, String codigoEmpresa)
			throws SQLException {
		Double precioOferta = 0.0;
		Double comision = 0.0;
		try {
			List<String> codigos = new ArrayList<String>();
			String sqlSelect = "SELECT * FROM ofertas o INNER JOIN empresa e ON e.id_empresa = o.id_empresa WHERE o.id_oferta = ?";
			this.conectar();
			cs = conexion.prepareCall(sqlSelect);
			cs.setString(1, id);
			rs = cs.executeQuery();
			codigos = ListaCodigos(cupon, codigoEmpresa);
			while (rs.next()) {
				for (int i = 0; i < codigos.size(); i++) {
					CuponesBeans cuponBeans = new CuponesBeans();
					cuponBeans.setId_oferta(Integer.parseInt(rs.getString("id_oferta")));
					cuponBeans.setEstado_cupon(1);
					cuponBeans.setCodigo_cupon(codigos.get(i));
					cuponBeans.setDui(dui);
					cuponBeans.setIdUser(idUser);
					// System.out.println(i);
					insertarCupones(cuponBeans);
					precioOferta = rs.getDouble("precio_oferta");
					comision = rs.getDouble("porcentaje_comision");
				}
				update(id, cupon, precioOferta, comision);

			}
			this.desconectar();
			return true;
		} catch (Exception ex) {
			Logger.getLogger(PromocionesModel.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public boolean AceptarPromocion(String id) {

		try {
			String sqlAprobar = "UPDATE ofertas SET id_estado_oferta = 2 WHERE id_oferta = ?";
			this.conectar();
			cs = conexion.prepareCall(sqlAprobar);
			cs.setString(1, id);
			cs.executeUpdate();
			this.desconectar();
			return true;
		} catch (Exception ex) {
			Logger.getLogger(PromocionesModel.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}

	}

	public boolean validarCompra(String cupon) {
		try {
			String sqlAprobar = "UPDATE cupones SET confirmacion = 1 WHERE codigo_cupon = ?";
			this.conectar();
			cs = conexion.prepareCall(sqlAprobar);
			cs.setString(1, cupon);
			cs.executeUpdate();
			this.desconectar();
			return true;
		} catch (Exception ex) {
			Logger.getLogger(PromocionesModel.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public boolean RechazarPromocion(String id, String justificacion) {
		try {
			String sqlRechazar = "UPDATE ofertas SET id_estado_oferta = 3, justificacion = ? WHERE id_oferta = ?";
			this.conectar();
			cs = conexion.prepareCall(sqlRechazar);
			cs.setString(1, justificacion);
			cs.setString(2, id);
			cs.executeUpdate();
			this.desconectar();
			return true;
		} catch (Exception ex) {
			Logger.getLogger(PromocionesModel.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
}
