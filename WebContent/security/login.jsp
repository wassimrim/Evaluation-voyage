 <%@ include file ="../layout/entete.jsp"  %>

 <section class="container-fluid bg">
<br/><br/><br/><br><br/><br/>
              <section class="row justify-content-center">
                  <section class="col-12-col-sm-6 col-md-3">
              <form class="form-container" action="${pageContext.request.contextPath}/AuthentificationController"  method="GET" >
              <input type="hidden" name="authentification" value="authentifier" />
                <div class="form-group">
                  <label for="InputLogin">Login</label>
                  <input type="text" class="form-control" id="InputLogin" aria-describedby="loginHelp" placeholder="Enter login" name="login">
                
                </div>
                <div class="form-group">
                  <label for="InputMotdepasse">Mot de passe</label>
                  <input type="password" class="form-control" id="InputMotdepasse" placeholder="Mot de passe" name="password">
                </div>
                <div class="form-group form-check">
                </div>
                <button type="submit" class="btn btn-primary">Connecter</button>
              </form>
              </section>
            </section>
            </section>
 <%@ include file= "../layout/pieddepage.jsp" %>


 
 
 
