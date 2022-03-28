<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>MovieRama</title>
</head>
<body>
<form:form action="/registration" modelAttribute="userDto" method="POST" enctype="utf8">
    <table>
        <tr>
            <td><form:label path="firstName">First name</form:label></td>
            <td><form:input path="firstName"/></td>
            <td><form:errors path="firstName" cssClass="error" /></td>
        </tr>
        <tr>
            <td><form:label path="lastName">Last name</form:label></td>
            <td><form:input path="lastName"/></td>
            <td><form:errors path="lastName" cssClass="error" /></td>
        </tr>
        <tr>
            <td><form:label path="userName">username</form:label></td>
            <td><form:input path="userName"/></td>
            <td><form:errors path="userName" cssClass="error" /></td>
        </tr>
        <tr>
            <td><form:label path="password">Password</form:label></td>
            <td><form:password path="password"/></td>
            <td><form:errors path="password" cssClass="error" /></td>
        </tr>
        <tr>
            <td><form:label path="matchingPassword">Confirm password</form:label></td>
            <td><form:password path="matchingPassword"/></td>
            <td><form:errors path="matchingPassword" cssClass="error" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>

</html>