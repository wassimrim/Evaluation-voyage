 <%@ include file ="../layout/entete.jsp"  %>
	<div id="wrapper">
		<div id="header">
			<h2>Administration  : Mise a jour voyage</h2>
		</div>
	</div>

	<div id="container">
		<h3>Mise a jour voyage</h3>
		<form action="VoyageController" method="GET">

			<input type="hidden" name="commande" value="MISEAJOUR" /> <input
				type="hidden" name="idVoyage" value="${Voyage.idVoyage}" />
			<table class ="table">
				<tbody>
					<tr>
						<td><label>labelle:</label></td>
						<td><input type="text" name="labelle"
							value="${Voyage.labelle}" /></td>
					</tr>
					<tr>
						<td><label>pays:</label></td>
						<td><input type="text" name="pays" value="${Voyage.pays}" /></td>
					</tr>
					<tr>
						<td><label>description:</label></td>
						<td><input type="text" name="description"
							value="${Voyage.description}" /></td>
					</tr>
					
					<tr>
						<td><label>image:</label></td>
						<td><input type="text" name="image"
							value="${Voyage.image}" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Enregistrer" class="enregistrer" /></td>
					</tr>
				</tbody>

			</table>
		</form>

		<div style="clear: both;"></div>
		<input type="button" value="retour liste voyage"
				onClick="window.location.href='${pageContext.request.contextPath}/VoyageController';return false;"
				class="btn btn-success" />
	</div>
<%@ include file= "../layout/pieddepage.jsp" %>