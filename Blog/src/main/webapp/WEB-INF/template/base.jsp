<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix = "s" uri = "http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
  <head>
      <meta charset="UTF-8"/>
      <link rel="stylesheet"
            type="text/css" href="<c:url value="/resources/css/base-style.css"/>"/>
    <title>
        LMExpertise &nbsp; <decorator:title /> 
        <s:message code="s"/>
    </title>
        <decorator:head/>
  </head>
  <body>
      
    <div id="header">
        <%@include file="head.html" %>
    </div>
    <%@include file="menu.html" %>
    
        
      <decorator:body />
    
  </body>
</html>