package cuponera.beans;

public class Ofertas {
	private EstadosOfertas estado_oferta;
	private String empresa;
	private int idOferta;
	private String titulo;
	private double precio_regular;
	private double precio_oferta;
	private String fecha_inicio;
	private String fecha_fin;
	private String fecha_limite;
	private int limite_cupones;
	private String descripcion;
	private int cupones_vendidos;
	private int cupones_disponibles;
	private double ingresos_totales;
	private double cargo_servicio;
	private String imagen_oferta;
	private int idEstadoOferta;
	private int idEmpresa;
	private int idAdministradorEmpresa;
	private String justificacion;

	public Ofertas() {
		this.estado_oferta = null;
		this.empresa = "";
		this.idOferta = 0;
		this.titulo = "";
		this.precio_regular = 0;
		this.precio_oferta = 0;
		this.fecha_inicio = "";
		this.fecha_fin = "";
		this.fecha_limite = "";
		this.limite_cupones = 0;
		this.descripcion = "";
		this.cupones_vendidos = 0;
		this.cupones_disponibles = 0;
		this.ingresos_totales = 0;
		this.cargo_servicio = 0;
		this.imagen_oferta = "";
		this.idEstadoOferta = 0;
		this.idEmpresa = 0;
		this.idAdministradorEmpresa = 0;
		this.justificacion = "";
	}

	public Ofertas(EstadosOfertas estado_oferta, String empresa, int idOferta, String titulo, double precio_regular,
			double precio_oferta, String fecha_inicio, String fecha_fin, String fecha_limite, int limite_cupones,
			String descripcion, int cupones_vendidos, int cupones_disponibles, double ingresos_totales,
			double cargo_servicio, String imagen_oferta, int idEstadoOferta, int idEmpresa, int idAdministradorEmpresa,
			String justificacion) {
		this.estado_oferta = estado_oferta;
		this.empresa = empresa;
		this.idOferta = idOferta;
		this.titulo = titulo;
		this.precio_regular = precio_regular;
		this.precio_oferta = precio_oferta;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.fecha_limite = fecha_limite;
		this.limite_cupones = limite_cupones;
		this.descripcion = descripcion;
		this.cupones_vendidos = cupones_vendidos;
		this.cupones_disponibles = cupones_disponibles;
		this.ingresos_totales = ingresos_totales;
		this.cargo_servicio = cargo_servicio;
		this.imagen_oferta = imagen_oferta;
		this.idEstadoOferta = idEstadoOferta;
		this.idEmpresa = idEmpresa;
		this.idAdministradorEmpresa = idAdministradorEmpresa;
		this.justificacion = justificacion;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public int getIdEstadoOferta() {
		return idEstadoOferta;
	}

	public void setIdEstadoOferta(int idEstadoOferta) {
		this.idEstadoOferta = idEstadoOferta;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public int getIdAdministradorEmpresa() {
		return idAdministradorEmpresa;
	}

	public void setIdAdministradorEmpresa(int idAdministradorEmpresa) {
		this.idAdministradorEmpresa = idAdministradorEmpresa;
	}

	public int getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}

	public EstadosOfertas getEstado_oferta() {
		return estado_oferta;
	}

	public void setEstado_oferta(EstadosOfertas estado_oferta) {
		this.estado_oferta = estado_oferta;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPrecio_regular() {
		return precio_regular;
	}

	public void setPrecio_regular(double precio_regular) {
		this.precio_regular = precio_regular;
	}

	public double getPrecio_oferta() {
		return precio_oferta;
	}

	public void setPrecio_oferta(double precio_oferta) {
		this.precio_oferta = precio_oferta;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public String getFecha_limite() {
		return fecha_limite;
	}

	public void setFecha_limite(String fecha_limite) {
		this.fecha_limite = fecha_limite;
	}

	public int getLimite_cupones() {
		return limite_cupones;
	}

	public void setLimite_cupones(int limite_cupones) {
		this.limite_cupones = limite_cupones;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public double getIngresos_totales() {
		return ingresos_totales;
	}

	public void setIngresos_totales(double ingresos_totales) {
		this.ingresos_totales = ingresos_totales;
	}

	public double getCargo_servicio() {
		return cargo_servicio;
	}

	public void setCargo_servicio(double cargo_servicio) {
		this.cargo_servicio = cargo_servicio;
	}

	public String getImagen_oferta() {
		return imagen_oferta;
	}

	public void setImagen_oferta(String imagen_oferta) {
		this.imagen_oferta = imagen_oferta;
	}

}
