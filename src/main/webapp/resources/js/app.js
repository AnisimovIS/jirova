Array.prototype.subUser = function (elements) {
    var a = this;
    for (var j = 0; j < elements.length; ++j) {
        for (var i = 0; i < a.length; ++i) {
            if (elements[j].name == a[i].name) {
                a.splice(i--, 1);
            }
        }
    }
    return a;
}

var app = angular.module('myApp', []);

app.controller('userCtrl',  function ($scope) {
    $scope.login = '';
    $scope.password = '';
    $scope.password2 = '';
    $scope.users = null;
    $scope.roles = null;
    $scope.groups = null;
    $scope.status = null;
    $scope.edit = true;
    $scope.error = false;
    $scope.incomplete = false;
    $scope.available_roles = null;
    $scope.available_groups = null;
    $scope.available_status = null;

    $scope.init = function(){
        $scope.users = [
            {id: 1, login: 'Hege', password: "12345",
                roles:[{id:1,name:"role_admin",description:"roleAdmin"},{id:2,name:"role_user",description:"roleUser"},{id:3,name:"role_anonymous",description:"roleAnonymous"}],
                groups:[{id:1,name:"group_admin",description:"groupAdmin"},{id:2,name:"group_user",description:"groupUser"}],
                status:[]
            },
            {id: 2, login: 'Limberg', password: "12345",
                roles:[{id:1,name:"role_admin",description:"roleAdmin"},{id:2,name:"role_user",description:"roleUser"},{id:3,name:"role_anonymous",description:"roleAnonymous"}],
                groups:[{id:1,name:"group_admin",description:"groupAdmin"},{id:2,name:"group_user",description:"groupUser"}],
                status:[{id:1,name:"status_active",description:"statusActive"},{id:2,name:"status_open",description:"statusOpen"}]
            },

            {id: 3, login: 'Jack', password: "452",
                roles:[{id:2,name:"role_user",description:"roleUser"}],
                groups:[{id:1,name:"group_admin",description:"groupAdmin"},{id:2,name:"group_user",description:"groupUser"},{id:3,name:"group_layers",description:"groupLayers"}],
                status:[{id:1,name:"status_active",description:"statusActive"}]
            }
        ];
        $scope.roles = [{id:1,name:"role_admin",description:"roleAdmin"},{id:2,name:"role_user",description:"roleUser"},{id:3,name:"role_anonymous",description:"roleAnonymous"}];
        $scope.groups = [{id:1,name:"group_admin",description:"groupAdmin"},{id:2,name:"group_user",description:"groupUser"},{id:3,name:"group_anonymous",description:"groupAnonymous"}];
        $scope.status = [{id:1,name:"status_active",description:"statusActive"},{id:2,name:"status_open",description:"statusOpen"},{id:3,name:"status_disable",description:"statusDisable"}];
    }
    $scope.editUser = function (id) {
        if (id == 'new') {
            $scope.edit = true;
            $scope.incomplete = true;
            $scope.login = '';
            $scope.password = '';
            $scope.available_roles = $scope.roles;
            $scope.available_groups = $scope.groups;
            $scope.available_status = $scope.status;

        } else {
            $scope.edit = false;
            $scope.login= $scope.users[id - 1].login;
            $scope.password = $scope.users[id - 1].password;
            $scope.available_roles = $scope.roles.subUser($scope.users[id-1].roles);
            $scope.available_groups = $scope.groups.subUser($scope.users[id-1].groups);
            $scope.available_status = $scope.status.subUser($scope.users[id-1].status);
        }
    };

    $scope.$watch('password', function () {
        $scope.test();
    });
    $scope.$watch('password2', function () {
        $scope.test();
    });
    $scope.$watch('login', function () {
        $scope.test();
    });

    $scope.test = function () {
        if ($scope.password !== $scope.password2) {
            $scope.error = true;
        } else {
            $scope.error = false;
        }
        $scope.incomplete = false;
        if ($scope.edit && (!$scope.login.length  || !$scope.password.length || !$scope.password2.length)) {
            $scope.incomplete = true;
        }
    };

});