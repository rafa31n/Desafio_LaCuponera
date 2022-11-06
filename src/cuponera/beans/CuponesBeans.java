package cuponera.beans;

import java.io.Serializable;

public class CuponesBeans implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id_cupon;
	private int id_oferta;
	private int estado_cupon;
	private String codigo_cupon;
	private String dui;
	private int idUser;

	public CuponesBeans() {
		this.id_cupon = 0;
		this.id_oferta = 0;
		this.estado_cupon = 0;
		this.codigo_cupon = " ";
		this.dui = " ";
		this.idUser = 0;
	}

	public CuponesBeans(int id_cupon, int id_oferta, int estado_cupon, String codigo_cupon, String dui, int idUser) {

		this.id_cupon = id_cupon;
		this.id_oferta = id_oferta;
		this.estado_cupon = estado_cupon;
		this.codigo_cupon = codigo_cupon;
		this.dui = dui;
		this.idUser = idUser;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getId_cupon() {
		return id_cupon;
	}

	public void setId_cupon(int id_cupon) {
		this.id_cupon = id_cupon;
	}

	public int getId_oferta() {
		return id_oferta;
	}

	public void setId_oferta(int id_oferta) {
		this.id_oferta = id_oferta;
	}

	public int getEstado_cupon() {
		return estado_cupon;
	}

	public void setEstado_cupon(int estado_cupon) {
		this.estado_cupon = estado_cupon;
	}

	public String getCodigo_cupon() {
		return codigo_cupon;
	}

	public void setCodigo_cupon(String codigo_cupon) {
		this.codigo_cupon = codigo_cupon;
	}

	public String getDui() {
		return dui;
	}

	public void setDui(String dui) {
		this.dui = dui;
	}

}
