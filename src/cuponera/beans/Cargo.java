package cuponera.beans;

public class Cargo {

	private int idCargoUsuario;
	private String cargoUsuario;

	public Cargo() {
		this.idCargoUsuario = 0;
		this.cargoUsuario = "";
	}

	public Cargo(String cargoUsuario) {
		this.cargoUsuario = cargoUsuario;
	}

	public Cargo(int idCargoUsuario, String cargoUsuario) {
		this.idCargoUsuario = idCargoUsuario;
		this.cargoUsuario = cargoUsuario;
	}

	public int getIdCargoUsuario() {
		return idCargoUsuario;
	}

	public void setIdCargoUsuario(int idCargoUsuario) {
		this.idCargoUsuario = idCargoUsuario;
	}

	public String getCargoUsuario() {
		return cargoUsuario;
	}

	public void setCargoUsuario(String cargoUsuario) {
		this.cargoUsuario = cargoUsuario;
	}

}
