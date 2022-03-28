<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <title>MovieRama</title>
    </head>
    <body>
        <table style="border-collapse: collapse; width: 100%;" border="0">
            <tbody>
                <tr>
                    <td style="width: 50%;">
                        <h1>MovieRama</h1>
                    </td>
                    <td style="width: 50%; padding-left: 80px;">
                        <sec:authorize access="isAuthenticated()">
                            <h2>Welcome back <sec:authentication property="name"/></h2>
                            <a href="/logout">Logout</a>
                        </sec:authorize>
                        <sec:authorize access="!isAuthenticated()">
                            <a href="/login">Login</a> or <a href="/registration">Sign up</a>
                        </sec:authorize>
                    </td>
                </tr>
            </tbody>
        </table>
        <table style="border-collapse: collapse; width: 100%;" border="1">
            <tbody>
                <tr>
                    <td style="width: 100%;">Sort by: <a href="/movies/sort?type=likes">Likes</a> | <a
                            href="/movies/sort?type=hates">Hates</a> | <a href="/movies/sort?type=publicationDate">Date</a>
                    </td>
                </tr>
            </tbody>
        </table>
        &nbsp;

        <table style="border-collapse: collapse; width: 100%;" border="0">
            <tbody>
            <c:forEach items="${movies}" var="movie" varStatus="loopStatus">
                <tr>
                    <td style="width: 50%;">
                        <div style="border-style:solid">
                            <table style="width: 100%; border-collapse: collapse;" border="0">
                                <tbody>
                                    <tr>
                                        <td style="width: 70%;">
                                            <h3><c:out value="${movie.title}"/></h3>
                                            <td style="width: 30%;">&nbsp;</td>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 70%;">Posted by <a href="movies/list?username=${movie.username}">${movie.username}</a> ${movie.publicationDateDifference}</td>
                                        <td style="width: 30%;">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 70%;">&nbsp;</td>
                                        <td style="width: 30%;">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 70%;"><c:out value="${movie.description}"/></td>
                                        <td style="width: 30%;">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 70%;">&nbsp;</td>
                                        <td style="width: 30%;">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 70%;">
                                            <c:choose>
                                                <c:when test="${movie.likes=='0' && movie.hates=='0'}">
                                                Be the first to vote for this movie:
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value="${movie.likes}"/> likes | <c:out value="${movie.hates}"/> hates
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td style="width: 30%;" colspan="2">
                                            <c:choose>
                                                <c:when test="${movie.likedByUser==true}">
                                                    You liked this movie <a href="/movies/rate?like=false&movieId=${movie.id}">Hate</a>
                                                </c:when>
                                                <c:when test="${movie.hatedByUser}">
                                                    You hated this movie <a href="/movies/rate?like=true&movieId=${movie.id}">Like</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <sec:authorize access="isAuthenticated()">
                                                        <a href="/movies/rate?like=true&movieId=${movie.id}">Like</a>
                                                        |
                                                        <a href="/movies/rate?like=false&movieId=${movie.id}">Hate</a>
                                                    </sec:authorize>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 70%;">&nbsp;</td>
                                        <td style="width: 30%;">&nbsp;</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div>&nbsp;</div>
                    </td>
                    <c:if test="${loopStatus.count==1}">
                        <td width="30%" style="vertical-align: center; horizontal-align: center;text-align: center;">
                            <h2><a href="/movies/new">New Movie</a></h2>
                        </td>
                    </c:if>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>