<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  ~ Copyright (c) 2024
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  ~
  ~
  --%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet"
          type="text/css" href="<c:url value="/resources/css/base-style.css"/>"/>
    <title>
        LMExpertise &nbsp; <decorator:title/>
        <s:message code="s"/>
    </title>
    <decorator:head/>
</head>
<body>

<div id="header">
    <%@include file="head.html" %>
</div>
<%@include file="menu.html" %>


<decorator:body/>

</body>
</html>