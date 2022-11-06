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
		<%
			HttpSession sesion = request.getSession(false);
			String dui_usuario = sesion.getAttribute("dui").toString();
			String nombre = sesion.getAttribute("nombre").toString() + " "
					+ sesion.getAttribute("apellidos").toString();
			int user = (Integer) sesion.getAttribute("id_usuario");
			String correo_usuario = sesion.getAttribute("correo").toString();
		%>

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<jsp:include page="/top_navbar.jsp" />

				<div class="container-fluid">
					<h2>Comprar Cupon</h2>

					<form style="width: 900px;"
						action="${pageContext.request.contextPath}/promociones.do"
						method="POST">
						<input type="hidden" name="op" value="comprar"> <input
							type="hidden" name="id" id="id" value="${requestScope.id}">
						<label class="form-label mb-3">Ingrese número tarjeta:</label> <input
							type="text" class="form-control mb-3"
							placeholder="XXXX-XXXX-XXXX" required> <label
							class="form-label  mb-3">Ingrese nombre Tarjeta:</label> <input
							type="text" class="form-control mb-3"
							value="<%out.print(nombre);%>" required> <label
							class="form-label  mb-3">Fecha de vencimiento:</label> <input
							type="text" class="form-control mb-3" placeholder="MM/AA"
							required /> <label class="form-label  mb-3">CVC:</label> <input
							type="text" class="form-control mb-3" placeholder="XXX" required>
						<label class="form-label  mb-3">Ingrese cantidad de
							cupones:</label> <input type="number" name="cupon" step="1"
							placeholder="0" class="form-control" required> <input
							type="submit" class="btn btn-success mt-3 mb-3"
							value="Comprar cupones" style="width: 600px; margin-left: 20%">
						<input type="hidden" value="<%out.print(dui_usuario);%>"
							name="dui_usuario"> <input type="hidden"
							value="<%out.print(user);%>" name="idUser"> <input
							type="hidden" value="${requestScope.codigoEmpresa}"
							name="codigoEmpresa"> <input type="hidden"
							value="<%out.print(correo_usuario);%>" name="correoUsuario">
					</form>

				</div>
				<jsp:include page="/footer.jsp" />
			</div>
		</div>
	</div>


	<jsp:include page="/scripts.jsp" />
</body>
</html>