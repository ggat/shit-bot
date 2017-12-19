<!DOCTYPE html>
<html lang="en" ng-app="matcher">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Category Matcher</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="css/bootstrap-yeti.min.css">
    <link rel="stylesheet" href="css/chosen.css">
    <link rel="stylesheet" href="css/chosen-bootstrap.css">
    <link rel="stylesheet" href="css/chosen-bootstrap-yeti.css">
    <link rel="stylesheet" href="css/app.css">
    <style>
        body {
            background-color: #bdc3c7;
        }

        .table-fixed {
            width: 100%;
            background-color: #f3f3f3;
        }

        .table-fixed tbody {
            height: 200px;
            overflow-y: auto;
            width: 100%;
        }

        .table-fixed thead, .table-fixed tbody, .table-fixed tr, .table-fixed td, .table-fixed th {
            display: block;
        }

        .table-fixed tbody td {
            float: left;
        }

        .table-fixed thead tr th {
            float: left;
            background-color: #f39c12;
            border-color: #e67e22;
        }

    </style>
</head>
<body>
<!--<h1>Hello, world!</h1>-->

<ui-view></ui-view>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="node_modules/angular/angular.min.js"></script>
<script src="node_modules/underscore/underscore-min.js"></script>

<script src="node_modules/angular-ui-router/release/angular-ui-router.min.js"></script>
<!--Chosen with fuzzy search support.-->
<script src="js/lib/fuzzy-chosen/chosen-fuzzy.jquery.js"></script>
<!-- Ordinary chosen.-->
<!--<script src="js/lib/fuzzy-chosen/chosen-fuzzy.jquery.js"></script>-->
<script src="js/lib/angular-chosen.js"></script>
<script src="js/app.js"></script>
</body>
</html>