<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
      <link rel="stylesheet"
            type="text/css" href="<c:url value="/resources/css/base-style.css"/>"/>
    <title>
        LMExpertise &nbsp; <decorator:title /> 
    
    </title>
        <decorator:head/>
  </head>
  <body>
    <div id="header">
        <%@include file="head.html" %>
    </div>
    <%@include file="menu.html" %>
    <div>
        
      <decorator:body />
    </div>
  </body>
</html>