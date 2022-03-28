<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>MovieRama</title>
</head>
<body>
<form:form action="/movies/newMovie" modelAttribute="movieDto" method="POST" enctype="utf8">
<table style="border-collapse: collapse; width: 100%;" border="0">
        <tr>
            <td style="width: 10%;"><form:label path="title">Title</form:label></td>
            <td style="width: 70%;"><form:input path="title" size="60"/></td>
            <td style="width: 20%;"><form:errors path="title" cssClass="error" /></td>
        </tr>
        <tr>
            <td style="width: 10%;"><form:label path="description">Description</form:label></td>
            <td style="width: 70%;"><form:textarea path="description" rows="5" cols="50"/></td>
            <td style="width: 20%;"><form:errors path="description" cssClass="error" /></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>

</html>