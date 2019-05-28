
<%@ include file="../layout/entete.jsp"%>
<div id="wrapper">
	<div id="header">
		<h2>Administration : Ajout Personne</h2>
	</div>
</div>


<div class="row  justify-content-center">
	
	
	
	<form action="${pageContext.request.contextPath}/AdminController"
		method="Get" >

		<input type="hidden" name="commande" value="AJOUT" />
		<table class="table">
			<tbody>
				<tr>
					<td><label>login:</label></td>
					<td><input type="text" name="login" /></td>
				</tr>
				<tr>
					<td><label>password:</label></td>
					<td><input type="text" name="password" /></td>
				</tr>

				<tr>
					<td><label>nom:</label></td>
					<td><input type="text" name="nom" /></td>
				</tr>
				<tr>
					<td><label>prenom:</label></td>
					<td><input type="text" name="prenom" /></td>
				</tr>
				<tr>
					<td><label>adresse:</label></td>
					<td><input type="text" name="adresse" /></td>
				</tr>
				<tr>
				<tr>
					<td><label>telephone:</label></td>
					<td><input type="text" name="telephone" /></td>
				</tr>
				<tr>
					<td><label>ville:</label></td>
					<td><input type="text" name="ville" /></td>
				</tr>
				<tr>
					<td><label>pays:</label></td>
					<td><input type="text" name="pays" /></td>
				</tr>
				<tr>
				<tr>
					<td><label>mail:</label></td>
					<td><input type="text" name="mail" /></td>
				</tr>
				<tr>
					<td><label>role:</label></td>


					<td><select name="role" class ="custom-select">
					        <option selected >Choisissez un role svp</option>
							<option value="ADMIN">Admin</option>
							<option value="USER">USER</option>
					</select></td>
				</tr>
				<tr>
					<td><label>photo:</label></td>
					<td><input type="text" name="photo_profile"/></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="enregistrer"
						class="enregistrer" /></td>
				</tr>
			</tbody>

		</table>
	</form>
	
	</div>	

	<div style="clear: both;"></div>
	<div>
		<input type="button" value="Aller vers gestion voyage"
			onClick="window.location.href='${pageContext.request.contextPath}/AdminController';return false;"
			class="btn btn-success" />
	</div>

<%@ include file="../layout/pieddepage.jsp"%>