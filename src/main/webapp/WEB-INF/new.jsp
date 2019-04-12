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
    <title>New Show</title>
    <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
</head>
<body>
    
    <div class="container">
        <div class="row mt-5">
            <div class="col-10">
                <h2>Create A New Show</h2>
            </div>
            <div class="col-2">
                <a href="/logout" class="btn btn-danger" role="button">Logout</a>
                <a href="/home" class="btn btn-primary" role="button">Home</a>
            </div>
        </div>
        <div class="row">
            <div class="col-7">
                <form:form action="/shows" method="post" modelAttribute="show">
                    <form:errors path="title" class="text-danger" />
                    <div class="form-group row">
                        <form:label path="title" class="col-sm-3 col-form-label">Title: </form:label>
                        <div class="col-sm-9">
                            <form:input path="title" class="form-control" />
                        </div>
                    </div>
                    <form:errors path="network" class="text-danger" />
                    <div class="form-group row">
                        <form:label path="network" class="col-sm-3 col-form-label">Network: </form:label>
                        <div class="col-sm-9">
                            <form:input path="network" class="form-control" />
                        </div>
                    </div>
                    <button type="submit" class="btn btn-success">Create</button>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>