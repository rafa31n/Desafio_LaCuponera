<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cupones</title>
<%@ include file='/cabecera.jsp'%>
</head>
<body id="page-top">

	<div id="wrapper">
		<jsp:include page="/dashboard.jsp" />

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<jsp:include page="/top_navbar.jsp" />

				<div class="container-fluid">
					<h2>Lista de Cupones</h2>

					<table class="table table-striped table-bordered table-hover"
						id="dataTable">
						<thead>
							<tr>
								<th>ID cupon</th>
								<th>ID oferta</th>
								<th>Estado</th>
								<th>Codigó</th>
								<th>DUI</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.listaCupones}" var="cupones">
								<tr>
									<td>${cupones.id_cupon}</td>
									<td>${cupones.id_oferta}</td>
									<td>${cupones.estado_cupon}</td>
									<td>${cupones.codigo_cupon}</td>
									<td>${cupones.dui}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<jsp:include page="/footer.jsp" />
			</div>
		</div>
	</div>
	<jsp:include page="/scripts.jsp" />
</body>

</html>