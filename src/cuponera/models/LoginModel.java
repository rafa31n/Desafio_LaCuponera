package cuponera.models;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cuponera.beans.Usuarios;

public class LoginModel extends Conexion {

	public Usuarios iniciarSesion(String correo, String password) throws SQLException {
		try {
			String sql = "SELECT * FROM usuarios u INNER JOIN cargo_usuarios cu ON u.id_cargo = cu.id_cargo_usuario "
					+ "WHERE u.correo = ? AND u.password = ? AND verificado = 1";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, correo);
			cs.setString(2, password);
			rs = cs.executeQuery();

			if (rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setIdUsuario(rs.getInt("id_usuario"));
				usuario.setIdCargo(rs.getInt("id_cargo"));
				usuario.setIdEmpresa(rs.getInt("id_empresa"));
				usuario.setCargoUsuario(rs.getString("cargo_usuario"));
				usuario.setNombre(rs.getString("nombres"));
				usuario.setApellidos(rs.getString("apellidos"));
				usuario.setTelefono(rs.getString("telefono"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setDireccion(rs.getString("direccion"));
				usuario.setDui(rs.getString("dui"));
				usuario.setPassword(rs.getString("password"));

				this.desconectar();
				return usuario;
			}
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int verificarToken(String correo, String token) throws SQLException {
		try {
			String sql = "SELECT * FROM usuarios u WHERE correo = ? AND token = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, correo);
			cs.setString(2, token);
			rs = cs.executeQuery();

			if (rs.next()) {
				int validado = verificarCuenta(correo);
				this.desconectar();
				return validado;
			}
			this.desconectar();
			return 0;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public int verificarCuenta(String correo) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "UPDATE usuarios SET verificado = 1 WHERE correo =?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, correo);
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public int cambiarContraOlvidada(String correo, String password) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "UPDATE usuarios SET password = ? WHERE correo =?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, password);
			cs.setString(2, correo);
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

}