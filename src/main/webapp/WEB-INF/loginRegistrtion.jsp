<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login and Registration</title>
    <link rel='stylesheet' href='/webjars/bootstrap/css/bootstrap.min.css'>
</head>
<body>
    <div class="container">
        <div class="row mt-5">
            <div class="col-12">
                <h1>Welcome!</h1>
                <span class="text-danger">${error}</span>
            </div>
        </div>
        <div class="row mt-2">
            <div class="col-6">
                <h1>Register</h1>
                <form action="/register" method="POST">
                    <span class="text-danger">${errors.name}</span>
                    <div class="form-group row">
                        <label for="name" class="col-sm-3 col-form-label">Name: </label>
                        <div class="col-sm-9">
                            <input type="text" name="name" id="name" class="form-control">
                        </div>
                    </div>
                    <span class="text-danger">${errors.email}</span>
                    <div class="form-group row">
                        <label for="email" class="col-sm-3 col-form-label">Email: </label>
                        <div class="col-sm-9">
                            <input type="email" name="email" id="email" class="form-control">
                        </div>
                    </div>
                    <span class="text-danger">${errors.password}</span>
                    <div class="form-group row">
                        <label for="password" class="col-sm-3 col-form-label">Password: </label>
                        <div class="col-sm-9">
                            <input type="password" name="password" id="password" class="form-control">
                            <small class="form-text text-muted">
                                * Password should be at least 8 characters
                            </small>
                        </div>
                    </div>
                    <span class="text-danger">${errors.confirm}</span>
                    <div class="form-group row">
                        <label for="confirm" class="col-sm-3 col-form-label">Confirm PW: </label>
                        <div class="col-sm-9">
                            <input type="password" name="confirm" id="confirm" class="form-control">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-success">Register</button>
                </form>
            </div>
            <div class="col-6">
                <h1>Login</h1>
                <form action="/login" method="POST">
                    <span class="text-danger">${errors.loginEmail}</span>
                    <div class="form-group row">
                        <label for="loginEmail" class="col-sm-3 col-form-label">Email</label>
                        <div class="col-sm-9">
                            <input type="email" name="loginEmail" id="loginEmail" class="form-control">
                        </div>
                    </div>
                    <span class="text-danger">${errors.loginPassword}</span>
                    <div class="form-group row">
                        <label for="loginPassword" class="col-sm-3 col-form-label">Password</label>
                        <div class="col-sm-9">
                            <input type="password" name="loginPassword" id="loginPassword" class="form-control">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-success">Log In</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
