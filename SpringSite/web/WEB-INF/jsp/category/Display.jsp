
<%@taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator"%>
<%@taglib uri="/WEB-INF/sitemesh-page.tld" prefix="page" %>



<head><title>G�rer les cat�gories</title></head>
<h1>Liste des cat�gories</h1>
        <table>
            <thead>
                <tr><td>ID</td> <td>Nom</td><td>Description</td></tr>
            </thead>
            <tbody>
            <c:forEach items="${data}" var="cat" >
                <tr>
                    <td><c:out value="${cat.key}"/></td>
                    <td><c:out value="${cat.value.catName}"/></td>
                    <td><c:out value="${cat.value.description}"/></td>
                </tr>    
            
            </c:forEach>    
            </tbody>
            
             
        </table>
