<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
    <title></title>
</head>
<body ng-app="myApp" ng-controller="userCtrl">

<h3></h3>
<table>
    <c:if test="${not empty users}">
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.login}"/></td>
                <td>
                    <c:if test="${not empty user.roles}">
                        <c:forEach var="p" items="${user.roles}">
                            <c:out value="${p.role_name}"/><br/>
                        </c:forEach>
                    </c:if>
                </td>
                <td>
                    <c:if test="${not empty user.groups}">
                        <c:forEach var="p" items="${user.groups}">
                            <c:out value="${p.group_name}"/><br/>
                        </c:forEach>
                    </c:if>
                </td>
                <td>
                    <c:if test="${not empty user.status}">
                        <c:forEach var="p" items="${user.status}">
                            <c:out value="${p.status_name}"/><br/>
                        </c:forEach>
                    </c:if>
                </td>

                <td><c:out value="${user.password}"/></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
