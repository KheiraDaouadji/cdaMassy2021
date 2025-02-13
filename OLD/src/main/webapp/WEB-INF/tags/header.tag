<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="En-t�te des pages"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title" required="true" description="titre de la page" %>
<!DOCTYPE html>
<html>
  <head>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cdamassy2021.css"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
  </head>
  <body>
    <c:set var="context" value="${pageContext.servletContext.contextPath}"/>
    <form id="resetDbForm" action="${context}/resetDb" method="post">
    </form>
    <nav id="mainNav">
      <nav>
        <a href="${context}/.">Accueil</a>
        <a href="javascript: document.getElementById('resetDbForm').submit()">R�initialiser
          la BD</a>
        <a href="${context}/canaux">Canaux</a>
        <a href="${context}/activitequestions">Mes Questions</a>
      </nav>
      <nav>
        <c:if test="${sessionScope['user'] != null}">
          <form action="${context}/connexion" method="post">
            <button type="submit">D�connecter ${sessionScope['user'].email}</button>
            <input type="hidden" name="action" value="deconnecter"/>
          </form>
        </c:if>
        <c:if test="${sessionScope['user'] == null}">
          <a href="${context}/inscription">Inscription</a>
          <a href="${context}/connexion">Connexion</a>
        </c:if>
        ${applicationScope["nbUtilisateurs"]} utilisateurs
        <br/>
        ${applicationScope["nbIdentifies"]} identifi�s
      </nav>
    </nav>      