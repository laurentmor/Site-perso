<%@taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator"%>
<%@taglib uri="/WEB-INF/sitemesh-page.tld" prefix="page" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<decorator:usePage id="mainPage"/>
<html>
    <head>
        <title>Blog::<decorator:title default="Titre par defaut"/></title>
        <decorator:head/>
        
    </head>
<body>
    <page:apply-decorator name="empty" page="/template/header.jsp" />
    <decorator:body/>
    

</body>
</html>