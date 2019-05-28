 <%@ include file ="../layout/entete.jsp"  %>
	<div id="wrapper">
		<div id="header">



			<h2>Administration :  gestion personnes</h2>
		</div>

	</div>
	<div id="container">
		<div id="content">
			
			<input type="button" value="Ajout personne"
				onClick="window.location.href='${pageContext.request.contextPath}/admin/ajout-personne.jsp';return false;"
				class="btn btn-success" />
				
			<table class = "table">

				<tr>
					<th>login</th>
					<th>password</th>
					<th>nom</th>
					<th>prenom</th>
					<th>adresse</th>
					<th>telephone</th>
					<th>ville</th>
					<th>pays</th>
					<th>mail</th>
					<th>role</th>
					<th>photo</th>
					<th>operation 1</th>
					<th>operation 2</th>

				</tr>
				<c:forEach var="personneincrement" items="${PERSONNE_LIST}">
					
					<c:url var="lienmiseajour" value="AdminController">
						<c:param name="commande" value="CHARGER" />
						<c:param name="idPersonne" value="${personneincrement.idPersonne}" />
					</c:url>

					
					<c:url var="liensuppression" value="AdminController">
						<c:param name="commande" value="SUPPRIMER" />
						<c:param name="idPersonne" value="${personneincrement.idPersonne}" />
					</c:url>

					<tr>
						<td>${personneincrement.login}</td>
						<td>${personneincrement.password}</td>
						<td>${personneincrement.nom}</td>
						<td>${personneincrement.prenom}</td>
						<td>${personneincrement.adresse}</td>
						<td>${personneincrement.tel}</td>
						<td>${personneincrement.ville}</td>
						<td>${personneincrement.pays}</td>
						<td>${personneincrement.mail}</td>
						<td>${personneincrement.role}</td>
						<td>${personneincrement.photoProfile}</td>
						<td><a href="${lienmiseajour}">Mise a jour</a> </td>
						<td><a href="${liensuppression}" onclick="if(!(confirm('êtes-vous sûr de suppreimer cette personne ?')))return false">Supprimer</a>
						</td>
					</tr>
				</c:forEach>

			</table>
			
			<div style="clear: both;"></div>
		<div>
		
		
		<input type="button" value="Retour vers acceuil admin"
				onClick="window.location.href='${pageContext.request.contextPath}/admin/menu-admin.jsp';return false;"
				class="btn btn-success" />
		
		<input type="button" value="Aller vers gestion voyage"
				onClick="window.location.href='VoyageController';return false;"
				class="btn btn-success" />
		</div>
	</div>
		</div>


<%@ include file= "../layout/pieddepage.jsp" %>