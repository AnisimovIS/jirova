<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
<body ng-app="myApp">

<div class="container" ng-controller="userCtrl" ng-init="init()">

    <h3>Users</h3>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Edit</th>
            <th>First Name</th>
            <th>Password</th>
            <th>Groups</th>
            <th>Roles</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="user in users">
            <td>
                <button class="btn" ng-click="editUser(user.id)">
                    <span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Edit
                </button>
            </td>
            <td>{{ user.login }}</td>
            <td>{{ user.password }}</td>
            <td><dd ng-repeat="group in user.groups">{{ group.name }}</dd><br/></td>
            <td><dd ng-repeat="role in user.roles">{{ role.name }}</dd><br/></td>
            <td><dd ng-repeat="status in user.status">{{ status.name }}</dd><br/></td>
        </tr>
        </tbody>
    </table>

    <hr>
    <button class="btn btn-success" ng-click="editUser('new')">
        <span class="glyphicon glyphicon-user"></span> Create New User
    </button>
    <hr>

    <h3 ng-show="edit">Create New User:</h3>

    <h3 ng-hide="edit">Edit User:</h3>

    <form class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-2 control-label">First Name:</label>

            <div class="col-sm-10">
                <input type="text" ng-model="login" ng-disabled="!edit" placeholder="First Name">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Password:</label>

            <div class="col-sm-10">
                <input type="password" ng-model="password" placeholder="Password">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Repeat:</label>

            <div class="col-sm-10">
                <input type="password" ng-model="password2" placeholder="Repeat Password">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Groups:</label>
            <div class="col-sm-10">
                <select ng-model="available_groups" ng-options="a.id as a.name for a in available_groups track by a.id"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Roles:</label>
            <div class="col-sm-10">
                <select ng-model="available_roles" ng-options="a.id as a.name for a in available_roles"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Status:</label>
            <div class="col-sm-10">
                <select ng-model="available_status" ng-options="a.id as a.name for a in available_status"/>
            </div>
        </div>

    </form>

    <hr>
    <button class="btn btn-success" ng-disabled="error || incomplete">
        <span class="glyphicon glyphicon-save"></span> Save Changes
    </button>
</div>

<script src="/resources/js/app.js"></script>
</body>
</html>