package cuponera.beans;

public class EstadosOfertas {

	private int idEstadoOferta;
	private String estadoOferta;

	public EstadosOfertas() {
		this.idEstadoOferta = 0;
		this.estadoOferta = "";
	}

	public EstadosOfertas(String estadoOferta) {
		this.estadoOferta = estadoOferta;
	}

	public EstadosOfertas(int idEstadoOferta, String estadoOferta) {
		this.idEstadoOferta = idEstadoOferta;
		this.estadoOferta = estadoOferta;
	}

	public int getIdEstadoOferta() {
		return idEstadoOferta;
	}

	public void setIdEstadoOferta(int idEstadoOferta) {
		this.idEstadoOferta = idEstadoOferta;
	}

	public String getEstadoOferta() {
		return estadoOferta;
	}

	public void setEstadoOferta(String estadoOferta) {
		this.estadoOferta = estadoOferta;
	}

}
