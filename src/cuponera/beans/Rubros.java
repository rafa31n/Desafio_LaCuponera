package cuponera.beans;

public class Rubros {
	private int idRubro;
	private String nombreRubro;

	public Rubros() {
		this.idRubro = 0;
		this.nombreRubro = "";
	}

	public Rubros(int idRubro, String nombreRubro) {
		this.idRubro = idRubro;
		this.nombreRubro = nombreRubro;
	}

	public Rubros(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}

	public int getIdRubro() {
		return idRubro;
	}

	public void setIdRubro(int idRubro) {
		this.idRubro = idRubro;
	}

	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}

}
