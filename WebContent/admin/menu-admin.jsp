 <%@ include file ="../layout/entete.jsp"  %>
 <div id="container">
<div id="content">
<div class="alert alert-primary  d-flex justify-content-center" role="alert">
<h1>Menu Admin </h1>
</div>
</div>


<div class="alert alert-secondary d-flex justify-content-center" role="alert">
<input type="button" value="Gestion utilisateurs" onClick="window.location.href='${pageContext.request.contextPath}/AdminController';return false;"/>
 <input type="button" value="Gestion voyages" onClick="window.location.href='${pageContext.request.contextPath}/VoyageController';return false;"/>
 <input type="button" value="Consultation liste commentaires" onClick="window.location.href='${pageContext.request.contextPath}/CommentaireController';return false;"/>

</div>

</div>

 <%@ include file= "../layout/pieddepage.jsp" %>