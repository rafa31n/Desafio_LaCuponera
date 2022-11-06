<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>La Cuponera</title>
<%@ include file='/cabecera.jsp'%>
</head>
<body id="page-top">

	<div id="wrapper">
		<jsp:include page="/dashboard.jsp" />

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<jsp:include page="/top_navbar.jsp" />

				<div class="container-fluid">
					<h2>Formulario para cambiar contraseña</h2>

					<div class=" col-md-7">

						<c:if test="${not empty listaErrores}">
							<div class="alert alert-danger">
								<ul>
									<c:forEach var="errores" items="${requestScope.listaErrores}">
										<li>${errores}</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
						<form role="form"
							action="${pageContext.request.contextPath}/usuarios.do"
							method="POST">
							<input type="hidden" name="op" value="cambiarContra"> 
							
							<input
								type="hidden" class="form-control" name="usuario" id="usuario"
								value="<%HttpSession sesion = request.getSession(false);
			out.print(session.getAttribute("id_usuario").toString());%>">

							<div class="form-group">
								<label for="passwordA">Antigua contraseña</label>
								<div class="input-group">
									<input type="password" class="form-control" name="passwordA"
										id="passwordA" required>
								</div>
							</div>

							<div class="form-group">
								<label for="passwordN">Nueva contraseña</label>
								<div class="input-group">
									<input type="password" class="form-control" name="passwordN"
										id="passwordN" required>
								</div>
							</div>

							<div class="form-group">
								<label for="passwordN2">Repetir contraseña nueva</label>
								<div class="input-group">
									<input type="password" class="form-control" name="passwordN2"
										id="passwordN2" required>
								</div>
							</div>



							<input type="submit" class="btn btn-info" value="Guardar"
								name="Guardar">
						</form>

					</div>
				</div>
				<br> <br> <br>
				<jsp:include page="/footer.jsp" />
			</div>
		</div>
	</div>

	<script>
		<c:if test="${not empty exito}">
		alertify.success('${exito}');
		<c:set var="exito" value="" scope="session" />
		</c:if>
		<c:if test="${not empty fracaso}">
		alertify.error('${fracaso}');
		<c:set var="fracaso" value="" scope="session" />
		</c:if>
	</script>

	<jsp:include page="/scripts.jsp" />
</body>
</html>