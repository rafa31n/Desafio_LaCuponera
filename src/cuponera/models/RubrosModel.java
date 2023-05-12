package cuponera.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cuponera.beans.Rubros;

public class RubrosModel extends Conexion {

	public List<Rubros> listarRubros() throws SQLException {
		try {
			List<Rubros> lista = new ArrayList<>();
			String sql = "SELECT * FROM rubros";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				Rubros rubros = new Rubros();
				rubros.setIdRubro(rs.getInt("id_rubro"));
				rubros.setNombreRubro(rs.getString("nombre_rubro"));
				lista.add(rubros);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(RubrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int insertarRubros(Rubros rubros) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "INSERT INTO rubros(nombre_rubro) VALUES (?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, rubros.getNombreRubro());
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(RubrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public Rubros obtenerRubros(String id) throws SQLException {
		try {
			String sql = "SELECT * FROM rubros WHERE id_rubro = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, id);
			rs = cs.executeQuery();
			if (rs.next()) {
				Rubros rubros = new Rubros();
				rubros.setIdRubro(rs.getInt("id_rubro"));
				rubros.setNombreRubro(rs.getString("nombre_rubro"));
				this.desconectar();
				return rubros;
			}
			this.desconectar();
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(RubrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int modificarRubros(Rubros rubros) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "UPDATE rubros SET nombre_rubro=? WHERE id_rubro =?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, rubros.getNombreRubro());
			cs.setInt(2, rubros.getIdRubro());
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(RubrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public int eliminarRubros(String id) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "DELETE FROM rubros WHERE id_rubro = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, id);
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(RubrosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}

	}


}
