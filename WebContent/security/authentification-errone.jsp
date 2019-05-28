<%@ include file ="../layout/entete.jsp"  %>

<center>
<div class="alert alert-danger  mx-auto">
  <h4 class="alert-heading">Authentification incorrecte :</h4>
  <p>votre login ou mot de passe est incorrecte :</p>
  <hr>
  <p class="mb-0"><input type="button" value="retour page login"
				onClick="window.location.href='security/login.jsp';return false;"
				/></p>
</div>

</center>


<%@ include file ="../layout/pieddepage.jsp"  %>