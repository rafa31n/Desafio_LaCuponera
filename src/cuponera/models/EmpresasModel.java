package cuponera.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cuponera.beans.Empresa;

public class EmpresasModel extends Conexion {

	public List<Empresa> listarEmpresas() throws SQLException {
		try {
			List<Empresa> lista = new ArrayList<>();
			String sql = "SELECT e.*, r.nombre_rubro FROM empresa e INNER JOIN rubros r ON e.id_rubro = r.id_rubro "
					+ "ORDER BY e.id_rubro";
			this.conectar();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				Empresa empresa = new Empresa();
				empresa.setIdEmpresa(rs.getInt("id_empresa"));
				empresa.setIdRubro(rs.getInt("id_rubro"));
				empresa.setNombreEmpresa(rs.getString("nombre_empresa"));
				empresa.setCodigoEmpresa(rs.getString("codigo_empresa"));
				empresa.setDireccion(rs.getString("direccion"));
				empresa.setTelefono(rs.getString("telefono"));
				empresa.setCorreo(rs.getString("correo"));
				empresa.setPassword(rs.getString("password"));
				empresa.setNombreContacto(rs.getString("nombre_contacto"));
				empresa.setPorcentajeComision(rs.getInt("porcentaje_comision"));
				empresa.setNombreRubro(rs.getString("nombre_rubro"));
				lista.add(empresa);
			}
			this.desconectar();
			return lista;
		} catch (SQLException ex) {
			Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int insertarEmpresa(Empresa empresa) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "INSERT INTO empresa(id_rubro, nombre_empresa, codigo_empresa, direccion, nombre_contacto, telefono, correo, porcentaje_comision, password) VALUES (?,?,?,?,?,?,?,?,?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, empresa.getIdRubro());
			cs.setString(2, empresa.getNombreEmpresa());
			cs.setString(3, empresa.getCodigoEmpresa());
			cs.setString(4, empresa.getDireccion());
			cs.setString(5, empresa.getNombreContacto());
			cs.setString(6, empresa.getTelefono());
			cs.setString(7, empresa.getCorreo());
			cs.setDouble(8, empresa.getPorcentajeComision());
			cs.setString(9, empresa.getPassword());
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			String correoEmpresa = empresa.getCorreo();
			int idEmpresa = buscarEmpresa(correoEmpresa);
			// System.out.print(idEmpresa);
			crearUsuarioEmpresa(empresa, idEmpresa);
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public int buscarEmpresa(String correoEmpresa) throws SQLException {
		try {
			String sql = "SELECT * FROM empresa WHERE correo = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, correoEmpresa);
			rs = cs.executeQuery();
			if (rs.next()) {
				Empresa em = new Empresa();
				em.setIdEmpresa(rs.getInt("id_empresa"));
				int idEmpresa = em.getIdEmpresa();
				this.desconectar();
				return idEmpresa;
			}
			return 0;
		} catch (SQLException ex) {
			Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public int crearUsuarioEmpresa(Empresa empresa, int idEmpresa) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "INSERT INTO usuarios(id_cargo, nombres, apellidos, telefono, correo, direccion, dui, password, id_empresa, verificado) VALUES (?,?,?,?,?,?,?,?,?,?)";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, 2);
			cs.setString(2, "Empresa");
			cs.setString(3, empresa.getNombreEmpresa());
			cs.setString(4, empresa.getTelefono());
			cs.setString(5, empresa.getCorreo());
			cs.setString(6, empresa.getDireccion());
			cs.setString(7, 0 + empresa.getTelefono());
			cs.setString(8, empresa.getPassword());
			cs.setInt(9, idEmpresa);
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

	public Empresa obtenerEmpresa(String codigo) throws SQLException {
		try {
			String sql = "SELECT * FROM empresa e INNER JOIN rubros r ON e.id_rubro = r.id_rubro WHERE id_empresa=?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			rs = cs.executeQuery();
			if (rs.next()) {
				Empresa empresa = new Empresa();
				empresa.setIdEmpresa(rs.getInt("id_empresa"));
				empresa.setIdRubro(rs.getInt("id_rubro"));
				empresa.setNombreEmpresa(rs.getString("nombre_empresa"));
				empresa.setCodigoEmpresa(rs.getString("codigo_empresa"));
				empresa.setDireccion(rs.getString("direccion"));
				empresa.setTelefono(rs.getString("telefono"));
				empresa.setCorreo(rs.getString("correo"));
				empresa.setPassword(rs.getString("password"));
				empresa.setNombreContacto(rs.getString("nombre_contacto"));
				empresa.setPorcentajeComision(rs.getDouble("porcentaje_comision"));
				this.desconectar();
				return empresa;
			}
			this.desconectar();
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int modificarEmpresa(Empresa empresa) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "UPDATE empresa SET id_rubro=?,nombre_empresa=?,codigo_empresa=?,direccion=?,nombre_contacto=?,telefono=?,correo=?,"
					+ "porcentaje_comision=?,password=? WHERE id_empresa = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, empresa.getIdRubro());
			cs.setString(2, empresa.getNombreEmpresa());
			cs.setString(3, empresa.getCodigoEmpresa());
			cs.setString(4, empresa.getDireccion());
			cs.setString(5, empresa.getNombreContacto());
			cs.setString(6, empresa.getTelefono());
			cs.setString(7, empresa.getCorreo());
			cs.setDouble(8, empresa.getPorcentajeComision());
			cs.setString(9, empresa.getPassword());
			cs.setInt(10, empresa.getIdEmpresa());
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}

	public int eliminarEmpresa(String codigo) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "DELETE FROM empresa WHERE id_empresa = ?";
			this.conectar();
			cs = conexion.prepareCall(sql);
			cs.setString(1, codigo);
			filasAfectadas = cs.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(EmpresasModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}

	}

}
