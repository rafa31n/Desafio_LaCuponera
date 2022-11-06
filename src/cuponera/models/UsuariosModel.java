package cuponera.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cuponera.beans.Usuarios;
import cuponera.beans.Cargo;
import cuponera.beans.Cupones;

public class UsuariosModel extends Conexion {

	/*
	 * 
	 * CLIENTES
	 * 
	 */

	public List<Usuarios> listarClientes() throws SQLException {
		try {
			List<Usuarios> lista = new ArrayList<>();
			String sql = "SELECT * FROM usuarios u INNER JOIN cargo_usuarios cu ON u.id_cargo = cu.id_cargo_usuario WHERE u.id_cargo = 3";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setIdUsuario(rs.getInt("id_usuario"));
				usuario.setCargoUsuario(rs.getString("cargo_usuario"));
				usuario.setNombre(rs.getString("nombres"));
				usuario.setApellidos(rs.getString("apellidos"));
				usuario.setTelefono(rs.getString("telefono"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setDireccion(rs.getString("direccion"));
				usuario.setDui(rs.getString("dui"));
				usuario.setPassword(rs.getString("password"));
				lista.add(usuario);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int registrarCliente(Usuarios usuarios) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "INSERT INTO usuarios(id_cargo, nombres, apellidos, telefono, correo, direccion, dui, password, token, verificado) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, usuarios.getIdCargo());
			cs.setString(2, usuarios.getNombre());
			cs.setString(3, usuarios.getApellidos());
			cs.setString(4, usuarios.getTelefono());
			cs.setString(5, usuarios.getCorreo());
			cs.setString(6, usuarios.getDireccion());
			cs.setString(7, usuarios.getDui());
			cs.setString(8, usuarios.getPassword());
			cs.setString(9, usuarios.getToken());
			cs.setInt(10, 0);
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public List<Cupones> obtenerCuponDisponible(String codigo) throws SQLException {
		try {
			List<Cupones> lista = new ArrayList<>();
			String sql = "SELECT * FROM cupones c " + " INNER JOIN usuarios u ON u.id_usuario = c.id_usuario"
					+ "	INNER JOIN ofertas o ON o.id_oferta = c.id_oferta"
					+ "	WHERE c.id_usuario = ? AND c.id_estado_cupon = 1 AND c.confirmacion = 1;";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			rs = cs.executeQuery();
			while (rs.next()) {
				Cupones cupon = new Cupones();
				cupon.setIdCupon(rs.getInt("id_cupon"));
				cupon.setTituloOferta(rs.getString("titulo_oferta"));
				cupon.setCodigoCupon((rs.getString("codigo_cupon")));
				lista.add(cupon);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public List<Cupones> obtenerCuponCanjeado(String codigo) throws SQLException {
		try {
			List<Cupones> lista = new ArrayList<>();
			String sql = "SELECT * FROM cupones c " + " INNER JOIN usuarios u ON u.id_usuario = c.id_usuario"
					+ "	INNER JOIN ofertas o ON o.id_oferta = c.id_oferta"
					+ "	WHERE c.id_usuario = ? AND c.id_estado_cupon = 2 AND c.confirmacion = 1;";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			rs = cs.executeQuery();
			while (rs.next()) {
				Cupones cupon = new Cupones();
				cupon.setIdCupon(rs.getInt("id_cupon"));
				cupon.setTituloOferta(rs.getString("titulo_oferta"));
				cupon.setCodigoCupon((rs.getString("codigo_cupon")));
				lista.add(cupon);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public List<Cupones> obtenerCuponVencido(String codigo) throws SQLException {
		try {
			List<Cupones> lista = new ArrayList<>();
			String sql = "SELECT * FROM cupones c " + " INNER JOIN usuarios u ON u.id_usuario = c.id_usuario"
					+ "	INNER JOIN ofertas o ON o.id_oferta = c.id_oferta"
					+ "	WHERE c.id_usuario = ? AND c.id_estado_cupon = 3 AND c.confirmacion = 1;";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			rs = cs.executeQuery();
			while (rs.next()) {
				Cupones cupon = new Cupones();
				cupon.setIdCupon(rs.getInt("id_cupon"));
				cupon.setTituloOferta(rs.getString("titulo_oferta"));
				cupon.setCodigoCupon((rs.getString("codigo_cupon")));
				lista.add(cupon);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	/*
	 * 
	 * DEPENDIENTES DE SUCURSAL
	 * 
	 */
	public List<Usuarios> listarDependientes(String codigo) throws SQLException {
		try {
			List<Usuarios> lista = new ArrayList<>();
			String sql = "SELECT * FROM usuarios u INNER JOIN cargo_usuarios cu ON u.id_cargo = cu.id_cargo_usuario"
					+ " WHERE u.id_cargo = 4 AND u.id_empresa = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			rs = cs.executeQuery();
			while (rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setIdUsuario(rs.getInt("id_usuario"));
				usuario.setCargoUsuario(rs.getString("cargo_usuario"));
				usuario.setNombre(rs.getString("nombres"));
				usuario.setApellidos(rs.getString("apellidos"));
				usuario.setTelefono(rs.getString("telefono"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setDireccion(rs.getString("direccion"));
				usuario.setDui(rs.getString("dui"));
				usuario.setPassword(rs.getString("password"));
				lista.add(usuario);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int insertarDependiente(Usuarios usuarios) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "INSERT INTO usuarios(id_cargo, nombres, apellidos, telefono, correo, direccion, dui, password, id_empresa, verificado)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, 4);
			cs.setString(2, usuarios.getNombre());
			cs.setString(3, usuarios.getApellidos());
			cs.setString(4, usuarios.getTelefono());
			cs.setString(5, usuarios.getCorreo());
			cs.setString(6, usuarios.getDireccion());
			cs.setString(7, usuarios.getDui());
			cs.setString(8, usuarios.getPassword());
			cs.setInt(9, usuarios.getIdEmpresa());
			cs.setInt(10, 1);
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public Usuarios obtenerDependiente(String codigo) throws SQLException {
		try {
			String sql = "SELECT * FROM usuarios u WHERE id_usuario = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			rs = cs.executeQuery();
			if (rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setIdUsuario(rs.getInt("id_usuario"));
				// usuario.setCargo(new Cargo(rs.getString("cargo_usuario")));
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
			this.desconectar();
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int modificarUsuarioDependiente(Usuarios usuario) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "UPDATE usuarios SET nombres=?,apellidos=?,telefono=?,correo=?,direccion=?,dui=?,password=? "
					+ "WHERE id_usuario =?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, usuario.getNombre());
			cs.setString(2, usuario.getApellidos());
			cs.setString(3, usuario.getTelefono());
			cs.setString(4, usuario.getCorreo());
			cs.setString(5, usuario.getDireccion());
			cs.setString(6, usuario.getDui());
			cs.setString(7, usuario.getPassword());
			cs.setInt(8, usuario.getIdUsuario());
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	/*
	 * 
	 * USUARIOSS
	 * 
	 */

	public List<Usuarios> listarUsuarios() throws SQLException {
		try {
			List<Usuarios> lista = new ArrayList<>();
			String sql = "SELECT * FROM usuarios u INNER JOIN cargo_usuarios cu ON u.id_cargo = cu.id_cargo_usuario "
					+ "WHERE u.id_cargo != 3 ORDER BY u.id_cargo";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setIdUsuario(rs.getInt("id_usuario"));
				usuario.setCargoUsuario(rs.getString("cargo_usuario"));
				usuario.setNombre(rs.getString("nombres"));
				usuario.setApellidos(rs.getString("apellidos"));
				usuario.setTelefono(rs.getString("telefono"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setDireccion(rs.getString("direccion"));
				usuario.setDui(rs.getString("dui"));
				usuario.setPassword(rs.getString("password"));
				lista.add(usuario);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int insertarUsuario(Usuarios usuarios) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "INSERT INTO usuarios(id_cargo, nombres, apellidos, telefono, correo, direccion, dui, password, verificado) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, usuarios.getIdCargo());
			cs.setString(2, usuarios.getNombre());
			cs.setString(3, usuarios.getApellidos());
			cs.setString(4, usuarios.getTelefono());
			cs.setString(5, usuarios.getCorreo());
			cs.setString(6, usuarios.getDireccion());
			cs.setString(7, usuarios.getDui());
			cs.setString(8, usuarios.getPassword());
			cs.setInt(9, 1);
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public Usuarios obtenerUsuario(String codigo) throws SQLException {
		try {
			String sql = "SELECT * FROM usuarios u INNER JOIN cargo_usuarios cu ON u.id_cargo = cu.id_cargo_usuario"
					+ " WHERE id_usuario = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			rs = cs.executeQuery();
			if (rs.next()) {
				Usuarios usuario = new Usuarios();
				usuario.setIdUsuario(rs.getInt("id_usuario"));
				usuario.setCargo(new Cargo(rs.getString("cargo_usuario")));
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
			this.desconectar();
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int modificarUsuario(Usuarios usuario) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "UPDATE usuarios SET id_cargo=?,nombres=?,apellidos=?,telefono=?,correo=?,direccion=?,dui=?,password=? "
					+ "WHERE id_usuario =?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, usuario.getIdCargo());
			cs.setString(2, usuario.getNombre());
			cs.setString(3, usuario.getApellidos());
			cs.setString(4, usuario.getTelefono());
			cs.setString(5, usuario.getCorreo());
			cs.setString(6, usuario.getDireccion());
			cs.setString(7, usuario.getDui());
			cs.setString(8, usuario.getPassword());
			cs.setInt(9, usuario.getIdUsuario());
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public int eliminarUsuario(String codigo) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(UsuariosModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}

	}

	public int cambiarContra(Usuarios usuario) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "UPDATE usuarios u SET u.password = ? WHERE u.id_usuario = ? AND u.password = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, usuario.getPasswordN());
			cs.setInt(2, usuario.getIdUsuario());
			cs.setString(3, usuario.getPassword());
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
