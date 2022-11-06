package cuponera.beans;

public class Cupones {
	private int idCupon;
	private int idUsuario;
	private int idOferta;
	private int idEstadoCupon;
	private String codigoCupon;
	private int cantidadDisponibles;
	private String tituloOferta;

	public Cupones() {
		this.idCupon = 0;
		this.idUsuario = 0;
		this.idOferta = 0;
		this.idEstadoCupon = 0;
		this.codigoCupon = "";
		this.cantidadDisponibles = 0;
		this.tituloOferta = "";
	}

	public Cupones(int idCupon, int idUsuario, int idOferta, int idEstadoCupon, String codigoCupon, String tituloOferta,
			int cantidadDisponibles) {
		this.idCupon = idCupon;
		this.idUsuario = idUsuario;
		this.idOferta = idOferta;
		this.idEstadoCupon = idEstadoCupon;
		this.cantidadDisponibles = cantidadDisponibles;
		this.tituloOferta = tituloOferta;
	}

	public String getTituloOferta() {
		return tituloOferta;
	}

	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	public int getCantidadDisponibles() {
		return cantidadDisponibles;
	}

	public void setCantidadDisponibles(int cantidadDisponibles) {
		this.cantidadDisponibles = cantidadDisponibles;
	}

	public int getIdCupon() {
		return idCupon;
	}

	public void setIdCupon(int idCupon) {
		this.idCupon = idCupon;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}

	public int getIdEstadoCupon() {
		return idEstadoCupon;
	}

	public void setIdEstadoCupon(int idEstadoCupon) {
		this.idEstadoCupon = idEstadoCupon;
	}

	public String getCodigoCupon() {
		return codigoCupon;
	}

	public void setCodigoCupon(String codigoCupon) {
		this.codigoCupon = codigoCupon;
	}

}
