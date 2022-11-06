<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>La Cuponera</title>
<%@ include file='/cabecera.jsp'%>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark mb-4">
		<a class="navbar-brand" href="vistaPublica.jsp">La Cuponera</a> <span
			class="navbar-text p-0"> <a class="btn btn-outline-secondary"
			href="index.jsp">Iniciar Sesión</a>
		</span>
	</nav>

	<div class="container text-center">
		<h2>La Cuponera</h2>
		<br> <br> <i style="font-size: 7em;"
			class="fas fa-solid fa-store"></i><br> <br>
		<p>
			Puedes adquirir tus cupones de compra en La cuponera el mejor sitio
			para adquirir y gestionar las compras de tus compones, no lo pienses
			más ingresa y adquiérelos<br> <b>Bienvenido, estas son las
				ofertas disponibles:</b>
		</p>

		<br>

		<div id="contenedor-ofertas">
			<div id="ofertas">
				<%
					LocalDate fechaActual = LocalDate.now();
					String fechaString = fechaActual.toString();
				%>
				<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver"
					url="jdbc:mysql://localhost/cuponera_dwf" user="root" password=""></sql:setDataSource>
				<sql:query var="rs" dataSource="${db}">select * from ofertas o INNER JOIN empresa e ON e.id_empresa = o.id_empresa WHERE o.id_estado_oferta = 2 AND 
				(DATE('<%out.print(fechaString);%>') BETWEEN o.fecha_inicio AND o.fecha_fin);
				</sql:query>

				<c:forEach items="${rs.rows}" var="row">
					<div class="card shadow mb-4" style="width: 800px">
						<div
							class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary">Oferta - ${row.nombre_empresa}</h6>
						</div>

						<div class="card-body" style="width: 100%">
							<h5>
								<b>Titulo: </b> ${row.titulo_oferta}
							</h5>
							<p>
								<b>Descripción: </b>${row.descripcion_oferta}</p>
							<p>
								<b>Precio regular: $</b> ${row.precio_regular}
							</p>
							<p>
								<b>Precio Oferta: $</b> ${row.precio_oferta}
							</p>
						</div>
					</div>
				</c:forEach>
			</div>

		</div>

		<br> <br>
		<div class="d-grid gap-2">
			<a class="btn btn-primary" href="index.jsp">Adquiere tus cupones
				aquí</a>
		</div>
	</div>

	<jsp:include page="/footer.jsp" />
	<jsp:include page="/scripts.jsp" />

</body>
</html>