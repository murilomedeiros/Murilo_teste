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
        <link href="resources/css/custom.css" rel="stylesheet" type="text/css"/>
    </head>
    <body ng-controller="controllerIndex">
        <%@include file="WEB-INF/jspf/nav-bar.jspf" %>
        <div id="loader" class="loader"></div>
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

        <section id="table-clients" class="container-fluid content" >
            <div class="row">
                <div class="col-md-12">
                    <div class="table-ranking">
                        <h2 class="text-center ">Passageiros cadastrados</h2><hr class="bottom-line3"><br><br>
                        <div class="tables">
                            <div class="row">
                                <div class="col-md-4">
                                    <label>Pesquisar:</label>
                                    <input id="search-client" type="text" class="form-control">
                                </div> 
                                <div class="filter-input form-group col-md-2 col-md-offset-" >
                                    <label>Filtrar por:</label>
                                    <select  id="filter-client" class="form-control">
                                        <option value="td:first-child">Id</option>
                                        <option value="td:nth-child(2)">Nome</option> 
                                        <option value="td:nth-child(3)">CPF</option> 
                                        <option value="td:nth-child(4)">Sexo</option> 
                                    </select>     
                                </div>      
                            </div>
                            <table id="table-clnt" class="table table-data table-hover table-responsive">
                                <thead> 
                                    <tr>
                                        <th>Id</th>
                                        <th>Nome</th>
                                        <th>CPF</th>
                                        <th>Sexo</th>
                                    </tr>
                                </thead> 
                                <a ></a>
                                <tbody ng-cloak>
                                    <tr ng-repeat="client in clients">
                                        <td>{{::client.id}}</td>
                                        <td id="name">{{::client.name}}</td>
                                        <td>{{::client.cpf}}</td>
                                        <td>{{::client.gender}}</td>
                                    </tr>
                                </tbody>
                            </table>
                            <div ng-cloak   ng-show="viewC" class="alert alert-danger text-center">
                                <strong>Nenhum passageiro cadastrado.</strong> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section id="table-drivers" class="container-fluid " >
            <div class="container">
                <div class="row container-equip">
                    <h2 class="text-center">Motoristas cadastrados</h2><br><br>
                    <div class="tables">
                        <div class="row">
                            <div class="col-md-4">
                                <label>Pesquisar:</label>
                                <input id="search-driver" type="text" class="form-control">
                            </div> 
                            <div class="filter-input form-group col-md-2 col-md-offset-" >
                                <label>Filtrar por:</label>
                                <select  id="filter-driver" class="form-control">
                                    <option value="td:first-child">Id</option>
                                    <option value="td:nth-child(2)">Nome</option> 
                                    <option value="td:nth-child(3)">CPF</option> 
                                    <option value="td:nth-child(4)">Sexo</option> 
                                    <option value="td:nth-child(5)">Status</option> 
                                </select>     
                            </div>      
                        </div>
                        <table id="table-drvrs" class="table table-data table-hover table-responsive">
                            <thead> 
                                <tr>
                                    <th>Id</th>
                                    <th>Nome</th>
                                    <th>Carro</th>
                                    <th>CPF</th>
                                    <th>Sexo</th>
                                    <th>Status</th>
                                    <th>Alterar status</th>
                                </tr>
                            </thead> 
                            <a ></a>
                            <tbody ng-cloak>
                            <div ng-show="selectError" id="selectErro" ui-alert></div> 
                            <tr ng-repeat="driver in drivers">
                                <td>{{::driver.id}}</td>
                                <td id="name">{{::driver.name}}</td>
                                <td>{{::driver.car}}</td>
                                <td>{{::driver.cpf}}</td>
                                <td>{{::driver.gender}}</td>
                                <td ng-show="driver.status == 'true'">Ativo</td>
                                <td ng-show="driver.status == 'false'">Inativo</td>
                                <td id="opcao">
                                    <form class="form-group"> 
                                        <select ng-change="changeStatusDriver(driver.status2, driver)" ng-model="driver.status2" class="form-control">
                                            <option value="" disabled selected>SELECIONAR</option>
                                            <option ng-show="driver.status == 'false'" value="true">ATIVO</option>
                                            <option ng-show="driver.status == 'true'" value="false">INATIVO</option>
                                        </select>
                                    </form>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <div ng-cloak   ng-show="viewD" class="alert alert-danger text-center">
                            <strong>Nenhum motorista cadastrado.</strong> 
                        </div>
                    </div>
                </div>
            </div>
        </section>  
        <%@include file="WEB-INF/jspf/erro-modal.jspf" %>
        <%@include file="WEB-INF/jspf/confirmStatus.jspf" %>
        <%@include file="WEB-INF/jspf/success-change.jspf" %>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
        <!-- Custom Js -->
        <script src="resources/js/custom.js" type="text/javascript"></script>
    </body>
</html>


