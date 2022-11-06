package cuponera.beans;

import java.sql.Date;

public class PromocionesBeans {

	private int id_oferta;
	private int id_estado_oferta;
	private int id_empresa;
	private int id_administrador_empresa;
	private String titulo_oferta;
	private float precio_regular;
	private float precio_oferta;
	private Date fecha_inicio;
	private Date fecha_fin;
	private Date fecha_limite;
	private int limite_cupones;
	private String descripcion_oferta;
	private int cupones_vendidos;
	private int cupones_disponibles;
	private float ingresos_totales;
	private float cargo_servicio;
	private String codigoEmpresa;

	public PromocionesBeans() {
		this.id_oferta = 0;
		this.id_estado_oferta = 0;
		this.id_empresa = 0;
		this.id_administrador_empresa = 0;
		this.titulo_oferta = " ";
		this.precio_regular = 0;
		this.precio_oferta = 0;
		this.fecha_inicio = null;
		this.fecha_fin = null;
		this.fecha_limite = null;
		this.limite_cupones = 0;
		this.descripcion_oferta = " ";
		this.cupones_vendidos = 0;
		this.cupones_disponibles = 0;
		this.ingresos_totales = 0;
		this.cargo_servicio = 0;
		this.codigoEmpresa = " ";
	}

	public PromocionesBeans(int id_oferta, int id_estado_oferta, int id_empresa, int id_administrador_empresa,
			String titulo_oferta, float precio_regular, float precio_oferta, Date fecha_incio, Date fecha_fin,
			Date fecha_limite, int limite_cupones, String descripcion_oferta, int cupones_vendidos,
			int cupones_disponibles, float ingresos_totales, float cargo_servicio, String codigoEmpresa) {

		this.id_oferta = id_oferta;
		this.id_estado_oferta = id_estado_oferta;
		this.id_empresa = id_empresa;
		this.id_administrador_empresa = id_administrador_empresa;
		this.titulo_oferta = titulo_oferta;
		this.precio_regular = precio_regular;
		this.precio_oferta = precio_oferta;
		this.fecha_inicio = fecha_incio;
		this.fecha_fin = fecha_fin;
		this.fecha_limite = fecha_limite;
		this.limite_cupones = limite_cupones;
		this.descripcion_oferta = descripcion_oferta;
		this.cupones_vendidos = cupones_vendidos;
		this.cupones_disponibles = cupones_disponibles;
		this.ingresos_totales = ingresos_totales;
		this.cargo_servicio = cargo_servicio;
		this.codigoEmpresa = codigoEmpresa;
	}

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public int getId_oferta() {
		return id_oferta;
	}

	public void setId_oferta(int id_oferta) {
		this.id_oferta = id_oferta;
	}

	public int getId_estado_oferta() {
		return id_estado_oferta;
	}

	public void setId_estado_oferta(int id_estado_oferta) {
		this.id_estado_oferta = id_estado_oferta;
	}

	public int getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(int id_empresa) {
		this.id_empresa = id_empresa;
	}

	public int getId_administrador_empresa() {
		return id_administrador_empresa;
	}

	public void setId_administrador_empresa(int id_administrador_empresa) {
		this.id_administrador_empresa = id_administrador_empresa;
	}

	public String getTitulo_oferta() {
		return titulo_oferta;
	}

	public void setTitulo_oferta(String titulo_oferta) {
		this.titulo_oferta = titulo_oferta;
	}

	public float getPrecio_regular() {
		return precio_regular;
	}

	public void setPrecio_regular(float precio_regular) {
		this.precio_regular = precio_regular;
	}

	public float getPrecio_oferta() {
		return precio_oferta;
	}

	public void setPrecio_oferta(float precio_oferta) {
		this.precio_oferta = precio_oferta;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public Date getFecha_limite() {
		return fecha_limite;
	}

	public void setFecha_limite(Date fecha_limite) {
		this.fecha_limite = fecha_limite;
	}

	public int getLimite_cupones() {
		return limite_cupones;
	}

	public void setLimite_cupones(int limite_cupones) {
		this.limite_cupones = limite_cupones;
	}

	public String getDescripcion_oferta() {
		return descripcion_oferta;
	}

	public void setDescripcion_oferta(String descripcion_oferta) {
		this.descripcion_oferta = descripcion_oferta;
	}

	public int getCupones_vendidos() {
		return cupones_vendidos;
	}

	public void setCupones_vendidos(int cupones_vendidos) {
		this.cupones_vendidos = cupones_vendidos;
	}

	public int getCupones_disponibles() {
		return cupones_disponibles;
	}

	public void setCupones_disponibles(int cupones_disponibles) {
		this.cupones_disponibles = cupones_disponibles;
	}

	public float getIngresos_totales() {
		return ingresos_totales;
	}

	public void setIngresos_totales(float ingresos_totales) {
		this.ingresos_totales = ingresos_totales;
	}

	public float getCargo_servicio() {
		return cargo_servicio;
	}

	public void setCargo_servicio(float cargo_servicio) {
		this.cargo_servicio = cargo_servicio;
	}

}
