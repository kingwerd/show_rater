<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
                <h2><c:out value="${show.title}" /></h2>
                <h5><c:out value="${show.network}" /></h5>
            </div>
            <div class="col-2">
                <a href="/logout" class="btn btn-danger" role="button">Logout</a>
                <a href="/home" class="btn btn-primary" role="button">Home</a>
            </div>
        </div>
        <div class="row mb-5">
            <div class="col-12">
                <h5>Users Who Rated This Show</h5>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Rating</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${show.showRatings}" var="r">
                            <tr>
                                <td>${r.user.name}</td>
                                <td>${r.rate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <a href="/shows/${show.id}/edit" class="btn btn-primary">Edit</a>
            </div>
        </div>
        <div class="row">
            <div class="col-8">
                <span class="text-danger">${error}</span>
                <form:form action="/ratings" method="post" class="row" modelAttribute="rating">
                    <div class="col-1">
                        <div class="form-group row">
                            <form:label path="rate"></form:label>
                            <form:select path="rate" class="form-control">
                                <form:option value="5">5</form:option>
                                <form:option value="4">4</form:option>
                                <form:option value="3">3</form:option>
                                <form:option value="2">2</form:option>
                                <form:option value="1">1</form:option>
                            </form:select>
                        </div>
                    </div>
                    <div class="col-6">
                        <input type="hidden" name="show_id" id="show_id" value="${show.id}">
                        <button type="submit" class="btn btn-success">Rate ${show.title}</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>