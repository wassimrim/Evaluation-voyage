 <%@ include file ="../layout/entete.jsp"  %>
	<div id="wrapper">
		<div id="header">
			<h2>administration :Ajout voyage</h2>
		</div>
	</div>

	<div id="container">
		<h3>Ajout Voyage</h3>
		<form action="../VoyageController" method="GET">

			<input type="hidden" name="commande" value="AJOUT" />
			<table>
				<tbody>
					<tr>
						<td><label>labelle:</label></td>
						<td><input type="text" name="labelle" /></td>
					</tr>
					<tr>
						<td><label>pays:</label></td>
						<td><input type="text" name="pays" /></td>
					</tr>

					<tr>
						<td><label>description:</label></td>
						<td><input type="text" name="description" /></td>
					</tr>
					
					<tr>
						<td><label>image:</label></td>
						<td><input type="text" name="image" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="enregistrer" class="enregistrer" /></td>
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