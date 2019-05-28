<%@ include file="../layout/entete.jsp"%>






<div class="container-fluid">



	<div class="card-group">


		<c:forEach var="voyageIncrement" items="${VOYAGE_LIST}">
			
			<c:url var="lienDetailCommentaire" value="CommentaireController">
				<c:param name="commande" value="CHARGERCOMMENTAIRE" />
				<c:param name="idVoyage" value="${voyageIncrement.idVoyage}" />
			</c:url>



			<div class="col-sm-3 mt-5">
				<div class="card" style="width: 18rem;">
					<img class="card-img-top" src="${pageContext.request.contextPath}/Template-source/images/${voyageIncrement.image}" alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title">${voyageIncrement.labelle }</h5>
						<p class="card-text">${voyageIncrment.description}</p>
						<a href="${lienDetailCommentaire}" class="btn btn-primary">consulter
							detail</a>
					</div>
				</div>
				</div>
		</c:forEach>

	</div>

	<div style="clear: both;"></div>

	<input type="button" value="Retour acceuil admin"
		onClick="window.location.href='${pageContext.request.contextPath}/admin/menu-admin.jsp';return false;"
		class="btn btn-success" />
</div>





















<%@ include file="../layout/pieddepage.jsp"%>