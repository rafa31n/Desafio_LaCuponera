package cuponera.beans;

public class Usuarios {

	private int idUsuario;
	private Cargo cargo;
	private int idCargo;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String correo;
	private String direccion;
	private String dui;
	private String password;
	private String cargoUsuario;
	private String passwordN;
	private String passwordN2;
	private int idEmpresa;
	private String token;
	private int verificado;

	public Usuarios() {
		this.idUsuario = 0;
		this.cargo = null;
		this.idCargo = 0;
		this.nombre = "";
		this.apellidos = "";
		this.telefono = "";
		this.correo = "";
		this.direccion = "";
		this.dui = "";
		this.password = "";
		this.cargoUsuario = "";
		this.passwordN = "";
		this.passwordN2 = "";
		this.idEmpresa = 0;
		this.token = "";
		this.verificado = 0;
	}

	public Usuarios(String nombre) {
		this.nombre = nombre;
	}

	public Usuarios(int idUsuario, Cargo cargo, int idCargo, String nombre, String apellidos, String telefono,
			String correo, String direccion, String dui, String password, String cargoUsuario, String passwordN,
			String passwordN2, int idEmpresa, String token, int verificado) {
		this.idUsuario = idUsuario;
		this.cargo = cargo;
		this.idCargo = idCargo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
		this.dui = dui;
		this.password = password;
		this.cargoUsuario = cargoUsuario;
		this.passwordN = passwordN;
		this.passwordN2 = passwordN2;
		this.idEmpresa = idEmpresa;
		this.token = token;
		this.verificado = verificado;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getVerificado() {
		return verificado;
	}

	public void setVerificado(int verificado) {
		this.verificado = verificado;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getPasswordN() {
		return passwordN;
	}

	public void setPasswordN(String passwordN) {
		this.passwordN = passwordN;
	}

	public String getPasswordN2() {
		return passwordN2;
	}

	public void setPasswordN2(String passwordN2) {
		this.passwordN2 = passwordN2;
	}

	public String getCargoUsuario() {
		return cargoUsuario;
	}

	public void setCargoUsuario(String cargoUsuario) {
		this.cargoUsuario = cargoUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public int getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDui() {
		return dui;
	}

	public void setDui(String dui) {
		this.dui = dui;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
