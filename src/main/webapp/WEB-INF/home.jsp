<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Shows</title>
    <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
</head>
<body>
    
    <div class="container">

        <div class="row mt-5">
            <div class="col-10">
                <h2>Hello, <c:out value="${user.name}" /></h2>
            </div>
            <div class="col-2">
                <a href="/logout" class="btn btn-danger" role="button">Logout</a>
            </div>
        </div>

        <div class="row mb-5">
            <div class="col-12">
                <h5>TV Shows</h5>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Show</th>
                            <th scope="col">Network</th>
                            <th scope="col">Average Rating</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${shows}" var="s">
                            <tr>
                                <td><a href="/shows/${s.id}"><c:out value="${s.title}" /></a></td>
                                <td><c:out value="${s.network}" /></td>
                                <td><c:out value="${s.averageRating}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <a href="/new" class="btn btn-primary">Add A Show</a>
            </div>
        </div>
    </div>
</body>
</html>