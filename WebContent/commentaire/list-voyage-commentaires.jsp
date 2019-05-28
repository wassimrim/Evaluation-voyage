
<!--  pas encore terminé -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>description</th>
			<th>personne</th>
			<th>voyage</th>
		</tr>
		<tr>
		</tr>
		<c:forEach var="commentaireincrement" items="${COMMENTAIRE_LIST}">
			
			<c:url var="lienmconsultation" value="VoyageController">
				<c:param name="commande" value="CONSULTER" />
				<c:param name="idVoyage" value="${voyageincrement.idVoyage}" />
			</c:url>
			</c:forEach>

			<tr>


			</tr>
	</table>
</body>
</html>