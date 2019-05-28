
<!--  pas encore terminé -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Evaluation voyage</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/style2.css">
</head>
<body>
qqqq
	<div id="wrapper">
		<div id="header">



			<h2>liste des voyages</h2>
		</div>

	</div>
	<div id="container">
		<div id="content">
			

			<table>

				<tr>
					<th>labelle</th>
					<th>pays</th>
					<th>description</th>
					<th>operation</th>
					


				</tr>
				<c:forEach var="voyageincrement" items="${VOYAGE_LIST}">
					
					<c:url var="lienmconsultation" value="VoyageController">
						<c:param name="commande" value="CONSULTER" />
						<c:param name="idVoyage" value="${voyageincrement.idVoyage}" />
					</c:url>

					<tr>
						<td>${voyageincrement.labelle}</td>
						<td>${voyageincrement.pays}</td>
						<td>${voyageincrement.description}</td>
						<td><a href="${lienconsultation}">Consulter</a> 
						</td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>



</body>
</html>