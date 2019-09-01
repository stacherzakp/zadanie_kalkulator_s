var app = angular.module('calculator', []);

app.controller('ctrl', function ($scope, $http) {

    $scope.isConfigLoaded = false;

    $http.get("calculator/config")
        .then(function (response) {
            $scope.systemCurrency = response.data.systemCurrency;
            $scope.countries = response.data.countries;
            $scope.isConfigLoaded = true;
        }, function (response) {
            $scope.message = response.body.message;
        });

    $scope.calculate = function (income, selectedCountry) {

        $http.post("calculator/net", {
            dailyIncome: income,
            currency: selectedCountry.currency
        }, {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {
                $scope.calculatedSalary = response.data.salary;
                $scope.calculatedSalaryCurrency = response.data.currency;
                $scope.message = "Salary calculated successfully."
            }, function (response) {
                $scope.message = response.body.message;
            });

    };
});