 <%@ include file ="../layout/entete.jsp"  %>

<div id="wrapper">
		<div id="header">

			<h2>Administration  : gestion voyages</h2>
		</div>

	</div>
	<div id="container">
		<div id="content">
			
			<input type="button" value="Ajout voyage"
				onClick="window.location.href='voyage/ajout-voyage.jsp';return false;"
				class="btn btn-success" />
			<table class ="table">

				<tr>
					<th>labelle</th>
					<th>pays</th>
					<th>description</th>
					<th>image</th>
					<th>operation 1</th>
					<th>operation 2</th>
                  
				</tr>
				<c:forEach var="voyageincrement" items="${VOYAGE_LIST}">
				
					<c:url var="lienmiseajour" value="VoyageController">
						<c:param name="commande" value="CHARGER" />
						<c:param name="idVoyage" value="${voyageincrement.idVoyage}" />
					</c:url>

					
					<c:url var="liensuppression" value="VoyageController">
						<c:param name="commande" value="SUPPRIMER" />
						<c:param name="idVoyage" value="${voyageincrement.idVoyage}" />
					</c:url>

					<tr>
						<td>${voyageincrement.labelle}</td>
						<td>${voyageincrement.pays}</td>
						<td>${voyageincrement.description}</td>
						<td>${voyageincrement.image}</td>
						<td><a href="${lienmiseajour}">mise a jour</a></td><td><a
							href="${liensuppression}"
							onclick="if(!(confirm('êtes-vous sûr de supprimer cette voyage ?')))return false">Supprimer</a>
						</td>
					</tr>
				</c:forEach>

			</table>
			
			<div style="clear: both;"></div>
	
		<input type="button" value="Retour acceuil admin"
				onClick="window.location.href='${pageContext.request.contextPath}/admin/menu-admin.jsp';return false;"
				class="btn btn-success" />
		</div>
	</div>

 <%@ include file= "../layout/pieddepage.jsp" %>