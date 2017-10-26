<%-- 
    Document   : index
    Created on : 22/10/2017, 22:30:22
    Author     : murilo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="pageModule">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home - Run</title>
        <link rel="icon" href="resources/img/logo.png">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <link href="resources/css/custom.css" rel="stylesheet" type="text/css"/>
    </head>
    <body ng-controller="controllerIndex">
        
        <%@include file="WEB-INF/jspf/nav-bar.jspf" %>

        <div class="top-title">
            <div class="row widthHundred">
                <div class="col-md-12">
                    <h1 class="whiteC">MUBER</h1><br> 
                </div>
            </div>
            <div class="row widthHundred">
                <div class="col-md-12">
                    <a href="cadastroMotorista.jsp" id="btn-simple" class="btn btn-default  btn-custom btn-lg">Cadastrar Motorista</a>
                    <a href="cadastroPassageiro.jsp" id="btn-compound" class="btn btn-warning btn-custom btn-lg">Cadastrar Passageiro</a>
                </div>
            </div>
        </div>

        <section id="ranking" class="container-fluid content" >
            <div class="row">
                <div class="col-md-12">
                    <div class="table-ranking">
                        <h2 class="text-center ">Passageiros cadastrados</h2><hr class="bottom-line3"><br><br>
                        <table class="table table-hover table-responsive">
                        </table>
                    </div>
                </div>
            </div>


            <img class="lion" src="resources/img/coruja.png" alt=""/>
        </section>

        <section id="team" class="container-fluid " >
            <img class="images-cipo" src="resources/img/cipo.png" alt=""/>
            <div class="container">
                <div class="row container-equip">
                    <h2 class="text-center">Motoristas cadastrados</h2><hr>
                    <br><br>
                    <div class="col-md-3 member-team">
                    </div>
                    <div class="col-md-3 member-team" >
                    </div>
                    <div class="col-md-3 member-team">
                    </div>
                </div>
            </div>
            <img class="images-animals" src="resources/img/tubarao.png" alt=""/>

        </section>  

        <%@include file="WEB-INF/jspf/footer.jspf" %>
        
    </body>
</html>


