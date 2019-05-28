<%@ include file="../layout/entete.jsp"%>

<div class="container-fluid">

	<div class="d-flex justify-content-center">
		<h3>Detail du voyage</h3>
	</div>



	<table class="table table-bordered ">
		<thead>
			<tr>
				<th scope="col">Labelle</th>
				<th scope="col">Pays</th>
				<th scope="col">Description</th>
			</tr>
		</thead>
		<tbody>

			<tr>
				<td>${VOYAGE.labelle}</td>
				<td>${VOYAGE.pays }</td>
				<td>${VOYAGE.description }</td>
			</tr>



		</tbody>
	</table>
	
	<div class="row">
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#commentaireModal">Commenter voyage</button>
	</div>
	
	<div class="d-flex justify-content-center">
		<h3>Commentaires</h3>
	</div>



	<table class="table table-bordered ">
		<thead>
			<tr>
				<th scope="col">Utilisateur</th>
				<th scope="col">Commentaire</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="commentaire" items="${COMMENTAIRES_LIST}">
				<tr>
					<c:forEach var="personne" items="${PERSONNES_LIST}">
						<c:if test="${personne.idPersonne == commentaire.idPersonne }">
							<td>${personne.nom} ${personne.prenom}</td>
						</c:if>
					</c:forEach>
					<td>${commentaire.description}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div style="clear: both;"></div>

	<input type="button" value="Retour acceuil client"
		onClick="window.location.href='${pageContext.request.contextPath}/CommentaireController';return false;"
		class="btn btn-success" />
</div>



<!-- Modal -->
<div class="modal fade" id="commentaireModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Editeur message :</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
			<form action="${pageContext.request.contextPath}/CommentaireController"
		method="GET">

		<input type="hidden" name="commande" value="COMMENTAIRE" />
		<input type="hidden" name="idVoyage" value="${VOYAGE.idVoyage }" />
		<table class="table">
			<tbody>
				<tr>
					<td><label for="commentaire"><b>commentaire:</b></label></td>
					<td> <textarea class="form-control" rows="5" id="commentaire" name="commentaire"></textarea></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="commenter"
						class="enregistrer" /></td>
				</tr>
			</tbody>

		</table>
	</form>
			
			
			
			
			
			
			
			
			
			
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>



<%@ include file="../layout/pieddepage.jsp"%>