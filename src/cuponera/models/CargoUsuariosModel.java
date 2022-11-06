package cuponera.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import cuponera.beans.Usuarios;
import cuponera.beans.Cargo;

public class CargoUsuariosModel extends Conexion {

	public List<Cargo> listarCargosUsuarios() throws SQLException {
		try {
			List<Cargo> lista = new ArrayList<>();
			String sql = "SELECT * FROM cargo_usuarios";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				Cargo cargo = new Cargo();
				cargo.setIdCargoUsuario(rs.getInt("id_cargo_usuario"));
				cargo.setCargoUsuario(rs.getString("cargo_usuario"));
				lista.add(cargo);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

}
