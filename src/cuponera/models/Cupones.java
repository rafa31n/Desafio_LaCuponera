package cuponera.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cuponera.beans.CuponesBeans;

public class Cupones extends Conexion {

	public List<CuponesBeans> listaCupones() throws SQLException {
		try {
			List<CuponesBeans> lista = new ArrayList<>();
			String sql = "SELECT * FROM cupones WHERE id_estado_cupon = 2";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				CuponesBeans cupon = new CuponesBeans();
				cupon.setId_cupon(Integer.parseInt(rs.getString("id_cupon")));
				cupon.setId_oferta(Integer.parseInt(rs.getString("id_oferta")));
				cupon.setEstado_cupon(Integer.parseInt(rs.getString("id_estado_cupon")));
				cupon.setCodigo_cupon(rs.getString("codigo_cupon"));
				cupon.setDui(rs.getString("dui"));
				lista.add(cupon);
			}
			this.desconectar();
			return lista;
		} catch (Exception e) {
			Logger.getLogger(Cupones.class.getName()).log(Level.SEVERE, null, e);
			this.desconectar();
			return null;
		}

	}

	public void updateCupon(String cod, String dui) throws SQLException {
		String sqlUpdate = "UPDATE cupones SET id_estado_cupon = 2 WHERE codigo_cupon = " + cod + ", dui = " + dui + "";
		System.out.println(sqlUpdate);
		cs = conexion.prepareCall(sqlUpdate);
		cs.executeUpdate();

	}

	public boolean canjearCupon(String codigo, String dui) throws SQLException {

		try {
			String sql = "SELECT * FROM cupones WHERE id_estado_cupon = 1 AND dui = " + dui + "";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();

			if (rs.getRow() <= 0) {
				return false;
			} else {
				updateCupon(codigo, dui);
			}
			this.desconectar();
			return true;

		} catch (Exception ex) {
			return false;
		}

	}

}
