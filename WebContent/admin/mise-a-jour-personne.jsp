 <%@ include file ="../layout/entete.jsp"  %>
	<div id="wrapper">
		<div id="header">
			<h2>Evaluation de voyage</h2>
		</div>
	</div>

	<div id="container">
		<h3> Administration :Mise a jour personne</h3>
		<form action="${pageContext.request.contextPath}/AdminController" method="GET">

			<input type="hidden" name="commande" value="MISEAJOUR" /> <input
				type="hidden" name="idPersonne" value="${Personne.idPersonne}" />
			<table>
				<tbody>
					<tr>
						<td><label>login:</label></td>
						<td><input type="text" name="login" value="${Personne.login}" /></td>
					</tr>
					<tr>
						<td><label>password:</label></td>
						<td><input type="text" name="password"
							value="${Personne.password}" /></td>
					</tr>
					<tr>
						<td><label>nom:</label></td>
						<td><input type="text" name="nom" value="${Personne.nom}" /></td>
					</tr>
					<tr>
						<td><label>prenom:</label></td>
						<td><input type="text" name="prenom"
							value="${Personne.prenom}" /></td>
					</tr>
					<tr>
						<td><label>adresse:</label></td>
						<td><input type="text" name="adresse"
							value="${Personne.adresse}" /></td>
					</tr>
					<tr>
						<td><label>telephone:</label></td>
						<td><input type="text" name="telephone"
							value="${Personne.tel}" /></td>
					</tr>
					<tr>
						<td><label>ville:</label></td>
						<td><input type="text" name="ville" value="${Personne.ville}" /></td>
					</tr>
					<tr>
						<td><label>pays:</label></td>
						<td><input type="text" name="pays" value="${Personne.pays}" /></td>
					</tr>
					<tr>
						<td><label>mail:</label></td>
						<td><input type="text" name="mail" value="${Personne.mail}" /></td>
					</tr>
					<tr>
						<td><label>role:</label></td>
						<td><input type="text" name="role" value="${Personne.role}" /></td>
					</tr>
					<tr>
						<td><label>photo profile:</label></td>
						<td><input type="text" name="photo_profile"
							value="${Personne.photoProfile}" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Enregistrer" class="enregistrer" /></td>
					</tr>
				</tbody>

			</table>
		</form>

		<div style="clear: both;"></div>
		
		<div>
		<input type="button" value="Aller vers gestion personnes"
				onClick="window.location.href='${pageContext.request.contextPath}/AdminController';return false;"
				class="btn btn-success" />
		</div>
	</div>
<%@ include file= "../layout/pieddepage.jsp" %>