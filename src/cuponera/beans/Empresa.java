package cuponera.beans;

public class Empresa {
	private int idRubro;
	private String nombreEmpresa;
	private String codigoEmpresa;
	private String direccion;
	private String nombreContacto;
	private String telefono;
	private String correo;
	private double porcentajeComision;
	private String password;
	private int idEmpresa;
	private String nombreRubro;

	public Empresa() {
		this.idRubro = 0;
		this.nombreEmpresa = "";
		this.codigoEmpresa = "";
		this.direccion = "";
		this.nombreContacto = "";
		this.telefono = "";
		this.correo = "";
		this.porcentajeComision = 0;
		this.password = "";
		this.idEmpresa = 0;
		this.nombreRubro = "";
	}

	public Empresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public Empresa(int idEmpresa, int idRubro, String nombreEmpresa, String codigoEmpresa, String direccion,
			String nombreContacto, String telefono, String correo, double porcentajeComision, String password,
			String nombreRubro) {
		this.idRubro = idRubro;
		this.nombreEmpresa = nombreEmpresa;
		this.codigoEmpresa = codigoEmpresa;
		this.direccion = direccion;
		this.nombreContacto = nombreContacto;
		this.telefono = telefono;
		this.correo = correo;
		this.porcentajeComision = porcentajeComision;
		this.password = password;
		this.idEmpresa = idEmpresa;
		this.nombreRubro = nombreRubro;
	}

	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public int getIdRubro() {
		return idRubro;
	}

	public void setIdRubro(int idRubro) {
		this.idRubro = idRubro;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public double getPorcentajeComision() {
		return porcentajeComision;
	}

	public void setPorcentajeComision(double porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
