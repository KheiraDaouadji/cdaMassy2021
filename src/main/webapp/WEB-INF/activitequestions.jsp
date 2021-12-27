<%-- 
    Document   : activitequestions
    Created on : 27 d�c. 2021, 03:31:56
    Author     : thoma
--%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<p:header title="Activit�: Mes questions."/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/questionaire_style.css"/>
<%-- Message d'erreur ou pas, au cas o� --%>
${msg}
<h1>Activit�: Mes questions.</h1>
<section class="flexh">
    <p>Ceci est votre espace de travail depuis lequel vous pouvez editer, 
        gerer, afficher ou reproposer des questions que vous avez d�ja pos�
        ainsi qu'en creer de nouvelles.</p>
    <a href="http://dev.formationgreta91.fr:8080/cdamassy2021/">Version en ligne</a>
</p>
</section>
<section id="index_articles">
    <article>
        <h3>Afficher vos questions</h3>
        <p>Affiche la liste des questions que vous avez d�j� pos�es.</p>
       <form action="ListerQuestionsUtilisateurCourant"class="boutonActivite" >
         <button type="submit">Afficher Questions</button>
       </form>


    </article>

    <article>
        <h3>Poser une question</h3>
        <p>Proposez une nouvelle question sur un canal.</p>
        <form action="creationQuestion"class="boutonActivite" >
         <button type="submit">Nouvelle Question</button>
        </form>

    </article>

    <article style="clear:left;">
        <h3>Editer une question</h3>
        <p>En cours d'impl�mentation</p>
    </article>
    <p:footer/>