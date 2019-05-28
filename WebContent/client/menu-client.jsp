<!--  pas encore utilisé -->
<%@ include file ="../layout/entete.jsp"  %>


<div id="container">
<div id="content">
<div class="alert alert-primary  d-flex justify-content-center" role="alert">
<h1>Menu Client </h1>
</div>
</div>


<div class="alert alert-secondary d-flex justify-content-center" role="alert">
<input type="button" value="consulter voyages" onClick="window.location.href='${pageContext.request.contextPath}/VoyageController';return false;"/>


</div>

</div>
















<%@ include file= "../layout/pieddepage.jsp" %>