<ul class="navbar-nav sidebar sidebar-dark accordion"
	id="accordionSidebar">
	<a
		class="sidebar-brand d-flex align-items-center justify-content-center"
		href="${pageContext.request.contextPath}/home.jsp">
		<div class="sidebar-brand-icon">
			<i class="fas fa-solid fa-store"></i>
		</div>
		<div class="sidebar-brand-text mx-3">La Cuponera</div>
	</a>


	<hr class="sidebar-divider my-0">

	<li class="nav-item active"><a class="nav-link"
		href="${pageContext.request.contextPath}/home.jsp"> <i
			class="fas fa-fw fa-tachometer-alt"></i> <span>Inicio</span>
	</a></li>

	<hr class="sidebar-divider">

	<!-- ADMINISTRADOR LA CUPONERA -->
	<%
		HttpSession sesion = request.getSession(false);
		int cargoSesion = (Integer) sesion.getAttribute("id_cargo");
		int empresa = (Integer) sesion.getAttribute("id_empresa");
		int user = (Integer) sesion.getAttribute("id_usuario");

		if (cargoSesion == 1) {
	%>
	<div class="sidebar-heading">Administrador</div>

	<li class="nav-item"><a class="nav-link"
		href="${pageContext.request.contextPath}/empresas.do?op=listar"> <i
			class="fas fa-solid fa-building"></i><span>Empresas</span>
	</a></li>

	<li class="nav-item"><a class="nav-link"
		href="${pageContext.request.contextPath}/rubros.do?op=listar"> <i
			class="fas fa-solid fa-briefcase"></i><span>Rubros</span>
	</a></li>

	<li class="nav-item"><a class="nav-link"
		href="${pageContext.request.contextPath}/usuarios.do?op=listarClientes">
			<i class="fas fa-solid fa-users"></i><span>Clientes</span>
	</a></li>

	<li class="nav-item"><a class="nav-link"
		href="${pageContext.request.contextPath}/usuarios.do?op=listar"> <i
			class="fas fa-solid fa-user"></i><span>Usuarios</span>
	</a></li>

	<li class="nav-item"><a class="nav-link"
		href="${pageContext.request.contextPath}/promociones.do?op=listarPromociones"><i
			class="fas fa-solid fa-check"></i><span>Aprobar/Rechazar</span> </a></li>

	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseOne" aria-expanded="true"
		aria-controls="collapseTwo"><i class="fas fa-regular fa-percent"></i>
			<span>Ofertas</span> </a>
		<div id="collapseOne" class="collapse" aria-labelledby="headingTwo"
			data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<a class="collapse-item"
					href="${pageContext.request.contextPath}/ofertas.do?op=listarEsperaAdmin">En
					espera de aprobación</a> <a class="collapse-item"
					href="${pageContext.request.contextPath}/ofertas.do?op=listarAprobadasAdmin">Aprobadas
					futuras</a> <a class="collapse-item"
					href="${pageContext.request.contextPath}/ofertas.do?op=listarActivasAdmin">Activas</a>

				<a class="collapse-item"
					href="${pageContext.request.contextPath}/ofertas.do?op=listarPasadasAdmin">Pasadas</a>

				<a class="collapse-item"
					href="${pageContext.request.contextPath}/ofertas.do?op=listarRechazadasAdmin">Rechazadas</a>

				<a class="collapse-item"
					href="${pageContext.request.contextPath}/ofertas.do?op=listarDescartadasAdmin">Descartadas</a>
			</div>
		</div></li>
	<%
		} else if (cargoSesion == 2) {
	%>
	<hr class="sidebar-divider">

	<!-- ADMINISTRADOR EMPRESA OFERTANTE -->

	<div class="sidebar-heading">Administrador de empresa ofertante</div>

	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseOne" aria-expanded="true"
		aria-controls="collapseTwo"><i class="fas fa-regular fa-percent"></i>
			<span>Ofertas</span> </a>
		<div id="collapseOne" class="collapse" aria-labelledby="headingTwo"
			data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<a class="collapse-item"
					href="${pageContext.request.contextPath}/ofertas.do?op=listarEspera&empresaId=<%out.print(empresa);%>">En
					espera de aprobación</a> <a class="collapse-item"
					href="${pageContext.request.contextPath}/ofertas.do?op=listarAprobadas&empresaId=<%out.print(empresa);%>">Aprobadas
					futuras</a> <a class="collapse-item"
					href="${pageContext.request.contextPath}/ofertas.do?op=listarActivas&empresaId=<%out.print(empresa);%>">Activas</a>

				<a class="collapse-item"
					href="${pageContext.request.contextPath}/ofertas.do?op=listarPasadas&empresaId=<%out.print(empresa);%>">Pasadas</a>

				<a class="collapse-item"
					href="${pageContext.request.contextPath}/ofertas.do?op=listarRechazadas&empresaId=<%out.print(empresa);%>">Rechazadas</a>

				<a class="collapse-item"
					href="${pageContext.request.contextPath}/ofertas.do?op=listarDescartadas&empresaId=<%out.print(empresa);%>">Descartadas</a>
			</div>
		</div></li>

	<li class="nav-item"><a class="nav-link"
		href="${pageContext.request.contextPath}/usuarios.do?op=listarDependientes&empresaId=<%out.print(empresa);%>">
			<i class="fas fa-solid fa-user"></i><span>Dependiente de
				sucursal</span>
	</a></li>
	<%
		} else if (cargoSesion == 3) {
	%>
	<!-- CLIENTE -->
	<hr class="sidebar-divider d-none d-md-block">


	<div class="sidebar-heading">Clientes</div>

	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true"
		aria-controls="collapseTwo"> <span>Cupones</span>
	</a>
		<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
			data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<a class="collapse-item"
					href="${pageContext.request.contextPath}/promociones.do?op=listar">Comprar
					cupones</a> <a class="collapse-item"
					href="${pageContext.request.contextPath}/usuarios.do?op=verMisCupones&id=<%out.print(user);%>">Ver
					mis cupones</a>
			</div>
		</div></li>
	<%
		}
	%>
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>

</ul>