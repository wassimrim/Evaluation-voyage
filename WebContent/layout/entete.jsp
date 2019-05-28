<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"  %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="ISO-8859-1">
<title>Evaluation de voyage</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Template-source/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Template-source/css/style2.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Template-source/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Template-source/css/global.css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/Template-source/js/popper.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Template-source/js/jquery-3.3.1.slim.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Template-source/js/bootstrap.min.js"></script>
</head>
<body>

 <c:set var = "prenom" scope = "session" value = "${util.prenom}"/>
 <c:set var = "nom" scope = "session" value = "${util.nom}"/>
 
 <c:if test = "${(prenom != null)  && (nom != null)}">
<div class="row float-right">
	<div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
  <i class="fa fa-user"></i> ${util.prenom}    ${util.nom } 
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
  <c:url var="lienDisconnect" value="AuthentificationController">
				<c:param name="authentification" value="DISCONNECT" />
  </c:url>
  
    <a class="dropdown-item" href="${lienDisconnect}" >deconnecter</a>
    
  </div>
</div>
</div>
</c:if>
<div class="clearfix"></div>
